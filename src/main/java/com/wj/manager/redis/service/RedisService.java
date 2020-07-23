package com.wj.manager.redis.service;

import com.wj.manager.properties.SecurityProperties;
import com.wj.manager.vercode.constant.VerCodeConstant;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Component;

import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Component
public class RedisService {
    @Autowired
    RedisTemplate redisTemplate;
    @Autowired
    SecurityProperties securityProperties;

    public String saveLoginVerCode(String code) {
        if (StringUtils.isNotBlank(code)) {
            String key = UUID.randomUUID().toString();
            redisTemplate.opsForValue().setIfAbsent(VerCodeConstant.VER_CODE_KEY + key, code, securityProperties.getCode().getImage().getExpire(), TimeUnit.SECONDS);
            return key;
        }
        return null;
    }


    public String getLoginVerCode(String key) {
        if (StringUtils.isNotBlank(key)) {
            return (String) redisTemplate.opsForValue().get(VerCodeConstant.VER_CODE_KEY + key);
        }
        return null;
    }
    public void removeLoginVerCode(String key) {
        if (StringUtils.isNotBlank(key)) {
            redisTemplate.delete(VerCodeConstant.VER_CODE_KEY + key);
        }
    }
}
