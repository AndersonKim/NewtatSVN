package com.andersonkim.newstatsvn.controller;

import com.andersonkim.newstatsvn.util.DocumentConverter;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import static com.andersonkim.newstatsvn.util.DocumentConverter.getProjectList;

@Controller
@RequestMapping("/user")
public class UserController {
    @RequestMapping("index")
    public String index(Model model){
        model.addAttribute("files",getProjectList());
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
        return "user/heatmap-month";
    }
}
