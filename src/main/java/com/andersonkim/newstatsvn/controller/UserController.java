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
        return "prototype/pie";
    }
    @RequestMapping("radar")
    public String radar(){
        return "prototype/radar";
    }
    @RequestMapping("heatmap")
    public String heatmap(){
        return "prototype/heatmap-month";
    }
}
