package com.andersonkim.newstatsvn.controller;

import com.andersonkim.newstatsvn.bo.Engineer;
import com.andersonkim.newstatsvn.bo.Project;
import com.andersonkim.newstatsvn.util.DocumentConverter;
import com.andersonkim.newstatsvn.util.EchartsWriter;
import com.andersonkim.newstatsvn.util.LogAnalyzer;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import java.io.File;
import java.util.Set;


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
        //取出enginner
        Set enginners=project.getProjectEngineer();
        Engineer enginner=new Engineer();
        enginner.setName(enginnerName);
        for(Object e:enginners){
            if(e.equals(enginner)){
                enginner=(Engineer)e;
            }
        }
        model.addAttribute("enginner",enginner);
        //返回值为空页面就会报错
        String[] config=EchartsWriter.genRadar(enginner);
        model.addAttribute("indicator",config[0]);
        model.addAttribute("addData",config[1]);
        model.addAttribute("modifyData",config[2]);
        return "enginner/index";
    }
}
