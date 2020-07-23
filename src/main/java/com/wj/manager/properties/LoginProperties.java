package com.wj.manager.properties;

import lombok.Data;

import java.util.LinkedHashMap;
import java.util.Map;
@Data
public class LoginProperties {
    Map<String,String> mapping = new LinkedHashMap<>();
}
