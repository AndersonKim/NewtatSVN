package bo;

import java.util.ArrayList;
import java.util.LinkedList;

/**
 * edited by AndersonKim
 * at 2018/10/17
 * 根节点日志
 */
public class LogEntry {
    String revision;
    String author;
    String date;
    ArrayList<LogPath> paths;
    String msg;

    public String getRevision() {
        return revision;
    }

    public void setRevision(String revision) {
        this.revision = revision;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public ArrayList<LogPath> getPaths() {
        return paths;
    }

    public void setPaths(ArrayList<LogPath> paths) {
        this.paths = paths;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }
}
