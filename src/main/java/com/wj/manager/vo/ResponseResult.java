package com.wj.manager.vo;

import com.wj.manager.business.entity.User;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.LinkedHashMap;
import java.util.Map;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ApiModel("响应数据结构")
public class ResponseResult implements Serializable {
    @ApiModelProperty(value = "响应状态码",example = "200")
    private int status;
    @ApiModelProperty(value = "请求返回的数据")
    private Object data;
    @ApiModelProperty(value = "请求过程如果token刷新，返回的新token")
    private Token token;

    @Data
    private static class Token implements Serializable{
        private int code;
        private String tokenData;
        protected Token(){}
        protected Token(int code, String tokenData) {
            this.code = code;
            this.tokenData = tokenData;
        }


    }

    public void ok(){
        ok(null);
    }

    public void ok(Object data){
        this.setStatus(200);
        this.setData(data);
    }

    public void withTokenData(int code, String tokenData){
        Token token = new Token(code, tokenData);
        this.setToken(token);
    }


    public void fail(Object failMsg){
        this.setStatus(400);
        this.setData(failMsg);
    }

    public void fail(){
        this.setStatus(400);
        this.setData("操作失败");
    }

    public void needLogin401(){
        this.setStatus(401);
        this.setData("请登录");
    }
    public void needLogin401(Object data){
        this.setStatus(401);
        this.setData(data);
    }
    public void accessDeny403(Object data){
        this.setStatus(403);
        this.setData(data);
    }
    public void accessDeny403(){
        this.setStatus(403);
        this.setData("无权访问");
    }

    public void pushLoginUser(User user){
        this.setStatus(200);
        this.setData(user);
    }
    public void buildLoginResult(String token){
        if (this.token == null){
            this.token = new Token(1,token);
        }
    }
}
