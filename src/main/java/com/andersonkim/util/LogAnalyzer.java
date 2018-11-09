package com.andersonkim.util;

import com.andersonkim.bo.Engineer;
import com.andersonkim.bo.LogEntry;
import com.andersonkim.bo.LogPath;
import com.andersonkim.bo.Project;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * edited by AndersonKim
 * at 2018/10/18
 * 日志分析器
 */
public class LogAnalyzer {

    /**
     * 获取用户对应的提交/修改文件列表
     *
     * @param author 工程师姓名
     * @return
     */
    public static Engineer extractFileByAuthor(String author) {
        ArrayList<LogEntry> data = DocumentConverter.phaseLog();

        Engineer result = new Engineer();
        result.setName(author);
        result.setFileAddNo(0);
        result.setFileModifyNo(0);
        result.setAddFiles(new HashSet<String>());
        result.setModifyFiles(new HashSet<String>());
        //去除首次提交导致的文件类型过于全面的问题
        for (LogEntry log : data) {
            if (log.getAuthor().equals(author)) {
                boolean isInit = false;
                ArrayList<LogPath> logPaths = log.getPaths();
                for (LogPath path : logPaths) {
                    //当前的路径值中有添加src目录操作的时候视为初始导入，跳出当前logentry的循环
                    if (path.getAction().equals("A") && path.getKind().equals("dir") && path.getValue().endsWith("/src")) {
                        isInit = true;
                    }
                }
                //统计非首次提交的文件类型
                if (!isInit) {
                    for (LogPath path : logPaths) {
                        //统计用户的文件提交类型的数量
                        if (path.getKind().equals("file")) {
                            switch (path.getAction()) {
                                case "A":
                                    result.getAddFiles().add(path.getFileName());
                                    result.setFileAddNo(result.getFileAddNo() + 1);
                                    break;
                                case "M":
                                    result.getModifyFiles().add(path.getFileName());
                                    result.setFileModifyNo(result.getFileModifyNo() + 1);
                                    break;
                            }
                        }
                    }
                }
            }
        }

        return result;
    }

    /**
     * edit by AndersonKim
     *
     * @Date：2018/10/22
     * @Description：分析工程师所提交以及修改的文件类型并计数
     */
    public static Engineer setEngineerAMType(Engineer engineer) {
        if (engineer.getAddFiles() != null) {
            if (engineer.getModifyFiles() != null) {
                HashMap<String, Integer> modifyFileTypeCount = new HashMap<>();
                HashMap<String, Integer> addFileTypeCount = new HashMap<>();
                for (String file : engineer.getAddFiles()) {
                    String fileType = file.substring(file.lastIndexOf(".") + 1, file.length());
                    if (addFileTypeCount.get(fileType) == null) {
                        addFileTypeCount.put(fileType, 1);
                    } else {
                        Integer num = addFileTypeCount.get(fileType);
                        addFileTypeCount.put(fileType, num + 1);
                    }
                }
                for (String file : engineer.getModifyFiles()) {
                    String fileType = file.substring(file.lastIndexOf(".") + 1, file.length());
                    if (modifyFileTypeCount.get(fileType) == null) {
                        modifyFileTypeCount.put(fileType, 1);
                    } else {
                        Integer num = modifyFileTypeCount.get(fileType);
                        modifyFileTypeCount.put(fileType, num + 1);
                    }
                }
                engineer.setAddFileTypeCount(addFileTypeCount);
                engineer.setModifyFileTypeCount(modifyFileTypeCount);
            }
        }

        return engineer;
    }

    /**
     * 通过工程师信息获取工程师所提交的文件类型集合
     *
     * @return
     */
    public static Engineer getFilesTypeByEngineer(Engineer engineer) {
        HashSet<String> files = new HashSet<String>();
        for (String file : engineer.getAddFiles()) {
            files.add(file.substring(file.lastIndexOf(".") + 1, file.length()));
        }
        for (String file : engineer.getModifyFiles()) {
            files.add(file.substring(file.lastIndexOf(".") + 1, file.length()));
        }
        engineer.setAllFileType(files);
        return engineer;
    }

