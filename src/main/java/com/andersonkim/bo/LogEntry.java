package com.andersonkim.bo;

import java.util.ArrayList;
import java.util.Date;

/**
 * edited by AndersonKim
 * at 2018/10/17
 * 根节点日志
 */
public class LogEntry {
    //版本号
    String revision;
    //作者
    String author;
    //日期str
    String date;
    //日期date
    Date time;
    //文件路径
    ArrayList<LogPath> paths;
    //提交信息
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

    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }
}
