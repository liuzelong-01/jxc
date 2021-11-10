package org.example.admin.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

@Controller
public class MainController {

    @RequestMapping("/index")
    public String index(){
        return "index";
    }

    @RequestMapping("/main")
    public String main(){
        return "main";
    }

    @RequestMapping("/welcome")
    public String welcome(){
        return "welcome";
    }



}
