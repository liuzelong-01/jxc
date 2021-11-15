package org.example.admin.utils;

import org.example.admin.exceptions.ParamsException;

public class AssertUtils {


    public static void isTrue(Boolean flag, String msg){
        if (flag){
            throw new ParamsException(msg);
        }
    }

}
