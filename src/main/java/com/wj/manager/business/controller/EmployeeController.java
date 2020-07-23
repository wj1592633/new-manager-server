package com.wj.manager.business.controller;


import com.baomidou.mybatisplus.core.metadata.IPage;
import com.wj.manager.business.entity.Employee;
import com.wj.manager.business.query.PageQuery;
import com.wj.manager.business.service.EmployeeService;
import com.wj.manager.vo.ResponseResult;
import com.wj.manager.vo.ResponseResultHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * <p>
 * 雇员表 前端控制器
 * </p>
 *
 * @author wj
 */
@RestController
public class EmployeeController {
    @Autowired
    EmployeeService employeeService;
    @RequestMapping(value = "/v1/employees", method = RequestMethod.GET)
    public ResponseResult getEmployees(PageQuery<Employee> page){
        IPage<Employee> employees = employeeService.getEmployees(page);
        return ResponseResultHelper.buildOkResult(employees);
    }


}
