package com.zyj.disk.sys.generate;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public final class FieldInfo {
    private String classType;
    private String key;
    private Object value;
    private final boolean primary;
    private final boolean unique;
    private final boolean required;
    private final String dataType;
}