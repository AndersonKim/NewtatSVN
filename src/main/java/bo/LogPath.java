package bo;

/**
 * edited by AndersonKim
 * at 2018/10/17
 * 文件路径
 */
public class LogPath {
    //文件修改类型
    String action;
    //文件还是文件夹
    String kind;
    //全路径
    String value;
    //文件名称
    String fileName;

    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getKind() {
        return kind;
    }

    public void setKind(String kind) {
        this.kind = kind;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
}
