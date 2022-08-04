package com.zyj.disk.sys.generate.file;

import java.io.BufferedOutputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

import com.zyj.disk.sys.generate.FieldInfo;

/**
 * 生成实体类
 */
public final class Entity extends FileType {
    StringBuilder param;
    StringBuilder body;

    public Entity() {
        super("Entity");
        int capacity = fieldInfos.size() << 3;
        this.param = new StringBuilder(capacity);
        this.body = new StringBuilder(capacity << 1);
    }

    @Override
    void createHead(BufferedOutputStream bos, String packageName) throws IOException {
        String basePath = packageName + "entity.";
        bos.write(packages(basePath + sourceName.toLowerCase()));
        bos.write(imports("lombok.EqualsAndHashCode;"));
        bos.write(imports("lombok.Getter;"));
        for (FieldInfo item : fieldInfos) {
            if ("BigDecimal".equals(item.getClassType())) {
                bos.write(imports("java.math.BigDecimal;"));
                break;
            }
        }
        bos.write("\n".getBytes(StandardCharsets.UTF_8));
        bos.write(annotations("Getter"));
        bos.write(annotations("EqualsAndHashCode(callSuper = false)"));
    }

    @Override
    void createBody(BufferedOutputStream bos) throws IOException {
        bos.write(("\npublic final class " + className + "{").getBytes(StandardCharsets.UTF_8));
        body.append("\n\t\treturn noArgs()");
        FieldInfo item = fieldInfos.get(0);
        String key = item.getKey();
        Object value = item.getValue();
        String type = item.getClassType();
        param.append(getMethod("public", className, key, type + " val", "\n\t\t" + key + " = val;\n\t\treturn this;\n\t"));
        field(bos, key, type);
        if (value != null)
            body.append("\n\t\t\t").append(".").append(key).append("(").append(getFormatValue(type, value)).append(")");
        for (int i = 1; i < fieldInfos.size(); i++) {
            item = fieldInfos.get(i);
            key = item.getKey();
            value = item.getValue();
            type = item.getClassType();
            field(bos, key, type);
            if (value != null)
                body.append("\n\t\t\t").append(".").append(key).append("(").append(getFormatValue(type, value)).append(")");
            param.append(getMethod("public", className, key, type + " val", "\n\t\t" + key + " = val;\n\t\treturn this;\n\t"));
        }
        bos.write("\n".getBytes(StandardCharsets.UTF_8));
        method(bos, className, "noArgs", "\n\t\treturn new " + className + "();\n\t");
        method(bos, className, "defaultArgs", body.append(";\n\t").toString());
        bos.write(param.toString().getBytes(StandardCharsets.UTF_8));
        bos.write("}".getBytes(StandardCharsets.UTF_8));
    }

    private void field(BufferedOutputStream bos, String key, String type) throws IOException {
        bos.write(("\n\tprivate " + type + " " + key + ";").getBytes(StandardCharsets.UTF_8));
    }

    private String getMethod(String scope, String result, String name, String param, String body) {
        return "\n\t" +
                (scope == null ? "" : scope + " ") +
                (result == null ? "" : result + " ") +
                name + "(" +
                param + "){" +
                (body == null ? "}" : body + "}") +
                "\n";
    }

    private void method(BufferedOutputStream bos, String result, String name, String body) throws IOException {
        bos.write(getMethod("public static", result, name, "", body).getBytes(StandardCharsets.UTF_8));
    }

    private String getFormatValue(String type, Object val) {
        switch (type.hashCode()) {
            case 2086184:
            case 79860828:
            case 2374300:
                return val + "L";
            case -672261858:
                return val.toString();
            case 67973692:
                return val + "F";
            case 2052876273:
                return val + "D";
            default:
                return null;
        }
    }
}