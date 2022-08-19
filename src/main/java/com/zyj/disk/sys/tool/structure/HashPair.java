package com.zyj.disk.sys.tool.structure;

import com.zyj.disk.sys.entity.Record;

/**
 * 链表键值对
 * 当作羽量级返回模型 size 最好不超过 100
 */
public final class HashPair<K, V> extends Pair<K, V> {
    private static final Record record = new Record(HashPair.class);

    @Override
    public synchronized void put(K key, V val) {
        if (super.node == null) {
            super.node = new Node<>(key, val, hash(key));
            return;
        }
        int hash = hash(key);
        boolean isNull = key == null;
        Node<K, V> node = super.node, parentPair;
        do {
            if (node.hash == hash && (isNull ? node.key == null : key.equals(node.key))) {
                node.val = val;
                return;
            } else {
                parentPair = node;
                node = node.next;
            }
        } while (node != null);
        parentPair.next = new Node<>(key, val, hash(key));
        ++size;
    }

    @Override
    public synchronized V get(K key) {
        int hash = hash(key);
        boolean isNull = key == null;
        if (super.node == null) return null;
        Node<K, V> node = super.node;
        do {
            if (node.hash == hash && (isNull ? node.key == null : key.equals(node.key))) return node.val;
            node = node.next;
        } while (node != null);
        return null;
    }

    @Override
    public synchronized String toJSONString() {
        if (super.node == null) return "{}";
        Node<K, V> node = super.node;
        StringBuilder sb = new StringBuilder(size << 5);
        sb.append("{\"").append(node.key).append("\":\"").append(node.val).append("\"");
        while ((node = node.next) != null) {
            sb.append(",\"").append(node.key).append("\":\"").append(node.val).append("\"");
        }
        return sb.append("}").toString();
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