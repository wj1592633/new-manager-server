package com.wj.manager.test;

import org.springframework.cglib.proxy.Callback;

import java.lang.reflect.Method;

public interface ConditionalCallback extends Callback {

		boolean isMatch(Method candidateMethod);
	}