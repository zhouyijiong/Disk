package com.zyj.disk.entity.user;

import lombok.EqualsAndHashCode;
import lombok.Getter;

@Getter
@EqualsAndHashCode(callSuper = false)
public final class UserAttach {
    private Integer id;
    private Integer userId;
    private String platform;
    private String language;
    private String lastClientInfo;
    private String thisClientInfo;
    private Integer deviceMemory;
    private Integer hardwareConcurrency;
    private Integer networkSpeed;
    private String networkType;

    public static UserAttach noArgs() {
        return new UserAttach();
    }

    public static UserAttach defaultArgs() {
        return noArgs()
                .platform(null)
                .language(null)
                .lastClientInfo(null)
                .deviceMemory(0)
                .hardwareConcurrency(0)
                .networkSpeed(0)
                .networkType(null);
    }

    public UserAttach id(Integer val) {
        id = val;
        return this;
    }

    public UserAttach userId(Integer val) {
        userId = val;
        return this;
    }

    public UserAttach platform(String val) {
        platform = val;
        return this;
    }

    public UserAttach language(String val) {
        language = val;
        return this;
    }

    public UserAttach lastClientInfo(String val) {
        lastClientInfo = val;
        return this;
    }

    public UserAttach thisClientInfo(String val) {
        thisClientInfo = val;
        return this;
    }

    public UserAttach deviceMemory(Integer val) {
        deviceMemory = val;
        return this;
    }

    public UserAttach hardwareConcurrency(Integer val) {
        hardwareConcurrency = val;
        return this;
    }

    public UserAttach networkSpeed(Integer val) {
        networkSpeed = val;
        return this;
    }

    public UserAttach networkType(String val) {
        networkType = val;
        return this;
    }
}