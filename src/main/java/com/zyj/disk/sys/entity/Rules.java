package com.zyj.disk.sys.entity;

import lombok.AllArgsConstructor;

import java.util.regex.Pattern;

@AllArgsConstructor
public enum Rules {
    NULL(Pattern.compile("")),
    CHARACTER_LOW(Pattern.compile("[a-z]+")),
    NUM(Pattern.compile("[0-9]+")),
    NUM_CHAR_LOW(Pattern.compile("[0-9a-z]+")),
    NUM_CHAR_LOW_32(Pattern.compile("[0-9a-z]{32}"));

    public final Pattern rules;
}