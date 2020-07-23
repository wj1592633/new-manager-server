package com.wj.manager.vercode.generator;

import com.wf.captcha.ArithmeticCaptcha;
import com.wf.captcha.GifCaptcha;
import com.wf.captcha.SpecCaptcha;
import com.wf.captcha.base.Captcha;
import com.wj.manager.properties.SecurityProperties;

import java.awt.*;

public class ImageValidateCodeGenerator implements ValidateCodeGenerator{
    SecurityProperties securityProperties;

    @Override
    public Captcha generateValidateCode() {
        Captcha captcha = null;
        switch (securityProperties.getCode().getImage().getType()) {
            case GIF:
                captcha = gitCode();
                break;
            case MATH:
                captcha = mathCode();
                break;
            default:
                captcha = simpleCode();
        }
        return captcha;
    }


    private Captcha mathCode(){
        // 算术类型
        ArithmeticCaptcha captcha = new ArithmeticCaptcha(
                securityProperties.getCode().getImage().getWidth(),
                securityProperties.getCode().getImage().getHeight());
        captcha.setLen(securityProperties.getCode().getImage().getLength());  // 几位数运算，默认是两位
        captcha.getArithmeticString();  // 获取运算的公式：3+2=?
        captcha.text();  // 获取运算的结果：5
        return captcha;
    }

    private Captcha simpleCode() {
        // 三个参数分别为宽、高、位数
        SpecCaptcha specCaptcha = new SpecCaptcha(
                securityProperties.getCode().getImage().getWidth(),
                securityProperties.getCode().getImage().getHeight(),
                securityProperties.getCode().getImage().getLength()
        );
        // 设置字体
        specCaptcha.setFont(new Font("Verdana", Font.PLAIN, securityProperties.getCode().getImage().getSize()));  // 有默认字体，可以不用设置
        // 设置类型，纯数字、纯字母、字母数字混合
        specCaptcha.setCharType(Captcha.TYPE_NUM_AND_UPPER);
        specCaptcha.text();
        return specCaptcha;
    }

    private Captcha gitCode() {
        // gif类型
        GifCaptcha captcha = new GifCaptcha(
                securityProperties.getCode().getImage().getWidth(),
                securityProperties.getCode().getImage().getHeight(),
                securityProperties.getCode().getImage().getLength());
        captcha.setFont(new Font("Verdana", Font.PLAIN, securityProperties.getCode().getImage().getSize()));  // 有默认字体，可以不用设置
        // 设置类型，纯数字、纯字母、字母数字混合
        captcha.setCharType(Captcha.TYPE_NUM_AND_UPPER);
        captcha.text();
        return captcha;
    }

    public SecurityProperties getSecurityProperties() {
        return securityProperties;
    }

    public void setSecurityProperties(SecurityProperties securityProperties) {
        this.securityProperties = securityProperties;
    }
}
