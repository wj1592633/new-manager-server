package com.wj.manager.business.controller;


import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 系统用户表 前端控制器
 * </p>
 *
 * @author wj
 */
@RestController
public class UserController {

    @RequestMapping(value = "/user/{id}", method = RequestMethod.GET)
    @PreAuthorize("hasAuthority('/aaa')")
    public Object getuser(){
        return "user....";
    }
    @RequestMapping(value = "/user1/{id}", method = RequestMethod.GET)
    @PreAuthorize("hasAuthority('/ddd')")
    public Object getuser1(){
        return "user....ddd";
    }
    @RequestMapping(value = "/user2/{id}", method = RequestMethod.GET)
    public Object getuser2(){
        return "user....222";
    }
    @RequestMapping(value = "/user3/{id}", method = RequestMethod.GET)
    public Object getuser32(){
        return "user....333";
    }
}
