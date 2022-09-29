package com.zyj.disk.sys.tool.structure;

import com.zyj.disk.sys.entity.Record;

/**
 * 链表键值对
 * 当作羽量级返回模型 size 最好不超过 100
 */
public abstract class Pair<K, V> {
    protected Node<K, V> node;
    public transient int size;
    private static final Record RECORD = new Record(Pair.class);

    /**
     * put element
     *
     * @param key key
     * @param val val
     */
    public abstract void put(K key, V val);

    /**
     * get element
     *
     * @param key key
     * @return val
     */
    public abstract V get(K key);

    /**
     * calc hash
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
            RECORD.error(e);
            return null;
        }
    }
}