package com.wj.manager.vercode.generator;

import com.wf.captcha.SpecCaptcha;
import com.wf.captcha.base.Captcha;
import com.wj.manager.properties.SecurityProperties;

import java.awt.*;

public class SmsValidateCodeGenerator implements ValidateCodeGenerator{

    @Override
    public Captcha generateValidateCode() {
        Captcha captcha = simpleCode();
        return captcha;
    }

    private Captcha simpleCode() {
        // 三个参数分别为宽、高、位数
        SpecCaptcha specCaptcha = new SpecCaptcha();
        // 设置类型，纯数字、纯字母、字母数字混合
        specCaptcha.setCharType(Captcha.TYPE_ONLY_NUMBER);
        specCaptcha.text();
        return specCaptcha;
    }

}
