package com.wj.manager.vercode.store;

import com.wj.manager.redis.service.RedisService;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ValidateCodeRedisStorer implements ValidateCodeStorer {

    RedisService redisService;

    @Override
    public String save(String code, HttpServletRequest request, HttpServletResponse response) {
       return redisService.saveLoginVerCode(code);
    }

    @Override
    public String get(String key, HttpServletRequest request, HttpServletResponse response) {
        return redisService.getLoginVerCode(key);
    }

    @Override
    public void remove(String key, HttpServletRequest request, HttpServletResponse response) {
        redisService.removeLoginVerCode(key);
    }


    public RedisService getRedisService() {
        return redisService;
    }

    public void setRedisService(RedisService redisService) {
        this.redisService = redisService;
    }
}
