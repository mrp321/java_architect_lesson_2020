package cn.sitedev.delegate.mvc.controller;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class MemberController {
    public void getMemberById(HttpServletResponse response, String mid) {
        try {
            response.getWriter().write("getMemberById=>" + mid);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
