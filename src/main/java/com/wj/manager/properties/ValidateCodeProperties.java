package com.wj.manager.properties;

import com.wj.manager.security.constant.SecurityConstant;
import lombok.Data;

@Data
public class ValidateCodeProperties {
    private ImageCodeProperties image = new ImageCodeProperties();
    private String store_type = "redis";
    //需要校验认证码的路径，用,分开
    private String validateUrl = SecurityConstant.LOGIN_PROCESSING_URL;
}
