package cn.sitedev.plugin;

import org.apache.ibatis.cache.CacheKey;
import org.apache.ibatis.executor.Executor;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.ParameterMapping;
import org.apache.ibatis.plugin.*;
import org.apache.ibatis.session.ResultHandler;
import org.apache.ibatis.session.RowBounds;

import java.util.List;
import java.util.Properties;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Intercepts({@Signature(type = Executor.class, method = "query", args = {MappedStatement.class, Object.class,
        RowBounds.class, ResultHandler.class}), @Signature(type = Executor.class, method = "query", args =
        {MappedStatement.class, Object.class, RowBounds.class, ResultHandler.class, CacheKey.class, BoundSql.class})})
public class FeeInterceptor implements Interceptor {

    private static final String TABLE_REGEX_IN_SELECT_SQL = "(from\\s*)(\\w*)(\\s*where)";

    @Override
    public Object intercept(Invocation invocation) throws Throwable {
        Object[] args = invocation.getArgs();
        MappedStatement ms = (MappedStatement) args[0];
        Executor executor = (Executor) invocation.getTarget();
        Object parameter = args[1];
        RowBounds rowBounds = (RowBounds) args[2];
        ResultHandler resultHandler = (ResultHandler) args[3];
        CacheKey cacheKey;
        BoundSql boundSql;
        if (args.length == 4) {
            //4 个参数时
            boundSql = ms.getBoundSql(parameter);
            cacheKey = executor.createCacheKey(ms, parameter, rowBounds, boundSql);
        } else {
            //6 个参数时
            cacheKey = (CacheKey) args[4];
            boundSql = (BoundSql) args[5];
        }
        if (ms.getId().contains("selectByFeeDate")) {
            // 参数映射列表
            List<ParameterMapping> parameterMappingList = boundSql.getParameterMappings();
            if (parameterMappingList != null && parameterMappingList.size() == 1) {
                ParameterMapping feeParameterMapping = parameterMappingList.get(0);
                // 参数名
                String feeProperty = feeParameterMapping.getProperty();
                // 参数java类型
                Class<?> javaType = feeParameterMapping.getJavaType();
                if ("feeDate".equals(feeProperty) && javaType == String.class) {
                    // 参数值
                    String feeParameterObject = (String) boundSql.getParameterObject();
                    // 匹配到SQL中的表名, 并进行替换
                    Pattern pattern = Pattern.compile(TABLE_REGEX_IN_SELECT_SQL);
                    Matcher matcher = pattern.matcher(boundSql.getSql());
                    if (matcher.find()) {
                        // 原表名: fee
                        String oldTableName = matcher.group(2);
                        // 新表名: fee_yyyyMMdd
                        String newTableName = oldTableName + "_" + feeParameterObject.substring(0, 6);
                        // 将SQL中的表名替换成新表名
                        String sqlAfterReplacingTableName = boundSql.getSql().replaceAll(TABLE_REGEX_IN_SELECT_SQL,
                                "$1" + newTableName + "$3");
                        // 封装成新的BoundSql
                        boundSql = new BoundSql(ms.getConfiguration(), sqlAfterReplacingTableName,
                                boundSql.getParameterMappings(), parameter);
                    }
                }
            }
        }
        return executor.query(ms, parameter, rowBounds, resultHandler, cacheKey, boundSql);
    }

    @Override
    public Object plugin(Object target) {
        return Plugin.wrap(target, this);
    }

    @Override
    public void setProperties(Properties properties) {
    }
}
