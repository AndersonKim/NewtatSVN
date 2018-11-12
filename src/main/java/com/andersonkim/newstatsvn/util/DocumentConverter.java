package com.andersonkim.newstatsvn.util;

import com.andersonkim.newstatsvn.bo.LogEntry;
import com.andersonkim.newstatsvn.bo.LogPath;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import java.io.File;
import java.util.ArrayList;
import java.util.Iterator;


/**
 * edited by AndersonKim
 * at 2018/10/17
 * 读取日志文件并封装入BO中
 */
public class DocumentConverter {
    /**
     * edit by AndersonKim
     *
     * @Date：2018/11/12
     * @Description：获取指定路径下的log文件
     */
    public static ArrayList<LogEntry> phaseLog() {
        try {
            File file = new File("Y:\\IDEAProject\\newstatSVN\\src\\main\\resources\\log\\bsdt.xml");
            SAXReader reader = new SAXReader();

            Document doc = reader.read(file);

            Element rootElement = doc.getRootElement();

            Element fooElement;

            ArrayList<LogEntry> logEntries = new ArrayList<>();

            for (Iterator i = rootElement.elementIterator("logentry"); i.hasNext(); ) {
                fooElement = (Element) i.next();
                LogEntry logEntry = new LogEntry();
                logEntry.setAuthor(fooElement.elementText("author"));
                logEntry.setDate(fooElement.elementText("date"));
                logEntry.setMsg(fooElement.elementText("msg"));
                logEntry.setRevision(fooElement.attribute("revision").getValue());

                ArrayList<LogPath> logPaths = new ArrayList<>();
                Element pathsElement = fooElement.element("paths");
                for (Iterator pathIt = pathsElement.elementIterator("path"); pathIt.hasNext(); ) {
                    Element pathElement = (Element) pathIt.next();
                    LogPath logPath = new LogPath();
                    String path = pathElement.getStringValue();
                    logPath.setValue(path);
                    logPath.setFileName(path.substring(
                            path.lastIndexOf("/") + 1,
                            path.length()
                    ));
                    logPath.setAction(pathElement.attribute("action").getValue());
                    logPath.setKind(pathElement.attribute("kind").getValue());


                    logPaths.add(logPath);

                }
                logEntry.setPaths(logPaths);


                logEntries.add(logEntry);
            }
            return logEntries;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * edit by AndersonKim
     *
     * @Date：2018/11/12
     * @Description：读取log文件夹下得所有日志文件
     */
    public static ArrayList<File> getProjectList() {
        //todo 配置springboot的读取信息的log
        ArrayList<File> files = new ArrayList<>();
        String classpath = Thread.currentThread().getContextClassLoader().getResource(".").getPath();
        File f = new File(classpath + "log/");
        if (f.isDirectory()) {
            File[] fs = f.listFiles();
            for (File file : fs) {
                files.add(file.getAbsoluteFile());
            }
        }
        return files;
    }

}
