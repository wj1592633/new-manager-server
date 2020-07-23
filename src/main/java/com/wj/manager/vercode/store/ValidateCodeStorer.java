package com.wj.manager.vercode.store;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public interface ValidateCodeStorer {
    String save(String code, HttpServletRequest request, HttpServletResponse response);
    String get(String key, HttpServletRequest request, HttpServletResponse response);
    void remove(String key, HttpServletRequest request, HttpServletResponse response);
}
