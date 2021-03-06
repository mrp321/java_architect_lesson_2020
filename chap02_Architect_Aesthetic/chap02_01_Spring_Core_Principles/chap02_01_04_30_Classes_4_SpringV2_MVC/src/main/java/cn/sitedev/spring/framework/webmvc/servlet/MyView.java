package cn.sitedev.spring.framework.webmvc.servlet;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.RandomAccessFile;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 视图
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MyView {
    private File viewFile;

    public void render(Map<String, ?> model, HttpServletRequest request,
                       HttpServletResponse response) throws Exception {
        StringBuffer stringBuffer = new StringBuffer();

        RandomAccessFile randomAccessFile = new RandomAccessFile(this.viewFile, "r");
        String line = null;
        while ((line = randomAccessFile.readLine()) != null) {
            line = new String(line.getBytes("ISO-8859-1"), "UTF-8");
            Pattern pattern = Pattern.compile("￥\\{[^\\}]+\\}", Pattern.CASE_INSENSITIVE);
            Matcher matcher = pattern.matcher(line);
            while (matcher.find()) {
                String paramName = matcher.group();
                paramName = paramName.replaceAll("￥\\{|\\}", "");
                Object paramValue = model.get(paramName);
                if (paramValue == null) {
                    continue;
                }
                line = matcher.replaceFirst(makeStringForRegExp(paramValue.toString()));
                matcher = pattern.matcher(line);
            }
            stringBuffer.append(line);
        }
        response.setCharacterEncoding("UTF-8");
        response.getWriter().write(stringBuffer.toString());

    }

    // 处理特殊字符
    private String makeStringForRegExp(String str) {
        return str
                .replace("\\","\\\\").replace("*", "\\*")
                .replace("+","\\+").replace("|","\\|")
                .replace("{","\\{").replace("}","\\}")
                .replace("(", "\\(").replace(")","\\)")
                .replace("^", "\\^").replace("$","\\$")
                .replace("[","\\[").replace("]","\\]")
                .replace("?","\\?").replace(",","\\,")
                .replace(".","\\.").replace("&","\\&");
    }
}
