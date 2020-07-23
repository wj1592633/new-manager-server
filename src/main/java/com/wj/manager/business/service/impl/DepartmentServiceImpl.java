package com.wj.manager.business.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wj.manager.business.entity.Department;
import com.wj.manager.business.mapper.DepartmentMapper;
import com.wj.manager.business.service.DepartmentService;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 部门表 服务实现类
 * </p>
 *
 * @author wj
 */
@Service
public class DepartmentServiceImpl extends ServiceImpl<DepartmentMapper, Department> implements DepartmentService {

}
