package org.example.admin;

import org.example.admin.exceptions.ParamsException;
import org.example.admin.model.RespBean;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;



/**
 * @author 201366530
 */
@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(ParamsException.class)
    @ResponseBody
    public RespBean paramsExceptionHandler(ParamsException e){
        return RespBean.error(e.getMsg());
    }

    @ExceptionHandler(AccessDeniedException.class)
    public String accessDeniedException(AccessDeniedException e){
        return "403";
    }


    @ExceptionHandler(Exception.class)
    @ResponseBody
    public RespBean exceptionHandler(Exception e){
        return RespBean.error(e.getMessage());
    }

}
