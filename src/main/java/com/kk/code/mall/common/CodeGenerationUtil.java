package com.kk.code.mall.common;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.DataSourceConfig;
import com.baomidou.mybatisplus.generator.config.GlobalConfig;
import com.baomidou.mybatisplus.generator.config.PackageConfig;
import com.baomidou.mybatisplus.generator.config.StrategyConfig;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;

/**
 * @ProjectName: hk-mall
 * @Package: com.kk.code.mall.common
 * @ClassName: CodeGenerationUtil
 * @Author: HK
 * @Description: 代码生成工具类
 * @Date: 2023/5/5 14:05
 * @Version: 1.0
 */
public class CodeGenerationUtil {

    public static void exec() {
        //作者名称
        String author = "hk";
        //表前缀
        String prefix = "";
        //表
        String[] tables = new String[]{"community"};
        //ip:port
        String ip = "192.168.0.200:3307";
        //数据库名称
        String db = "nspdb";
        //用户名
        String user = "root";
        //密码
        String password = "123456";
        //输出路径 会自动覆盖
        String outPath = "out";
        //包名
        String packageName = "com.vdata.cloud.hyspgxptkf";
        String param = "";

        exec(author, prefix, tables, ip, db, user, password, outPath, packageName, param);
    }

    public static void exec(String author, String prefix, String[] tables, String ipAndPort, String db, String user, String password, String outPath, String packageName, String param) {
        //1、全局配置
        GlobalConfig globalConfig = new GlobalConfig();
        globalConfig.setActiveRecord(true)  //是否支持AR模式
                .setAuthor(author)
                .setOutputDir(outPath)     //生成路径
                .setFileOverride(true)      //是否支持文件覆盖
                .setIdType(IdType.AUTO)     //主键策略
//                .setServiceName("%sService")        //设置生成的service接口名字首字符是否为I
                .setSwagger2(true)  //开启swagger
                .setBaseResultMap(true)             //设置是否支持映射结果集
                .setBaseColumnList(true);//设置是否支持生成数据库列名集合


        //2、数据源配置
        DataSourceConfig dataSourceConfig = new DataSourceConfig();
        String url = "jdbc:mysql://" + ipAndPort + "/" + db + param;
        System.out.println("url:" + url);
        dataSourceConfig.setDbType(DbType.MYSQL)    //设置数据库类型
                .setDriverName("com.mysql.cj.jdbc.Driver")
                .setUrl(url)
                .setUsername(user)
                .setPassword(password);

        //3、策略配置
        StrategyConfig strategyConfig = new StrategyConfig();
        strategyConfig.setCapitalMode(true)         //全局大写命名
                .setNaming(NamingStrategy.underline_to_camel)       //数据库表映射到实体的命名规则，下划线-->驼峰命名
                .setTablePrefix(prefix)//指定表名前缀
                .setEntityLombokModel(true)  // lombok模式创建
                .setInclude(tables);        //生成的表

        //4、包名策略配置
        PackageConfig packageConfig = new PackageConfig();
        packageConfig.setParent(packageName)
                .setMapper("mapper")            //指定Mapper接口包名
                .setService("service")          //指定service层包名
                .setController("controller")    //指定Controller层包名
                .setEntity("entity")              //指定bean包名
                .setXml("mapper");              //xml映射文件放在mapper包下

        //5、整合配置
        AutoGenerator autoGenerator = new AutoGenerator();
        autoGenerator.setGlobalConfig(globalConfig)
                .setDataSource(dataSourceConfig)
                .setStrategy(strategyConfig)
                .setPackageInfo(packageConfig);

        //6、执行
        autoGenerator.execute();
    }
}
