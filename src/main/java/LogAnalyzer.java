import bo.LogEntry;

import java.util.*;

/**
 * edited by AndersonKim
 * at 2018/10/18
 * 日志分析器
 */
public class LogAnalyzer {


    static Set<String> author;

    static HashMap<String,Integer> authorCount;

    /**
     * edit by AndersonKim
     * @Date：2018/10/19
     * @Description：数据标准化
     */
    public static void convert(){

    }

    /**
     * edit by AndersonKim
     * @Date：2018/10/19
     * @Description：获取日志中的作者以及提交数量
     */
    public static void main(String args[]){
        //count writer
        ArrayList<LogEntry> data=DocumentConverter.phaseLog();
        author=new HashSet<>();
        authorCount=new HashMap<>();
        for (LogEntry log:data) {
            if(author.add(log.getAuthor())){
                authorCount.put(log.getAuthor(),0);
                //System.out.println("author："+log.getAuthor());
            }else{
                Integer time = authorCount.get(log.getAuthor());
                time++;
                authorCount.put(log.getAuthor(),time);
            }
        }

        System.out.println("log count:"+data.size());


        for (String str : author) {
            System.out.print("{value:"+authorCount.get(str)+",name:'"+str+"'},");
            System.out.println();
        }



    }
}
