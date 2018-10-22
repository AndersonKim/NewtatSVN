import bo.Engineer;
import bo.LogEntry;
import bo.LogPath;

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
     * @param author 工程师姓名
     * @return
     */
    public  static Engineer extractFileByAuthor(String author){
        ArrayList<LogEntry> data=DocumentConverter.phaseLog();

        Engineer result=new Engineer();
        result.setName(author);
        result.setFileAddNo(0);
        result.setFileModifyNo(0);
        result.setAddFiles(new HashSet<String>());
        result.setModifyFiles(new HashSet<String>());

        for (LogEntry log:data){
            if(log.getAuthor().equals(author)){
                ArrayList<LogPath> logPaths = log.getPaths();
                for(LogPath path:logPaths){
                    if(path.getKind().equals("file")){
                        switch (path.getAction()){
                            case "A":
                                result.getAddFiles().add(path.getFileName());
                                result.setFileAddNo(result.getFileAddNo()+1);
                                break;
                            case "M":
                                result.getModifyFiles().add(path.getFileName());
                                result.setFileModifyNo(result.getFileModifyNo()+1);
                                break;
                        }
                    }
                }
            }
        }

        return result;
    }

/**
 * edit by AndersonKim
 * @Date：2018/10/22
 * @Description：分析工程师所提交以及修改的文件类型并计数
 */
    public static Engineer setEngineerAMType(Engineer engineer){
        if (engineer.getAddFiles()!=null){
            if(engineer.getModifyFiles()!=null){
                HashMap<String,Integer> modifyFileTypeCount=new HashMap<>();
                HashMap<String,Integer> addFileTypeCount=new HashMap<>();
                for (String file:engineer.getAddFiles()){
                    String fileType=file.substring(file.lastIndexOf(".")+1,file.length());
                    if(addFileTypeCount.get(fileType)==null){
                        addFileTypeCount.put(fileType,1);
                    }else{
                        Integer num=addFileTypeCount.get(fileType);
                        addFileTypeCount.put(fileType,num+1);
                    }
                }
                for (String file:engineer.getModifyFiles()){
                    String fileType=file.substring(file.lastIndexOf(".")+1,file.length());
                    if(modifyFileTypeCount.get(fileType)==null){
                        modifyFileTypeCount.put(fileType,1);
                    }else{
                        Integer num=modifyFileTypeCount.get(fileType);
                        modifyFileTypeCount.put(fileType,num+1);
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
     * @return
     */
    public static Engineer getFilesTypeByEngineer(Engineer engineer){
        HashSet<String> files=new HashSet<String>();
        for (String file:engineer.getAddFiles()){
            files.add(file.substring(file.lastIndexOf(".")+1,file.length()));
        }
        for (String file:engineer.getModifyFiles()){
            files.add(file.substring(file.lastIndexOf(".")+1,file.length()));
        }
        engineer.setAllFileType(files);
        return engineer;
    }
    /**
     * edit by AndersonKim
     * @Date：2018/10/19
     * @Description：数据标准化：获取工程师提交的标准时间信息
     */
    public static ArrayList<LogEntry> standardizationDate(ArrayList<LogEntry> data){
        ArrayList<LogEntry> result=data;
        //2017-04-26T10:24:23.780597Z
        SimpleDateFormat dateFormat=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        for (LogEntry log: data) {
            String time=log.getDate();
            time=time.substring(0,time.lastIndexOf("."));
            time=time.replace("T"," ");
            try {
                log.setTime(dateFormat.parse(time));
            } catch (ParseException e) {
                e.printStackTrace();
            }
        }
        return  result;
    }

    /**
     * edit by AndersonKim
     * @Date：2018/10/19
     * @Description：获取日志中的作者以及对应的提交数量
     */
    public static HashMap<String, Integer> getAuthorCount(){
        ArrayList<LogEntry> data=DocumentConverter.phaseLog();
        HashSet author=new HashSet<>();
        HashMap<String,Integer> authorCount=new HashMap<>();
        for (LogEntry log:data) {
            if(author.add(log.getAuthor())){
                authorCount.put(log.getAuthor(),0);
            }else{
                Integer time = authorCount.get(log.getAuthor());
                time++;
                authorCount.put(log.getAuthor(),time);
            }
        }
        return authorCount;

    }
}
