package com.wj.manager.exception;

public class CustomException extends RuntimeException{
    public CustomException(){}
    public CustomException(String msg){
        super(msg);
    }
}
