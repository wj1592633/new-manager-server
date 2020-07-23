package com.wj.manager.test;

import org.springframework.beans.factory.BeanFactory;
import org.springframework.beans.factory.BeanFactoryAware;
import org.springframework.cglib.proxy.MethodInterceptor;
import org.springframework.cglib.proxy.MethodProxy;
import org.springframework.lang.Nullable;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

public  class BeanFactoryAwareMethodInterceptor implements MethodInterceptor, ConditionalCallback {

		@Override
		@Nullable
		public Object intercept(Object obj, Method method, Object[] args, MethodProxy proxy) throws Throwable {
			System.out.println("BeanFactoryAwareMethodInterceptor，，方法名称：---》》》"+method.getName());
			Class<?> aClass1 = obj.getClass();
			Class<?> aClass = obj.getClass().getSuperclass();
			if (aClass.getName().contains("$$")) {
				Class<?> superclass = aClass.getSuperclass();
				if (superclass != null && superclass != Object.class) {
					aClass = superclass;
				}
			}
			if (BeanFactoryAware.class.isAssignableFrom(aClass)) {
				return proxy.invokeSuper(obj, args);
			}
			return null;
		}

		@Override
		public boolean isMatch(Method candidateMethod) {
			return isSetBeanFactory(candidateMethod);
		}

		public static boolean isSetBeanFactory(Method candidateMethod) {
			String methodName = candidateMethod.getName();
			return (methodName.equals("setBeanFactory") || "setUser".equals(methodName));
			/*return (candidateMethod.getName().equals("setBeanFactory") &&
					candidateMethod.getParameterCount() == 1 &&
					BeanFactory.class == candidateMethod.getParameterTypes()[0]);*/
		}
	}
