package com.andersonkim.newstatsvn.controller;

import com.andersonkim.newstatsvn.bo.Engineer;
import com.andersonkim.newstatsvn.bo.Project;
import com.andersonkim.newstatsvn.util.DocumentConverter;
import com.andersonkim.newstatsvn.util.LogAnalyzer;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.File;


/**
 * edited by AndersonKim
 * at 2018/11/20
 */
@Controller
@RequestMapping("enginner")
public class EnginnerController {
    @RequestMapping("/{enginnerName}/{projectId}")
    public String projectIndex(Model model, @PathVariable("enginnerName") String enginnerName,@PathVariable("projectId") String projectId) {
        File file = DocumentConverter.getProjectList().get(Integer.parseInt(projectId));
        Project project = LogAnalyzer.initProject(file.getAbsolutePath());

        return "enginner/index";
    }
}
