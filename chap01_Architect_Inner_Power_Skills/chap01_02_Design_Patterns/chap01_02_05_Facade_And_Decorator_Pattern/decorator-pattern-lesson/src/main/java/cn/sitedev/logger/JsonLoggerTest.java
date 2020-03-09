package cn.sitedev.logger;

import org.slf4j.Logger;

public class JsonLoggerTest {
    private static final Logger logger = JsonLoggerFactory.getLogger(JsonLoggerTest.class);

    public static void main(String[] args) {
        logger.info("系统正常");
        logger.error("系统错误");
        try {
            int i = 1 / 0;
        } catch (Exception e) {
            logger.error("执行除法运算时发生异常", e);
        }

    }
}
