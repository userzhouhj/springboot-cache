package com.jun.cache.custom;

/**
 * @author ：userzhou
 * @date ：Created in 2020
 */
public class DoubleLinkedList<V> {

    private Node<V> head;

    private Node<V> tail;

    public Node<V> getHead() {
        return head;
    }

    public void setHead(Node<V> head) {
        this.head = head;
    }

    public Node<V> getTail() {
        return tail;
    }

    public void setTail(Node<V> tail) {
        this.tail = tail;
    }

    /**
     * 添加啊节点到链表尾部
     * @param newNode 添加的节点
     */
    public void addNode(Node<V> newNode){
        if(newNode == null){
            throw new RuntimeException("节点为空！");
        }
        if(head == null){
            head = newNode;
            tail = newNode;
        }else{

            tail.setNext(newNode);
            newNode.setLast(tail);
            tail = newNode;
        }
    }

    /**
     * 将当前的节点移动到链表尾部
     * @param node 移动的节点
     */
    public void moveNodeToTail(Node<V> node){

        if(node == tail){
            return;
        }
        if(head == node){
            head = node.getNext();
            head.setLast(null);
        }else{
            node.getLast().setNext(node.getNext());
            node.getNext().setLast(node.getLast());
        }

        tail.setNext(node);
        node.setLast(tail);
        node.setNext(null);
        tail = node;
    }

    /**
     * 删除头节点
     * @return
     */
    public Node<V> removeHeadNode(){

        if(head == null){
            return null;
        }
        Node<V> res = this.head;

        if(head == tail){
            this.tail = null;
            this.head = null;
        }else{
            head = res.getNext();
            head.setLast(null);
            res.setNext(null);
        }

        return res;
    }
}
