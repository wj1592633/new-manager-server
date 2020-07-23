package com.wj.manager.advice;

import com.wj.manager.exception.CustomException;
import com.wj.manager.vo.ResponseResult;
import com.wj.manager.vo.ResponseResultHelper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionAdvice {

    @ExceptionHandler(CustomException.class)
    public ResponseResult handleCustomException(CustomException e){
        log.error(e.getMessage());
        return ResponseResultHelper.buildFailResult(e.getMessage());
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseResult handleIllegalArgumentException(IllegalArgumentException e){
        log.error(e.getMessage());
        return ResponseResultHelper.buildFailResult("请填入正确的内容");
    }

    @ExceptionHandler(BadCredentialsException.class)
    public ResponseResult handleBadCredentialsException(BadCredentialsException e){
        log.error(e.getMessage());
        return ResponseResultHelper.buildFailResult(e.getMessage());
    }

    @ExceptionHandler(Exception.class)
    public ResponseResult handleException(Exception e){
        log.error(e.getMessage());
        return ResponseResultHelper.buildFailResult(e.getMessage());
    }
}
