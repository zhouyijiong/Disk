package com.zyj.disk.sys.tool.structure;

import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @Author: ZYJ
 * @Date: 2022/6/9 9:28
 * @Remark: linkNode
 */
@ToString
public final class LinkNode<T>{
    private int size;
    private Node<T> node;

    public void put(T val){
        if(node != null){
            node.put(val);
        }else{
            node = new Node<>(val);
        }
        ++size;
    }

    public T get(){
        if(node == null) return null;
        T result = node.node;
        node = node.next;
        return result;
    }

    @ToString
    @NoArgsConstructor
    static final class Node<T>{
        T node;
        Node<T> next;
        transient int hash;

        public Node(T node){
            this.node = node;
        }

        public void put(T node){
            if(this.node == null){
                this.node = node;
                return;
            }
            Node<T> parNode = this,curNode = parNode.next;
            while(curNode != null){
                parNode = curNode;
                curNode = curNode.next;
            }
            parNode.next = new Node<>(node);
        }
    }

    static final class HeadNode<T>{
        Node<T> next;
        Node<T> tail;
    }
}