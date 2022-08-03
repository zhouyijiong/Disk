package com.zyj.disk.sys.tool.structure;

/**
 * @Author: ZYJ
 * @Date: 2022/6/7 15:16
 * @Remark:
 */
public final class Node<T>{
    public T node;
    public Node<T> next;
    public transient int hash;

    private Node(T node){
        this.node = node;
        this.hash = node.hashCode();
    }

    /**
     * 添加元素
     * @param node 节点
     */
    public void put(T node){
        if(this.node == null){
            this.node = node;
            this.hash = hash(node);
            return;
        }
        int hash = hash(node);
        boolean isNull = node == null;
        Node<T> curNode = this,parentPair;
        do{
            if(curNode.hash == hash && (isNull ? curNode.node == null : node.equals(curNode.node))){
                //node.val = val;
                return;
            }else{
                parentPair = curNode;
                curNode = curNode.next;
            }
        }while(curNode != null);
        parentPair.next = new Node<>(node);
    }

    private int hash(T node){
        if(node == null) return 0;
        int hash = node.hashCode();
        return hash ^ (hash >>> 16);
    }
}