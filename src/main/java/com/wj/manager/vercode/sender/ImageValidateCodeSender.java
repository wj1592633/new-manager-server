package com.wj.manager.vercode.sender;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wf.captcha.base.Captcha;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.LinkedHashMap;

public class ImageValidateCodeSender implements ValidateCodeSender{

    @Override
    public void send(String key,Captcha captcha, HttpServletRequest request, HttpServletResponse response) {
        // 设置请求头为输出图片类型
        response.setContentType("image/gif");
        response.setHeader("Pragma", "No-cache");
        response.setHeader("Cache-Control", "no-cache");
        response.setDateHeader("Expires", 0);

        // 输出图片流
        try {
            LinkedHashMap<Object, Object> map = new LinkedHashMap<>();
            map.put("key",key);
            map.put("code",captcha.toBase64());
            //captcha.out(response.getOutputStream());
            response.getWriter().write(new ObjectMapper().writeValueAsString(map));
            return;
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
