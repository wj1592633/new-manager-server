package com.wj.manager.vercode.store;

import com.wj.manager.vercode.constant.VerCodeConstant;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.UUID;

public class ValidateCodeSessionStorer implements ValidateCodeStorer{
    @Override
    public String save( String code, HttpServletRequest request, HttpServletResponse respponse) {
        String key = UUID.randomUUID().toString();
        request.getSession().setAttribute(VerCodeConstant.VER_CODE_KEY + key, code);
        return key;
    }

    @Override
    public String get(String key, HttpServletRequest request, HttpServletResponse response) {
        return (String) request.getSession().getAttribute(VerCodeConstant.VER_CODE_KEY + key);
    }

    @Override
    public void remove(String key, HttpServletRequest request, HttpServletResponse response) {
        request.getSession().removeAttribute(VerCodeConstant.VER_CODE_KEY + key);
    }
}
