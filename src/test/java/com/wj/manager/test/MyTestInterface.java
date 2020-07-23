package com.wj.manager.test;

import com.wj.manager.business.entity.User;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;

public interface MyTestInterface extends BeanFactoryAware{
    default void setUser(User user){
        System.out.println("setUser====>>>>>>>>>setUser...");
    }

    @Override
    default void setBeanFactory(BeanFactory beanFactory) throws BeansException{
        System.out.println("setBeanFactory====>>>>>>>>>setBeanFactory...");
    }
}
