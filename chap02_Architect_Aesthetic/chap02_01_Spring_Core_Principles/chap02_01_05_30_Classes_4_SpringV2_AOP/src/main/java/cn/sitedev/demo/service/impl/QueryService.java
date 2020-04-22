package cn.sitedev.demo.service.impl;

import cn.sitedev.demo.service.IQueryService;
import cn.sitedev.spring.framework.annotation.MyService;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 查询业务
 */
@MyService
public class QueryService implements IQueryService {
    @Override
    public String query(String name) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String time = sdf.format(new Date());
        String json = "{ name: \"" + name + "\", time: \"" + time + "\"}";

        System.out.println("这是在业务方法中打印的:" + json);
        return json;
    }
}
