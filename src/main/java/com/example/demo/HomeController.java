package com.example.demo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {
    @RequestMapping("/")
    //when I try to go to this page authentication will be required to be meet first!
    //securityConfiguration is then called
    public String index(){

        return "index";
    }

    @RequestMapping("/login")
    public String login(){
        return "login";
    }

}
