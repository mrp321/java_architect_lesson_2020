package cn.sitedev.sqlbuilder;

import java.util.Arrays;

public class SqlBuilderTest {
    public static void main(String[] args) {
        QueryRule queryRule = QueryRule.getInstance();
        queryRule.addAscOrder("age");
        queryRule.andEqual("addr", "China");
        queryRule.andLike("name", "Sitedev");
        QueryRuleSqlBuilder builder = new QueryRuleSqlBuilder(queryRule);

        System.out.println("sql : " + builder.builder("t_member"));
        System.out.println("params : " + Arrays.toString(builder.getValues()));
    }
}
