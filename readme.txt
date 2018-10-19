newstatSVN
需求分析：
    http://www.cnblogs.com/ThinkFree/p/5441904.html
    1.开发者，统计多少开发者，指定开发者的提交次数，指定开发者提交的AM比例以及数量
    2.提交日志
    3.代码行数
    4.文件数据
    5.文件夹大小
    6.仓库热力图
    7.文件扰动图

增加导出以下项目的数据：
aid
bsdt
netframework
gsyth



技术架构：
    1.数据来源的提供：svnkit以及svn导出的日志文件
    2.数据的处理工具：dom4j处理xml文件
    3.数据的渲染工具：echarts的java配置生成json配置，结合前端的echarts渲染数据

基础流程：
    1.通过数据源获取指定的数据到内存
    2.根据图表所需填充渲染图表整理数据（将数据以及配置写入到前端模板中即可）
