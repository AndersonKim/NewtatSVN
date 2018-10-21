import bo.Engineer;
import bo.LogEntry;
import bo.LogPath;

import java.util.*;

/**
 * edited by AndersonKim
 * at 2018/10/18
 * 日志分析器
 */
public class LogAnalyzer {

    //作者集合
    static Set<String> author;
    //文件类型集合
    static Set<String> filesType;
    //作者提交数集合
    static HashMap<String,Integer> authorCount;

    /**
     * 获取用户对应的提交/修改文件列表
     * @param author
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
     * 通过工程师信息获取工程师所提交的文件类型集合
     * @return
     */
    public static Set<String> getFilesTypeByEngineer(Engineer engineer){
        HashSet<String> files=new HashSet<String>();
        for (String file:engineer.getAddFiles()){
            files.add(file.substring(file.lastIndexOf(".")+1,file.length()));
        }
        for (String file:engineer.getModifyFiles()){
            files.add(file.substring(file.lastIndexOf(".")+1,file.length()));
        }
        return files;
    }
    /**
     * edit by AndersonKim
     * @Date：2018/10/19
     * @Description：数据标准化
     */
    public static ArrayList<LogEntry> convert(ArrayList<LogEntry> data){
        ArrayList<LogEntry> result=new ArrayList<LogEntry>();
        return  result;
    }

    /**
     * edit by AndersonKim
     * @Date：2018/10/19
     * @Description：获取日志中的作者以及提交数量
     */
    public static void main(String args[]){
        ArrayList<LogEntry> data=DocumentConverter.phaseLog();
        author=new HashSet<>();
        authorCount=new HashMap<>();
        for (LogEntry log:data) {
            if(author.add(log.getAuthor())){
                authorCount.put(log.getAuthor(),0);
            }else{
                Integer time = authorCount.get(log.getAuthor());
                time++;
                authorCount.put(log.getAuthor(),time);
            }
        }
    }
}
