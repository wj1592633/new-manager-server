package com.wj.manager.business.service;

import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.IService;
import com.wj.manager.business.entity.Employee;
import com.wj.manager.business.query.PageQuery;

import java.util.List;

/**
 * <p>
 * 雇员表 服务类
 * </p>
 *
 * @author wj
 */
public interface EmployeeService extends IService<Employee> {
    /**
     * 分页查询员工，如果name deptId有值则作条件查询
     * @param page
     * @return
     */
    IPage<Employee> getEmployees(PageQuery<Employee> page);
    public void test1();
    public void test2();
}
