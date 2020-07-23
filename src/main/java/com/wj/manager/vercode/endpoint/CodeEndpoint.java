package com.wj.manager.vercode.endpoint;

import com.wf.captcha.base.Captcha;
import com.wj.manager.properties.SecurityProperties;
import com.wj.manager.vercode.generator.ValidateCodeGenerator;
import com.wj.manager.vercode.sender.ValidateCodeSender;
import com.wj.manager.vercode.store.ValidateCodeStorer;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

@Controller
@Slf4j
public class CodeEndpoint {

    @Autowired
    SecurityProperties securityProperties;

    /**
     * generators和senders的key都是"imageValidateCodeGenerator,smsValidateCodeSender"格式
     * 根据 /code/{type} 传过来路径的type匹配获取对应的对象
     */
    @Autowired
    Map<String, ValidateCodeGenerator> generators;

    @Autowired
    Map<String, ValidateCodeSender> senders;
    
    @Autowired
    ValidateCodeStorer validateCodeStorer;

    /**
     *
     * @param type 默认可以填image或sms。想要更多，可以自己实现
     * @param request
     * @param response
     * @throws IOException
     */
    @RequestMapping(value = "/code/{type}", method = RequestMethod.GET)
    public void validateCode(@PathVariable("type") String type, HttpServletRequest request, HttpServletResponse response) throws IOException {
        String generatorFullName = type + "ValidateCodeGenerator";
        String senderFullName = type + "ValidateCodeSender";
        ValidateCodeGenerator generator = generators.get(generatorFullName);
        Captcha captcha = generator.generateValidateCode();
        String code = captcha.text();
        String key = validateCodeStorer.save(code, request, response);
        ValidateCodeSender sender = senders.get(senderFullName);
        sender.send(key,captcha,request,response);
    }






}
