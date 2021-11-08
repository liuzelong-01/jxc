package org.example.admin;


import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author 201366530
 */
@SpringBootApplication
@MapperScan("org.example.admin.mapper")
public class JxcAdminApplication {
    public static void main(String[] args) {
        SpringApplication.run(JxcAdminApplication.class,args);
    }
}
