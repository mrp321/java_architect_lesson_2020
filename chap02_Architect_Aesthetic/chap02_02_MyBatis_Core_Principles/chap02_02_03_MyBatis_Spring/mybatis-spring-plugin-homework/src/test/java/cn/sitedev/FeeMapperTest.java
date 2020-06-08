package cn.sitedev;

import cn.sitedev.entity.Fee;
import cn.sitedev.mapper.FeeMapper;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.math.BigDecimal;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Random;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:application-context.xml")
public class FeeMapperTest {
    private static final String feeDate = "20200510";
    private static final String feeMonth = feeDate.substring(0, 6);
    @Autowired
    private FeeMapper feeMapper;

    @Test
    public void test() {
        Fee fee = this.feeMapper.selectByFeeDate(feeDate);
        System.out.println(fee);
    }

    @Test
    @Ignore
    public void insert() throws ParseException {
        int count = 30;
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyyMMdd");
        List<Fee> feeList = new ArrayList<>(count);
        Fee fee = null;
        for (int i = 1; i <= count; i++) {
            Date feeDate = simpleDateFormat.parse(feeMonth + (String.valueOf(i).length() == 1 ? ("0" + i) :
                    String.valueOf(i)));
            fee = new Fee(new BigDecimal(new Random().nextDouble() * 1000).setScale(2, BigDecimal.ROUND_HALF_UP),
                    feeDate);
            feeList.add(fee);
        }
        this.feeMapper.insert(feeList);
    }
}
