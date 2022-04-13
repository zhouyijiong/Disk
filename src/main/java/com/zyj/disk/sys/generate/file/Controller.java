package com.zyj.disk.sys.generate.file;

import com.zyj.disk.sys.generate.FieldInfo;
import java.io.BufferedOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.List;

/**
 * @Author: ZYJ
 * @Date: 2022/4/13
 * @Remark: 生成控制器类
 */
public class Controller extends FileType{
    public Controller(String name,List<FieldInfo> fieldInfos){
        super(name,"Controller");
        this.fieldInfos = fieldInfos;
    }

    @Override
    void createHead(BufferedOutputStream bos,String packageName)throws IOException{
        String lowName = sourceName.toLowerCase();
        String oneToUpName = oneStrToUp(sourceName);
        bos.write(packages(packageName + "controller." + lowName));
        bos.write(imports(packageName + "service." + lowName + "." + oneToUpName + "Service;"));
        bos.write(imports("lombok.RequiredArgsConstructor;"));
        bos.write(imports("org.springframework.web.bind.annotation.RequestMapping;"));
        bos.write(imports("org.springframework.web.bind.annotation.RestController;"));
        bos.write("\n".getBytes(StandardCharsets.UTF_8));
        bos.write(annotations("RestController"));
        bos.write(annotations("RequestMapping(\"/" + lowName + "\")"));
        bos.write(annotations("RequiredArgsConstructor"));
    }

    @Override
    void createBody(BufferedOutputStream bos)throws IOException{
        String oneToUpName = oneStrToUp(sourceName);
        String service = sourceName.toLowerCase() + "Service";
        bos.write(("\npublic class "+ className +"{").getBytes(StandardCharsets.UTF_8));
        bos.write(("\n\tprivate final " + oneToUpName + "Service " + service + ";").getBytes(StandardCharsets.UTF_8));
        bos.write("\n}".getBytes(StandardCharsets.UTF_8));
    }
}