package com.zyj.disk.sys.result;

/**
 * @Author: ZYJ
 * @Date: 2022/4/27 17:01
 * @Remark: 链表键值对 当作轻量级返回模型,范围 [1:100] ,超范围效率比降低
 */
public final class Pair<K,V>{
    private final K key;
    private V val;
    private transient int size;
    private transient final int hash;
    private transient Pair<K,V> next;

    public Pair(K key,V val){
        this.key = key;
        this.val = val;
        this.hash = hash(key);
        size = 1;
    }

    public void put(K key,V val){
        int hash = hash(key);
        boolean isNull = key == null;
        Pair<K,V> pair = this,parentPair;
        do{
            if(this.hash == hash && (isNull ? pair.key == null : key.equals(pair.key))){
                pair.val = val;
                return;
            }else{
                parentPair = pair;
                pair = pair.next;
            }
        }while(pair != null);
        parentPair.next = new Pair<>(key,val);
        ++size;
    }

    public V get(K key){
        int hash = hash(key);
        boolean isNull = key == null;
        Pair<K,V> pair = this;
        do{
            if(this.hash == hash && (isNull ? pair.key == null : key.equals(pair.key))) return pair.val;
            pair = pair.next;
        }while(pair != null);
        return null;
    }

    private int hash(K key){
        if(key == null) return 0;
        int hash = key.hashCode();
        return hash ^ (hash >>> 16);
    }

    public String toJSONString(){
        Pair<K,V> pair = this;
        StringBuilder sb = new StringBuilder(size << 5);
        sb.append("{");
        while(pair != null){
            sb.append("\"").append(pair.key).append("\":\"").append(pair.val).append("\"");
            pair = pair.next;
        }
        return sb.append("}").toString();
    }
}