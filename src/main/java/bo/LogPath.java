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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        LogPath logPath = (LogPath) o;

        if (!action.equals(logPath.action))
            return false;
        if (!kind.equals(logPath.kind))
            return false;
        if (!value.equals(logPath.value))
            return false;
        return fileName.equals(logPath.fileName);
    }

    @Override
    public int hashCode() {
        int result = action.hashCode();
        result = 31 * result + kind.hashCode();
        result = 31 * result + fileName.hashCode();
        result = 31 * result + value.hashCode();
        return result;
    }

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
