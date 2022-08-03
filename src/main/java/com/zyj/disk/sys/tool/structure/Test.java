package com.zyj.disk.sys.tool.structure;

import java.util.Objects;

/**
 * @Author: ZYJ
 * @Date: 2022/6/10 11:16
 * @Remark:
 */
public class Test{
    public static void main(String[] args) {
        LinkNode<Task> linkNode = new LinkNode<>();
        linkNode.put(() -> {
            System.out.println("Hello");
        });
        linkNode.put(() -> System.out.println("World"));
    }
}

interface Task{
    abstract void run();
}