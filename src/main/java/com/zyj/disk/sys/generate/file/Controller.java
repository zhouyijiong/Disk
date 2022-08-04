package com.zyj.disk.sys.generate.file;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

/**
 * 生成控制器类
 */
public class Controller extends FileType {
    public Controller() {
        super("Controller");
    }

    @Override
    void createHead(BufferedOutputStream bos, String packageName) throws IOException {
        String lowName = sourceName.toLowerCase();
        String oneToUpName = oneStrToUp(sourceName);
        bos.write(packages(packageName + "controller." + lowName));
        bos.write(imports(packageName + "service." + lowName + "." + oneToUpName + "Service;"));
        bos.write(imports("lombok.RequiredArgsConstructor;"));
        bos.write(imports("org.springframework.web.bind.annotation.RequestMapping;"));
        bos.write(imports("org.springframework.web.bind.annotation.RestController;"));
        bos.write("\n".getBytes(StandardCharsets.UTF_8));
        bos.write(annotations("RestController"));
        String oneToLowName = sourceName.substring(0, 1).toLowerCase() + sourceName.substring(1);
        bos.write(annotations("RequestMapping(\"/" + oneToLowName + "\")"));
        bos.write(annotations("RequiredArgsConstructor"));
    }

    @Override
    void createBody(BufferedOutputStream bos) throws IOException {
        String oneToUpName = oneStrToUp(sourceName);
        String service = sourceName.toLowerCase() + "Service";
        bos.write(("\npublic class " + className + "{").getBytes(StandardCharsets.UTF_8));
        bos.write(("\n\tprivate final " + oneToUpName + "Service " + service + ";").getBytes(StandardCharsets.UTF_8));
        bos.write("\n}".getBytes(StandardCharsets.UTF_8));
    }
}