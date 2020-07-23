package com.wj.manager.vo;

import com.wj.manager.business.entity.User;

public class ResponseResultHelper {
    private static ThreadLocal<ResponseResultHelper> threadLocal = new ThreadLocal<>();
    private ResponseResult result = null;

    private ResponseResultHelper() {
        this.result = new ResponseResult();
    }

    public static ResponseResultHelper withTokenData(int code, String tokenData){
        ResponseResultHelper helper = getInstance();
        helper.result.withTokenData(code,tokenData);
        return helper;
    }

    public static ResponseResult buildOkResult(Object data){
        ResponseResultHelper helper = getInstance();
        helper.result.ok(data);
        threadLocal.remove();
        return helper.result;
    }
    public static ResponseResult buildOkResult(){
       return buildOkResult(null);
    }

    public static ResponseResult buildFailResult(Object data){
        ResponseResultHelper helper = getInstance();
        helper.result.fail(data);
        threadLocal.remove();
        return helper.result;
    }

    private static ResponseResultHelper getInstance(){
        ResponseResultHelper helper = threadLocal.get();
        if (helper == null){
            helper = new ResponseResultHelper();
            threadLocal.set(helper);
        }
        return helper;
    }

    public static ResponseResult build401Result(Object data){
        ResponseResultHelper helper = getInstance();
        helper.result.needLogin401(data);
        threadLocal.remove();
        return helper.result;
    }
    public static ResponseResult build401Result(){
        ResponseResultHelper helper = getInstance();
        helper.result.needLogin401();
        threadLocal.remove();
        return helper.result;
    }
    public static ResponseResult build403Result(Object data){
        ResponseResultHelper helper = getInstance();
        helper.result.accessDeny403(data);
        threadLocal.remove();
        return helper.result;
    }
    public static ResponseResult build403Result(){
        ResponseResultHelper helper = getInstance();
        helper.result.accessDeny403();
        threadLocal.remove();
        return helper.result;
    }
    public static ResponseResult buildFailResult(){
        return buildFailResult(null);
    }

    public static ResponseResult pushLoginUser(User user){
        ResponseResultHelper helper = getInstance();
        helper.result.pushLoginUser(user);
        return helper.result;
    }

    public static ResponseResult buildLoginResult(String token){
        ResponseResultHelper helper = getInstance();
        helper.result.buildLoginResult(token);
        threadLocal.remove();
        return helper.result;
    }
}
