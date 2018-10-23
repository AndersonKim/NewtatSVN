package bo;

import java.util.Date;
import java.util.HashMap;
import java.util.Set;

/**
 * edited by AndersonKim
 * at 2018/10/19
 * 工程师
 */
public class Engineer {

    String name;
    //添加的文件数量
    Integer fileAddNo;
    //修改的文件数量
    Integer fileModifyNo;
    //添加的文件集合
    Set<String> addFiles;
    //修改的文件集合
    Set<String> modifyFiles;
    //开发者类别
    String devType;
    //添加文件的类型对应的数量
    HashMap<String,Integer> addFileTypeCount;
    //修改文件的类型对应的数量
    HashMap<String,Integer> modifyFileTypeCount;
    //开发者接触的所有文件类型
    Set<String> allFileType;
    //开发者提交时间统计表<提交时间，次数>
    HashMap<Date,Integer> timeTabe;

    public HashMap<Date, Integer> getTimeTabe() {
        return timeTabe;
    }

    public void setTimeTabe(HashMap<Date, Integer> timeTabe) {
        this.timeTabe = timeTabe;
    }

    public Set<String> getAllFileType() {
        return allFileType;
    }

    public void setAllFileType(Set<String> allFileType) {
        this.allFileType = allFileType;
    }

    public String getDevType() {
        return devType;
    }

    public void setDevType(String devType) {
        this.devType = devType;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getFileAddNo() {
        return fileAddNo;
    }

    public void setFileAddNo(Integer fileAddNo) {
        this.fileAddNo = fileAddNo;
    }

    public Integer getFileModifyNo() {
        return fileModifyNo;
    }

    public void setFileModifyNo(Integer fileModifyNo) {
        this.fileModifyNo = fileModifyNo;
    }

    public Set<String> getAddFiles() {
        return addFiles;
    }

    public void setAddFiles(Set<String> addFiles) {
        this.addFiles = addFiles;
    }

    public Set<String> getModifyFiles() {
        return modifyFiles;
    }

    public void setModifyFiles(Set<String> modifyFiles) {
        this.modifyFiles = modifyFiles;
    }

    public HashMap<String, Integer> getAddFileTypeCount() {
        return addFileTypeCount;
    }

    public void setAddFileTypeCount(HashMap<String, Integer> addFileTypeCount) {
        this.addFileTypeCount = addFileTypeCount;
    }

    public HashMap<String, Integer> getModifyFileTypeCount() {
        return modifyFileTypeCount;
    }

    public void setModifyFileTypeCount(HashMap<String, Integer> modifyFileTypeCount) {
        this.modifyFileTypeCount = modifyFileTypeCount;
    }
}
