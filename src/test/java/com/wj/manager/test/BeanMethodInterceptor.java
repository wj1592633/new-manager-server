package com.wj.manager.test;

import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;
import org.springframework.lang.Nullable;
import java.lang.reflect.Method;

public class BeanMethodInterceptor implements MethodInterceptor, ConditionalCallback {

		@Override
		@Nullable
		public Object intercept(Object enhancedConfigInstance, Method beanMethod, Object[] beanMethodArgs,
					MethodProxy cglibMethodProxy) throws Throwable {

			System.out.println("BeanMethodInterceptor，，方法名称：---》》》"+beanMethod.getName());
			return cglibMethodProxy.invokeSuper(enhancedConfigInstance, beanMethodArgs);

			//return resolveBeanReference(beanMethod, beanMethodArgs, beanFactory, beanName);
		}


		@Override
		public boolean isMatch(Method candidateMethod) {
			return (!(candidateMethod.getName().equals("setBeanFactory")) && (!(candidateMethod.getName().equals("setUser"))));
		}


	}