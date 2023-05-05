package com.kk.code.mall;

import com.kk.code.mall.common.CodeGenerationUtil;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class HkMallApplicationTests {

    @Test
    public void contextLoads() {
        String author = "hk";

        String ipAndPort = "localhost:3306";
        String db = "hk-mall";
        String username = "root";
        String password = "root";
        String outPath = "out";
        //获得当前所在包
        String packageName = getClass().getPackage().getName();


        System.out.println(packageName);
        String[] tables = new String[]{"pms_brand"};
        String param = "?serverTimezone=GMT-8";
        CodeGenerationUtil.exec(
                author,
                "",
                tables,
                ipAndPort,
                db,
                username,
                password,
                outPath,
                packageName,
                param

        );
    }

}
