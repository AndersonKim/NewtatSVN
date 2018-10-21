package bo;

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
}
