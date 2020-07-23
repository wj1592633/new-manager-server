package com.wj.manager.business.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.wj.manager.business.entity.Employee;
import com.wj.manager.business.entity.User;
import com.wj.manager.business.entity.cloumns.EmployeeColumns;
import com.wj.manager.business.mapper.EmployeeMapper;
import com.wj.manager.business.query.PageQuery;
import com.wj.manager.business.service.EmployeeService;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

/**
 * <p>
 * 雇员表 服务实现类
 * </p>
 *
 * @author wj
 */
@Service
public class EmployeeServiceImpl extends ServiceImpl<EmployeeMapper, Employee> implements EmployeeService {

    @Override
    public IPage<Employee> getEmployees(PageQuery<Employee> page) {
        QueryWrapper<Employee> wrapper = new QueryWrapper<>();
        String name = (String) page.getKeyword();
        if (StringUtils.isNotBlank(name)) {
            wrapper.likeRight(EmployeeColumns.name, name);
        }
        if (page.getKeyword1() != null) {
            wrapper.eq(EmployeeColumns.deptId, page.getKeyword1());
        }
        return baseMapper.selectPage(page, wrapper);
    }
    @Autowired
    @Override
    @ConditionalOnMissingBean(User.class)
    public void test1(){
        System.out.println("test1 =>>>>>>>>>");
    }
    @RequestMapping(value = "/1111",method = RequestMethod.POST)
    @ConditionalOnMissingBean(UserServiceImpl.class)
    public void test2(){
        System.out.println("test2 =>>>>>>>>>");
    }
    public void test3(String name,User user1,Employee e1, User user2){
        System.out.println("test3 =>>>>>>>>>");
    }
}
