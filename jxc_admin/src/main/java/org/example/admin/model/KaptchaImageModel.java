package org.example.admin.model;

import java.time.LocalDateTime;


public class KaptchaImageModel {

    private String code;

    private LocalDateTime expireTime;


    public KaptchaImageModel(String code, int expireAfterSeconds){
        this.code = code;
        this.expireTime = LocalDateTime.now().plusSeconds(expireAfterSeconds);
    }

    public String getCode() {
        return code;
    }

    /**
     * 验证码是否失效
     * @return
     */
    public boolean isExpired(){
        return  LocalDateTime.now().isAfter(expireTime);
    }
}
