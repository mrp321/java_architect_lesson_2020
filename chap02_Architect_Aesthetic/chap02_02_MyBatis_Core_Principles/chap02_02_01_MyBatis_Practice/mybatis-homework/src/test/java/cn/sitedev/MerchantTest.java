package cn.sitedev;

import cn.sitedev.domain.Merchant;
import cn.sitedev.mapper.MerchantMapper;
import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * 商户测试
 */
public class MerchantTest {

    private MerchantMapper merchantMapper;

    @Before
    public void setUp() throws IOException {
        InputStream inputStream = Resources.getResourceAsStream("mybatis-config.xml");
        SqlSessionFactory factory = new SqlSessionFactoryBuilder().build(inputStream);
        // 设置成自动提交
        SqlSession sqlSession = factory.openSession(true);
        merchantMapper = sqlSession.getMapper(MerchantMapper.class);
    }

    @Test
    public void insert() {
        List<Merchant> merchantList = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            Merchant merchant = new Merchant(i + 1, "商户" + (i + 1), Arrays.asList(i + 1, i + 2, i + 3));
            merchantList.add(merchant);
        }
        merchantMapper.insert(merchantList);
    }

    @Test
    public void delete() {
        merchantMapper.delete(new Merchant(99, "商户99", Arrays.asList(99, 100, 101)));
    }

    @Test
    public void update() {
        merchantMapper.update(98, new Merchant(null, "商户99", Arrays.asList(99, 98, 97)));
    }

    @Test
    public void query() {
        List<Merchant> merchantList = merchantMapper.query(new Merchant(1, "商户", Arrays.asList(1, 2, 3)));
        for (Merchant merchant : merchantList) {
            System.out.println("merchant : " + merchant);
        }
    }

    @Test
    public void getBizScopeListById() {
        List<Integer> bizScopeList = merchantMapper.getBizScopeListById(1);
        System.out.println(bizScopeList);
    }
}