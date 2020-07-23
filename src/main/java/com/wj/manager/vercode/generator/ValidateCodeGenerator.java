package com.wj.manager.vercode.generator;

import com.wf.captcha.base.Captcha;

public interface ValidateCodeGenerator {
    Captcha generateValidateCode();
}
