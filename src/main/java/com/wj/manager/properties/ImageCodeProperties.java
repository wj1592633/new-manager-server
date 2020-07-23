package com.wj.manager.properties;

import com.wj.manager.enumeration.ImageCodeType;
import com.wj.manager.security.constant.SecurityConstant;
import lombok.Data;

/**
 * 图片验证码参数
 */
@Data
public class ImageCodeProperties {
    private int width = 130;
    private int height = 48;
    private ImageCodeType type = ImageCodeType.SIMPLE;
    private int length = 5;
    private int size = 32;
    private int expire = 300;//单位秒
}
