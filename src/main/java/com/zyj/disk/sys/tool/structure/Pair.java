package com.zyj.disk.sys.tool.structure;

/** 链表键值对抽象类 */
public abstract class Pair<K,V>{
    protected Node<K,V> node;
    protected transient int size;

    /** 链表键值对 */
    static final class Node<K,V>{
        public K key;
        public V val;
        public transient int hash;
        public transient Node<K,V> next;

        public Node(K key,V val,int hash){
            this.key = key;
            this.val = val;
            this.hash = hash;
        }
    }

    /**
     * 添加元素
     * @param key key
     * @param val val
     */
    public abstract void put(K key,V val);

    /**
     * 获取元素
     * @param key key
     * @return val
     */
    public abstract V get(K key);

    /**
     * 转换成 json 字符串
     * @return json 字符串
     */
    public abstract String toJSONString();

    /**
     * @Author: ZYJ
     * @Date: 2022/04/28
     * @Remark: hash计算
     */
    protected int hash(K key){
        if(key == null) return 0;
        int hash = key.hashCode();
        return hash ^ (hash >>> 16);
    }
}