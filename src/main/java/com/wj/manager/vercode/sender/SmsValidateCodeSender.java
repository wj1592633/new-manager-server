package com.wj.manager.vercode.sender;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.wf.captcha.base.Captcha;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.LinkedHashMap;

public class SmsValidateCodeSender implements ValidateCodeSender {

    @Override
    public void send(String key, Captcha captcha, HttpServletRequest request, HttpServletResponse response) {
        String msg = "模拟向手机发送短信，短信验证码: " + captcha.text();
        // 输出
        System.out.println(msg);
        try {
            response.setContentType("application/json; charset=UTF-8");
            LinkedHashMap<Object, Object> map = new LinkedHashMap<>();
            map.put("key",key);
            map.put("code",msg);
            response.getWriter().write(new ObjectMapper().writeValueAsString(map));
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
