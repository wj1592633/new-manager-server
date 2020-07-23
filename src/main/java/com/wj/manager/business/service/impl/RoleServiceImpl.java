package com.wj.manager.business.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wj.manager.business.entity.Role;
import com.wj.manager.business.mapper.RoleMapper;
import com.wj.manager.business.service.RoleService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 角色表 服务实现类
 * </p>
 *
 * @author wj
 */
@Service
public class RoleServiceImpl extends ServiceImpl<RoleMapper, Role> implements RoleService {

}
