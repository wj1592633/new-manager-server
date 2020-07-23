package com.wj.manager.vercode.config;

import com.wj.manager.properties.SecurityProperties;
import com.wj.manager.redis.service.RedisService;
import com.wj.manager.vercode.generator.ImageValidateCodeGenerator;
import com.wj.manager.vercode.generator.SmsValidateCodeGenerator;
import com.wj.manager.vercode.generator.ValidateCodeGenerator;
import com.wj.manager.vercode.sender.ImageValidateCodeSender;
import com.wj.manager.vercode.sender.SmsValidateCodeSender;
import com.wj.manager.vercode.sender.ValidateCodeSender;
import com.wj.manager.vercode.store.ValidateCodeRedisStorer;
import com.wj.manager.vercode.store.ValidateCodeSessionStorer;
import com.wj.manager.vercode.store.ValidateCodeStorer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ValidateCodeConfig {
    @Autowired
    SecurityProperties securityProperties;

    @Autowired
    RedisService redisService;
    /**
     * 没有名字为imageValidateCodeGenerator的对象才创建
     * @return
     */
    @Bean
    @ConditionalOnMissingBean(name = "imageValidateCodeGenerator")
    public ValidateCodeGenerator imageValidateCodeGenerator(){
        ImageValidateCodeGenerator imageValidateCodeGenerator = new ImageValidateCodeGenerator();
        imageValidateCodeGenerator.setSecurityProperties(securityProperties);
        return imageValidateCodeGenerator;
    }

    /**
     * 没有SmsValidateCodeGenerator类的对象才创建
     * @return
     */
    @Bean
    @ConditionalOnMissingBean(SmsValidateCodeGenerator.class)
    public ValidateCodeGenerator smsValidateCodeGenerator(){
        SmsValidateCodeGenerator smsValidateCodeGenerator = new SmsValidateCodeGenerator();
        return smsValidateCodeGenerator;
    }

    @Bean
    @ConditionalOnMissingBean(name = "imageValidateCodeSender")
    public ValidateCodeSender imageValidateCodeSender(){
        ImageValidateCodeSender imageValidateCodeSender = new ImageValidateCodeSender();
        return imageValidateCodeSender;
    }

    @Bean
    @ConditionalOnMissingBean(name = "smsValidateCodeSender")
    public ValidateCodeSender smsValidateCodeSender(){
        SmsValidateCodeSender smsValidateCodeSender = new SmsValidateCodeSender();
        return smsValidateCodeSender;
    }
    @Bean
    @ConditionalOnMissingBean(ValidateCodeStorer.class)
    @ConditionalOnProperty(prefix = "base.security.code", name = "store_type", havingValue = "redis")
    public ValidateCodeStorer validateCodeRedisStorer(){
        ValidateCodeRedisStorer redisStorer = new ValidateCodeRedisStorer();
        redisStorer.setRedisService(redisService);
        return redisStorer;
    }

    /**
     * 注意：：session方式并没有真实实现功能
     * @return
     */
    @Bean
    @ConditionalOnMissingBean(ValidateCodeStorer.class)
    @ConditionalOnProperty(prefix = "base.security.code", name = "store_type", havingValue = "session")
    public ValidateCodeStorer validateCodeSessionStorer(){
        ValidateCodeStorer sessionStorer = new ValidateCodeSessionStorer();
        return sessionStorer;
    }

}
