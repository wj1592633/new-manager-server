package com.wj.manager.util;

public class KeyUtil {
    public static String getCodeKey(){
        return null;
    }
    public static String getSmsValidateCodeKey(String phone){
        return "smsValidateCode:" + phone;
    }
}
