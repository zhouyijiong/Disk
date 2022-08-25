package com.zyj.disk.sys.tool.structure;

import com.zyj.disk.sys.entity.Record;

/**
 * 链表键值对抽象类
 */
public abstract class Pair<K, V> {
    protected Node<K, V> node;
    public transient int size;
    private static final Record record = new Record(HashPair.class);

    /**
     * 链表键值对
     */
    static final class Node<K, V> {
        public K key;
        public V val;
        public transient int hash;
        public transient Node<K, V> next;

        public Node(K key, V val, int hash) {
            this.key = key;
            this.val = val;
            this.hash = hash;
        }
    }

    /**
     * 添加元素
     *
     * @param key key
     * @param val val
     */
    public abstract void put(K key, V val);

    /**
     * 获取元素
     *
     * @param key key
     * @return val
     */
    public abstract V get(K key);

    /**
     * 转换成 json 字符串
     *
     * @return json 字符串
     */
    public abstract String toJSONString();

    /**
     * hash计算
     *
     * @param key key
     * @return hash
     */
    protected int hash(K key) {
        if (key == null) return 0;
        int hash = key.hashCode();
        return hash ^ (hash >>> 16);
    }

    public static Pair<String, String> fromPair(String str) {
        Pair<String, String> pair = new HashPair<>();
        int len = str.length();
        try {
            for (int i = 0; i < len; ++i) {
                if (str.charAt(i) == '"') {
                    int index = str.indexOf('"', i += 1);
                    String key = str.substring(i, index);
                    i = str.indexOf('"', index += 3);
                    String val = str.substring(index, i);
                    pair.put(key, val);
                }
            }
            return pair;
        } catch (RuntimeException e) {
            record.error(e);
            return null;
        }
    }
}