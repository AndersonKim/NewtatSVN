package com.andersonkim.newstatsvn.controller;

import com.andersonkim.newstatsvn.bo.Project;
import com.andersonkim.newstatsvn.util.DocumentConverter;
import com.andersonkim.newstatsvn.util.EchartsWriter;
import com.andersonkim.newstatsvn.util.LogAnalyzer;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.io.File;


/**
 * edited by AndersonKim
 * at 2018/11/13
 */
@Controller
@RequestMapping("/project")
public class ProjectController {

    @RequestMapping("/list")
    @ResponseBody
    public String list(String time) {
        StringBuffer buffer=new StringBuffer();
        buffer.append("this is a response"+time);
        return buffer.toString();
    }

    @RequestMapping("/{projectId}")
    public String projectIndex(Model model, @PathVariable("projectId") String projectId) {
        File file = DocumentConverter.getProjectList().get(Integer.parseInt(projectId));
        Project project = LogAnalyzer.initProject(file.getAbsolutePath());
        model.addAttribute("projectId", projectId);
        model.addAttribute("project", project);
        model.addAttribute("pidata", EchartsWriter.genPi(project.projectLogEntry));
        return "project/index";
    }
}
