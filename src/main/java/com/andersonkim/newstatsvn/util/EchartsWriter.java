package com.andersonkim.newstatsvn.util;

import com.andersonkim.newstatsvn.bo.Engineer;
import com.andersonkim.newstatsvn.bo.LogEntry;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * edited by AndersonKim
 * at 2018/10/18
 * echarts数据操作
 */
public class EchartsWriter {

    /**
     * edit by AndersonKim
     *
     * @Date：2018/10/22
     * @Description：产生文件分布图的echarts数据
     */
    public static String[] genRadar(String author, List<LogEntry> data) {
        Engineer engineer = LogAnalyzer.extractFileByAuthor(author,data);
        engineer = LogAnalyzer.setEngineerAMType(engineer);
        //获取修改以及增加的文件类型的共有的类型
        Set<String> addFileType = engineer.getAddFileTypeCount().keySet();
        Set<String> modifyFileType = engineer.getModifyFileTypeCount().keySet();
        Set<String> finalFileType = new HashSet<>();
        for (String type : addFileType) {
            if (modifyFileType.contains(type)) {
                finalFileType.add(type);
            }
        }
        //写入修改以及新增的map中
        HashMap<String, Integer> finalAddMap = new HashMap<>();
        HashMap<String, Integer> finalModifyMap = new HashMap<>();
        for (String type : finalFileType) {
            finalAddMap.put(type, engineer.getAddFileTypeCount().get(type));
            finalModifyMap.put(type, engineer.getModifyFileTypeCount().get(type));
        }

        String[] result=new String[3];
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
        int maxIndicator = 0;
        for (String type : finalFileType) {
            if (finalAddMap.get(type) > maxIndicator) {
                maxIndicator = finalAddMap.get(type);
            }
            if (finalModifyMap.get(type) > maxIndicator) {
                maxIndicator = finalModifyMap.get(type);
            }
        }
        StringBuffer buffer=new StringBuffer();

        //System.out.println("            indicator: [");
        for (String type : finalFileType) {
            buffer.append("{ name: '" + type + "', max: " + maxIndicator + "},");
            //System.out.println("{ name: '" + type + "', max: " + maxIndicator + "},");
        }
        result[0]=buffer.toString();
        System.out.println(result[0]);

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
        StringBuffer add=new StringBuffer();
        for (String type : finalFileType) {
            add.append(finalAddMap.get(type) + ",");
            //System.out.printf(finalAddMap.get(type) + ",");
        }
        result[1]=add.toString();
        System.out.println(result[1]);
        StringBuffer modify=new StringBuffer();

        for (String type : finalFileType) {
            modify.append(finalModifyMap.get(type) + ",");
            //System.out.printf(finalModifyMap.get(type) + ",");
        }
        result[2]=modify.toString();
        System.out.println(result[2]);

        return result;
    }

    public static String[] genRadar(Engineer inEngineer) {
        Engineer engineer = inEngineer;
        engineer = LogAnalyzer.setEngineerAMType(engineer);
        //获取修改以及增加的文件类型的共有的类型
        Set<String> addFileType = engineer.getAddFileTypeCount().keySet();
        Set<String> modifyFileType = engineer.getModifyFileTypeCount().keySet();
        Set<String> finalFileType = new HashSet<>();
        for (String type : addFileType) {
            if (modifyFileType.contains(type)) {
                finalFileType.add(type);
            }
        }
        //写入修改以及新增的map中
        HashMap<String, Integer> finalAddMap = new HashMap<>();
        HashMap<String, Integer> finalModifyMap = new HashMap<>();
        for (String type : finalFileType) {
            finalAddMap.put(type, engineer.getAddFileTypeCount().get(type));
            finalModifyMap.put(type, engineer.getModifyFileTypeCount().get(type));
        }

        String[] result=new String[3];
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
        int maxIndicator = 0;
        for (String type : finalFileType) {
            if (finalAddMap.get(type) > maxIndicator) {
                maxIndicator = finalAddMap.get(type);
            }
            if (finalModifyMap.get(type) > maxIndicator) {
                maxIndicator = finalModifyMap.get(type);
            }
        }
        StringBuffer buffer=new StringBuffer();

        //System.out.println("            indicator: [");
        for (String type : finalFileType) {
            buffer.append("{ name: '" + type + "', max: " + maxIndicator + "},");
            //System.out.println("{ name: '" + type + "', max: " + maxIndicator + "},");
        }
        result[0]=buffer.toString();
        System.out.println(result[0]);

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
        StringBuffer add=new StringBuffer();
        for (String type : finalFileType) {
            add.append(finalAddMap.get(type) + ",");
            //System.out.printf(finalAddMap.get(type) + ",");
        }
        //todo 有可能没有,导致空指针
        add.replace(add.lastIndexOf(","),add.length(),"");
        result[1]=add.toString();
        System.out.println(result[1]);
        StringBuffer modify=new StringBuffer();

        for (String type : finalFileType) {
            modify.append(finalModifyMap.get(type) + ",");
            //System.out.printf(finalModifyMap.get(type) + ",");
        }
        modify.replace(modify.lastIndexOf(","),modify.length(),"");
        result[2]=modify.toString();
        System.out.println(result[2]);

        return result;
    }
    /**
     * edit by AndersonKim
     *
     * @Date：2018/10/22
     * @Description：产生文件分布图的echarts数据
     */
    public static String genPi(List<LogEntry> data) {
        //获取数据
        HashMap<String, Integer> authorCount = LogAnalyzer.getAuthorCount(data);
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
        StringBuffer buffer=new StringBuffer();
        //buffer.append("[");
        //System.out.println("             data:[");
        for (String author : authorCount.keySet()) {
            buffer.append("{value:" + authorCount.get(author) + ",name:'" + author + "'},");
            //System.out.println("{value:" + authorCount.get(author) + ",name:'" + author + "'},");
        }
        buffer.replace(buffer.lastIndexOf(","),buffer.length(),"");
        //buffer.append("]");
        //System.out.println("            ],");
        return buffer.toString();
    }

    public static void genHeatMap_Month(String author, String year,ArrayList<LogEntry> data) {
        Engineer engineer = new Engineer();
        engineer.setName(author);
        engineer = LogAnalyzer.getDashBoardByAuther_YMD(engineer,data);
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
        StringBuffer cache = new StringBuffer();
        cache.append("    var data = [");
        for (String str : engineer.getDashBord().keySet()) {
            //date类使用的时候注意初始化需要year-1900，month-1
            //Date s=new Date(year-1900,1-1,1);
            //Date e=new Date(year-1900,12-1,31);
            if (str.contains(year)) {
                cache.append("['" + str + "'," + engineer.getDashBord().get(str) + "],");
            }
        }
        cache.append("    ];");
        System.out.println(cache.toString());

    }

}
