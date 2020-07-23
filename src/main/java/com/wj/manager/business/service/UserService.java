package com.wj.manager.business.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.wj.manager.business.entity.User;

/**
 * <p>
 * 系统用户表 服务类
 * </p>
 *
 * @author wj
 */
public interface UserService extends IService<User> {
    User getRecordByUsername(String account);
    User getRecordByPhone(String phone);
}
