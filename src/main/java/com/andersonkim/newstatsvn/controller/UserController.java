package com.andersonkim.newstatsvn.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class UserController {
    @RequestMapping("index")
    public String index(){
        return "user/index";
    }
    @RequestMapping("pie")
    public String pie(){
        return "user/pie";
    }
    @RequestMapping("radar")
    public String radar(){
        return "user/radar";
    }
    @RequestMapping("heatmap")
    public String heatmap(){
        return "user/heatmap";
    }
}
