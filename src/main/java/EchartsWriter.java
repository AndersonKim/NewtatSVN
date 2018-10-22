import bo.Engineer;

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
    public static void genRadar(){
        Engineer yuantao= LogAnalyzer.extractFileByAuthor("yuantao");
        yuantao=LogAnalyzer.setEngineerAMType(yuantao);

    }


}
