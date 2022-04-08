package com.zyj.disk.sys.tool;

import java.util.LinkedHashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * @Author: ZYJ
 * @Date: 2022/4/8 9:25
 * @Remark: 内部响应式缓存
 */
public final class ResponsiveCache<K,V>{
    private final int size;
    private final int timing;
    private int targetTime;

    private final Map<K,TimeVal<V>> container;

    public ResponsiveCache(int size, int timing){
        this.size = size;
        this.timing = timing;
        this.targetTime = timing + currentTime();
        container = new LinkedHashMap<>();
    }

    private final static class TimeVal<V>{
        public final V val;
        public final int expirationTime;

        public TimeVal(V val,int expirationTime){
            this.val = val;
            this.expirationTime = expirationTime;
        }
    }

    private int currentTime(){
        return (int)(System.currentTimeMillis() / 1000);
    }

    public void put(K key,V val,int seconds){
        if(container.size() < size) container.put(key,new TimeVal<>(val,seconds + currentTime()));
    }

    public V get(K key){
        TimeVal<V> timeVal = container.get(key);
        if(timeVal == null) return null;
        int currentTime = currentTime();
        if(currentTime > timeVal.expirationTime){
            container.remove(key);
            return null;
        }
        if(currentTime > targetTime){
            this.targetTime = timing + currentTime;
            new Thread(() -> {
                final List<K> storage = new LinkedList<>();
                container.forEach((k,v) -> {
                    if(currentTime > v.expirationTime) storage.add(k);
                });
                storage.forEach(container::remove);
            }).start();
        }
        return timeVal.val;
    }
}