package com.wj.manager.business.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wj.manager.business.entity.Menu;
import com.wj.manager.business.mapper.MenuMapper;
import com.wj.manager.business.service.MenuService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 菜单表，权限表 服务实现类
 * </p>
 *
 * @author wj
 */
@Service
public class MenuServiceImpl extends ServiceImpl<MenuMapper, Menu> implements MenuService {

}
