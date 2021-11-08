package org.example.admin.utils;

public class StringUtils {

    /**
     * 判断字符串是否为空
     * @param str
     * @return
     */
    public static boolean isEmpty(String str){
        if (str==null||"".equals(str.trim())){
            return true;
        }else {
            return false;
        }

    }


}
