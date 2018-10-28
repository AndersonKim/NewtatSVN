import bo.Engineer;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

/**
 * edited by AndersonKim
 * at 2018/10/18
 * echarts数据操作
 */
public class EchartsWriter {

    /**
     * edit by AndersonKim
     * @Date：2018/10/22
     * @Description：产生文件分布图的echarts数据
     */
    public static void genRadar(String author){
        Engineer engineer= LogAnalyzer.extractFileByAuthor(author);
        engineer=LogAnalyzer.setEngineerAMType(engineer);
        //获取修改以及增加的文件类型的共有的类型
        Set<String> addFileType= engineer.getAddFileTypeCount().keySet();
        Set<String> modifyFileType= engineer.getModifyFileTypeCount().keySet();
        Set<String> finalFileType=new HashSet<>();
        for (String type:addFileType){
            if(modifyFileType.contains(type)){
                finalFileType.add(type);
            }
        }
        //写入修改以及新增的map中
        HashMap<String,Integer> finalAddMap=new HashMap<>();
        HashMap<String,Integer> finalModifyMap=new HashMap<>();
        for (String type:finalFileType){
            finalAddMap.put(type,engineer.getAddFileTypeCount().get(type));
            finalModifyMap.put(type,engineer.getModifyFileTypeCount().get(type));
        }


        //最终生成数据
        /*
            indicator: [
                { name: 'jpg', max: 100},
                { name: 'css', max: 100},
                { name: 'pdf', max: 100},
                { name: 'java', max: 100},
                { name: 'jsp', max: 100},
                { name: 'png', max: 100},
                { name: 'doc', max: 100},
                { name: 'js', max: 100},
                { name: 'properties', max: 100},
                { name: 'docx', max: 100}
            ]
*/
        int maxIndicator=0;
        for (String type:finalFileType){
            if(finalAddMap.get(type)>maxIndicator){
                maxIndicator=finalAddMap.get(type);
            }
            if(finalModifyMap.get(type)>maxIndicator){
                maxIndicator=finalModifyMap.get(type);
            }
        }
        System.out.println("            indicator: [");
        for (String type:finalFileType){
            System.out.println("{ name: '"+type+"', max: "+maxIndicator+"},");
        }
        System.out.println("            ]");

/*
            data : [
                {
                    value : [43, 10, 20, 35, 50, 10, 20, 35, 50, 10],
                    name : titleAdd
                },
                {
                    value : [43, 10, 20, 35, 50, 10, 20, 35, 50, 10],
                    name : titleModify
                }
            ]
*/
        for (String type:finalFileType){
            System.out.printf(finalAddMap.get(type)+",");
        }
        System.out.println();
        for (String type:finalFileType){
            System.out.printf(finalModifyMap.get(type)+",");
        }
    }

    /**
     * edit by AndersonKim
     * @Date：2018/10/22
     * @Description：产生文件分布图的echarts数据
     */
    public static void genPi(){
        //获取数据
        HashMap<String,Integer> authorCount=LogAnalyzer.getAuthorCount();
        //生成数据
/*
             data:[
                {value:0,name:'caomengying'},
                {value:0,name:'tangcongcong'},
                {value:6,name:'liweijie'},
                {value:12,name:'machanggang'},
                {value:13,name:'yuantao'},
                {value:6,name:'shichenyue'},
                {value:4,name:'lizhuoyuan'},
                {value:49,name:'lizhenjian'}
            ],
 */
        System.out.println("             data:[");
        for (String author:authorCount.keySet()){
            System.out.println("{value:"+authorCount.get(author)+",name:'"+author+"'},");
        }
        System.out.println("            ],");
    }


}
