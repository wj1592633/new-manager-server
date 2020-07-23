package com.wj.manager.util;

import com.fasterxml.jackson.databind.ObjectMapper;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class ServletUtil {
    //返回json数据
    public static void writeJsonData(HttpServletRequest request, HttpServletResponse response, Object data) throws IOException {
        response.setContentType("application/json;charset=utf-8");
        String result = null;
        if (!data.getClass().isAssignableFrom(String.class)){
            result = new ObjectMapper().writeValueAsString(data);
        }else {
            result = (String) data;
        }
        response.getWriter().write(result);
    }
}
