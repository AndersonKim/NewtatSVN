package com.andersonkim.newstatsvn.controller;

import com.andersonkim.newstatsvn.bo.Engineer;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;


/**
 * edited by AndersonKim
 * at 2018/11/20
 */
@Controller
@RequestMapping("enginner")
public class EnginnerController {
    @RequestMapping("/{enginnerName}")
    public String projectIndex(Model model, @PathVariable("enginnerName") String enginnerName) {
        Engineer enginner=new Engineer();
        enginner.setName(enginnerName);
        model.addAttribute("enginner",enginner);
        return "enginner/index";
    }
}
