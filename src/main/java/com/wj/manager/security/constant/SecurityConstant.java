package com.wj.manager.security.constant;

import java.util.Arrays;

public interface SecurityConstant {
    public final static String IMAGE_VALIDATE_CODE_SESSION_KEY = "captcha";
    public final static String LOGIN_TYPE_SEPARATOR = "type=";
    public final static String LOGOUT_SUCCESS_MSG = "退出登陆成功";
    public final static String SERVER_ERROR_MSG = "服务器繁忙";
    public final static String NEED_LOGIN_MSG = "请登录";
    public final static String LOGIN_AGAIN_MSG = "登陆已经失效，请重新登陆";
    public final static String LOGIN_USERNAME_PASSWORD_ERROR_MSG = "用户名或者密码错误";
    public final static String LOGIN_PHONE_ERROR_MSG = "手机号错误";
    public final static String LOGIN_PROCESSING_URL = "/login";
    public final static String LOGIN_PAGE = "/authenticatiuon/require";
    public final static String[] IGNORE_URL = {
            "/**/*.html", "/code/**", "/logout",
            LOGIN_PAGE, "/**/*.css", "/**/*.js",
            "/**/*.jpg", "/**/*.ico",
            "/v2/api-docs",
            "/swagger-resources/**",
            "/swagger-ui.html","/webjars/**"};
    public final static String[] PERMIT_URL = new String[]{
            "/tt", LOGIN_PROCESSING_URL, "/login/phone","/user3/**"};
}
