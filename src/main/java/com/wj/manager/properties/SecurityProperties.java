package com.wj.manager.properties;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "base.security")
@Data
public class SecurityProperties {
    BrowserProperties browser = new BrowserProperties();
    ValidateCodeProperties code = new ValidateCodeProperties();
    LoginProperties login = new LoginProperties();

}
