package org.example.admin.utils;

import org.example.admin.exceptions.ParamsException;

public class AssertUtils {


    public static void isTure(Boolean flag,String msg){
        if (flag){
            throw new ParamsException(msg);
        }
    }

}
