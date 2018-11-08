package bo;

import java.util.HashSet;
import java.util.List;

/**
 * edited by AndersonKim
 * at 2018/11/8
 * 项目最高级对象
 */
public class Project {
    //项目名称
    public String projectName;

    //该项目的日志
    public List<LogEntry> projectLogEntry;

    //该项目参与的工程师
    public HashSet<Engineer> projectEngineer;

    public String getProjectName() {
        return projectName;
    }

    public void setProjectName(String projectName) {
        this.projectName = projectName;
    }

    public List<LogEntry> getProjectLogEntry() {
        return projectLogEntry;
    }

    public void setProjectLogEntry(List<LogEntry> projectLogEntry) {
        this.projectLogEntry = projectLogEntry;
    }

    public HashSet<Engineer> getProjectEngineer() {
        return projectEngineer;
    }

    public void setProjectEngineer(HashSet<Engineer> projectEngineer) {
        this.projectEngineer = projectEngineer;
    }
}
