package cn.sitedev.delegate.mvc.controller;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class OrderController {
    public void getOrderById(HttpServletResponse response, String mid) {
        try {
            response.getWriter().write("getOrderById=>" + mid);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
