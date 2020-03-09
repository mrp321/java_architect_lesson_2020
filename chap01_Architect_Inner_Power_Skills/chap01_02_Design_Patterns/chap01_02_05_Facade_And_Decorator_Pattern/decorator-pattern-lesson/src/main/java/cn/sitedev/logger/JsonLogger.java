package cn.sitedev.logger;

import com.alibaba.fastjson.JSONObject;
import org.slf4j.Logger;

import java.util.Arrays;

public class JsonLogger extends LoggerDecorator {
    public JsonLogger(Logger logger) {
        super(logger);
    }

    @Override
    public void info(String msg) {
        JSONObject result = newJsonObject();
        result.put("message", msg);
        logger.info(result.toJSONString());
    }


    @Override
    public void error(String msg) {
        JSONObject result = newJsonObject();
        result.put("message", msg);
        logger.error(result.toJSONString());
    }

    @Override
    public void error(String msg, Throwable t) {
        JSONObject result = newJsonObject();
        result.put("exception", t.getClass().getName());
        String trace = Arrays.toString(t.getStackTrace());
        result.put("stackTrace", trace);
        result.put("message", msg);
        logger.error(result.toJSONString());
    }

    public void error(Exception e) {
        JSONObject result = newJsonObject();
        result.put("exception", e.getClass().getName());
        String trace = Arrays.toString(e.getStackTrace());
        result.put("stackTrace", trace);
        logger.error(result.toJSONString());
    }


    private JSONObject newJsonObject() {
        return new JSONObject();
    }
}
