package cn.generate.service;

import cn.generate.entity.DataBase;
import cn.generate.entity.Table;
import cn.generate.utils.DataBaseUtils;
import cn.generate.utils.FileUtils;
import cn.generate.utils.PropertiesUtils;
import freemarker.cache.FileTemplateLoader;
import freemarker.template.Configuration;
import freemarker.template.Template;

import java.io.File;
import java.io.FileWriter;
import java.io.StringReader;
import java.io.StringWriter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author zycstart
 * @create 2020-05-09 21:41
 */
public class GenerateService {

    private String templatePath = "D:\\ideaworkspaces\\code-generate\\模板\\springboot+mybatis模板";//模板路径

    private String outPath;//代码生成路径

    private Configuration cfg;

    public GenerateService(String templatePath, String outPath) throws Exception {
        this.templatePath = templatePath;
        this.outPath = outPath;
        //实例化Configuration对象
//        cfg = new Configuration();
        cfg = new Configuration(Configuration.getVersion());
        //指定模板加载器
        FileTemplateLoader ftl = new FileTemplateLoader(new File(templatePath));
        cfg.setTemplateLoader(ftl);
    }

    /**
     * 代码生成
     * 1.扫描模板路径下的所有模板
     * 2.对每个模板进行文件生成（数据模型）
     */
    public void scanAndGenerator(Map<String, Object> dataModel) throws Exception {
        //1.根据模板路径找到此路径下的所有模板文件
//        List<File> fileList = FileUtils.searchAllFile(new File(templatePath));
        List<File> fileList = FileUtils.searchAllFile(new File("D:\\ideaworkspaces\\code-generate\\模板\\springboot+mybatis模板"));
        //2.对每个模板进行文件生成
        for (File file : fileList) {
            executeGenertor(dataModel, file);
        }
    }

    /**
     * 对模板进行文件生成
     *
     * @param dataModel ： 数据模型
     * @param file      ： 模板文件
     *                  模板文件：c：com.ihrm.system.abc.java
     */
    private void executeGenertor(Map<String, Object> dataModel, File file) throws Exception {
        //1.文件路径处理   (E:\模板\${path1}\${path2}\${path3}\${ClassName}.java)
        //templatePath : E:\模板\
        String templateFileName = file.getAbsolutePath().replace(this.templatePath, "");
        String outFileName = processTemplateString(templateFileName, dataModel);
        //2.读取文件模板
        Template template = cfg.getTemplate(templateFileName);
        template.setOutputEncoding("utf-8");//指定生成文件的字符集编码
        //3.创建文件
        File file1 = FileUtils.mkdir(outPath, outFileName);
        //4.模板处理（文件生成）
        FileWriter fw = new FileWriter(file1);
        template.process(dataModel, fw);
        fw.close();
    }


    public String processTemplateString(String templateString, Map dataModel) throws Exception {
        StringWriter out = new StringWriter();
        Template template = new Template("ts", new StringReader(templateString), cfg);
        template.process(dataModel, out);
        return out.toString();
    }

    public static void main(String[] args) throws Exception {
        String templatePath = "D:\\ideaworkspaces\\code-generate\\模板\\springboot+mybatis模板";
        String outPath = "D:\\生成";
        GenerateService generator = new GenerateService(templatePath, outPath);
//        Map<String, Object> dataModel = new HashMap<>();
//        dataModel.put("username", "张三");
//        generator.scanAndGenerator(dataModel);


        //
        /**
         * 1.准备数据模型
         * 2.调用核心处理类完成代码生成工作
         */
        DataBase db = new DataBase("MYSQL", "localhost", "3306", "study");
        db.setUserName("root");
        db.setPassWord("root");
//        db.getDriver("com.mysql.cj.jdbc.Driver");
        List<Table> tables = DataBaseUtils.getDbInfo(db);
        for (Table table : tables) {
            //对每一个Table对象进行代码生成
            /**
             * 数据模型
             * 调用Generator核心处理类
             */
            Map<String, Object> dataModel1 = getDataModel(table);
//
//            for(Map.Entry<String,Object> entry:dataModel.entrySet()) {
//                System.out.println(entry.getKey() + "--" + entry.getValue());
//            }
//            System.out.println("------------------------");
            generator.scanAndGenerator(dataModel1);
        }
    }

    private static Map<String, Object> getDataModel(Table table) {
        Map<String, Object> dataModel = new HashMap<>();
        //1.自定义配置
        dataModel.putAll(PropertiesUtils.customMap);
        //2.元数据
        dataModel.put("table", table);  //table.name2
        //3.setting
//        dataModel.putAll(this.settings.getSettingMap());
        //4.类型
        dataModel.put("ClassName", table.getName2());
        return dataModel;
    }
}
