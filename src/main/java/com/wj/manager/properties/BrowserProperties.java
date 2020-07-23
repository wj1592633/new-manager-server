package com.wj.manager.properties;

import com.wj.manager.enumeration.LoginSuccessEnum;
import lombok.Data;

@Data
public class BrowserProperties {
    private String loginPage;
    LoginSuccessEnum lsType = LoginSuccessEnum.JSON;
}