    /**
     * edit by AndersonKim
     *
     * @Date：2018/10/19
     * @Description：数据标准化：获取工程师提交的标准时间信息
     */
    public static ArrayList<LogEntry> standardizationDate(ArrayList<LogEntry> data) {
        ArrayList<LogEntry> result = data;
        //2017-04-26T10:24:23.780597Z
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        for (LogEntry log : data) {
            String time = log.getDate();
            time = time.substring(0, time.lastIndexOf("."));
            time = time.replace("T", " ");
            try {
                log.setTime(dateFormat.parse(time));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return result;
    }

    /**
     * edit by AndersonKim
     *
     * @Date：2018/10/19
     * @Description：获取日志中的作者以及对应的提交数量
     */
    public static HashMap<String, Integer> getAuthorCount() {
        ArrayList<LogEntry> data = DocumentConverter.phaseLog();
        HashSet author = new HashSet<>();
        HashMap<String, Integer> authorCount = new HashMap<>();
        for (LogEntry log : data) {
            if (author.add(log.getAuthor())) {
                authorCount.put(log.getAuthor(), 0);
            } else {
                Integer time = authorCount.get(log.getAuthor());
                time++;
                authorCount.put(log.getAuthor(), time);
            }
        }
        return authorCount;

    }

    /**
     * edit by AndersonKim
     *
     * @Date：2018/10/23
     * @Description：通过作者名称获取用户的日期（精确到时分秒）提交计数
     */
    public static Engineer getDashBoardByAuther_HMS(Engineer engineer) {
        ArrayList<LogEntry> data = DocumentConverter.phaseLog();
        data = LogAnalyzer.standardizationDate(data);
        HashMap<Date, Integer> dashBord = new HashMap<>();
        for (LogEntry log : data) {
            if (log.getAuthor().equals(engineer.getName())) {
                if (dashBord.get(log.getTime()) != null) {
                    Integer times = dashBord.get(log.getTime());
                    times = times + 1;
                    dashBord.put(log.getTime(), times);
                } else {
                    dashBord.put(log.getTime(), 1);
                }
            }
        }
        engineer.setTimeTabe(dashBord);
        return engineer;
    }

    /**
     * edit by AndersonKim
     *
     * @Date：2018/10/23
     * @Description：通过作者名称获取用户的日期（精确到年月日）提交计数
     */
    public static Engineer getDashBoardByAuther_YMD(Engineer engineer) {
        ArrayList<LogEntry> data = DocumentConverter.phaseLog();
        data = LogAnalyzer.standardizationDate(data);
        HashMap<String, Integer> dashBord = new HashMap<>();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        for (LogEntry log : data) {
            if (log.getAuthor().equals(engineer.getName())) {
                String dateStr = simpleDateFormat.format(log.getTime());
                if (dashBord.get(dateStr) != null) {
                    Integer times = dashBord.get(dateStr);
                    times = times + 1;
                    dashBord.put(dateStr, times);
                } else {
                    dashBord.put(dateStr, 1);
                }
            }
        }
        engineer.setDashBord(dashBord);
        return engineer;
    }

    /**
     * edit by AndersonKim
     *
     * @Date：2018/10/23
     * @Description：获取特殊的版本升级的版本号，版本升级类型
     */
    public static HashMap<String, String> getSpecialVersionTag() {
        String SQL_UPGRADE = "sql";
        String CORE_UPGRADE = "esw";
        String BOTH_UPGRADE = "sql|esw";
        String[] SQL_WORD = {"sql", "数据库", "表结构"};
        String[] CORE_WORD = {"esw", "框架", "单点"};
        HashMap<String, String> batchTag = new HashMap<>();
        ArrayList<LogEntry> data = DocumentConverter.phaseLog();
        for (LogEntry log : data) {
            for (String sensWord : SQL_WORD) {
                if (log.getMsg().contains(sensWord)) {
                    batchTag.put(log.getRevision(), SQL_UPGRADE);
                }
            }
            for (String senWord : CORE_WORD) {
                if (log.getMsg().contains(senWord)) {
                    if (batchTag.get(log.getRevision()) != null) {
                        batchTag.put(log.getRevision(), BOTH_UPGRADE);
                    } else {
                        batchTag.put(log.getRevision(), CORE_UPGRADE);
                    }
                }
            }

        }
        return batchTag;
    }

    /**
     * 获取指定文件的修改历史纪录
     *
     * @param fileFullPath
     * @return 返回该文件历史修改记录以及对应的时间作者和备注
     */
    public static List getFileHistory(String fileFullPath) {
        ArrayList<HashMap<String, String>> fileModifyHistory = new ArrayList<>();
        ArrayList<LogEntry> data = DocumentConverter.phaseLog();
        data = LogAnalyzer.standardizationDate(data);
        for (LogEntry logEntry : data) {
            HashMap<String, String> fileHis = new HashMap<>();
            LogPath logPath = new LogPath();
            logPath.setValue(fileFullPath);
            logPath.setFileName(fileFullPath.substring(fileFullPath.lastIndexOf("/") + 1, fileFullPath.length()));
            logPath.setKind("file");
            logPath.setAction("M");
            if (logEntry.getPaths().contains(logPath)) {
                fileHis.put("revision", logEntry.getRevision());
                fileHis.put("msg", logEntry.getMsg());
                fileHis.put("author", logEntry.getAuthor());
                fileHis.put("date", logEntry.getDate());
                fileModifyHistory.add(fileHis);
            }
        }

        return fileModifyHistory;

    }



    /**
     * edit by AndersonKim
     * @Date：2018/11/8
     * @Description：初始化指定的项目相关数据
     */
    public static Project initProject(){
        ArrayList<LogEntry> data = DocumentConverter.phaseLog();
        data = LogAnalyzer.standardizationDate(data);

        HashSet<Engineer> engineerArrayList=new HashSet<>();
        Set<String> enginners= getAuthorCount().keySet();
        for (String enginner:enginners) {
            Engineer e=extractFileByAuthor(enginner);
            e=setEngineerAMType(e);
            e=getFilesTypeByEngineer(e);
            e=getDashBoardByAuther_YMD(e);
            engineerArrayList.add(e);
        }

        Project project=new Project();
        project.setProjectLogEntry(data);
        project.setProjectEngineer(engineerArrayList);
        return project;
    }

}
