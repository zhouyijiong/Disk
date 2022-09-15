package com.zyj.disk.entity.request.user;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public final class UserWhole{
    private final String username;
    private final String password;
    private final String platform;
    private final String language;
    private final Integer cores;
    private final Integer thread;
    private final Double network;
}