package com.wj.manager.vercode.sender;

import com.wf.captcha.base.Captcha;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface ValidateCodeSender {
    void send(String key,Captcha captcha, HttpServletRequest request, HttpServletResponse response);
}
