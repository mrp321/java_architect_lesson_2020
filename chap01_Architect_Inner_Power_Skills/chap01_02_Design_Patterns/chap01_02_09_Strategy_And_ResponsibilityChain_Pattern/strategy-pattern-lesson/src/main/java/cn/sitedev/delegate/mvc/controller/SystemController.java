package cn.sitedev.delegate.mvc.controller;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class SystemController {
    public void logout(HttpServletResponse response) {
        try {
            response.getWriter().write("logout");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
