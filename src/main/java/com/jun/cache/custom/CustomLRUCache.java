package com.jun.cache.custom;

import java.util.HashMap;

/**
 * 自定义LRU缓存
 * @author ：userzhou
 * @date ：Created in 2020
 */
public class CustomLRUCache<K,V> {

    /**
     * 存储k对用链表中的node
     */
    private HashMap<K,Node<V>> keyNodeMap;

    /**
     * 存储链表中node对应的k
     */
    private HashMap<Node<V>,K> nodeKayMap;

    private Long capacity;

    private DoubleLinkedList<V> nodeList;

    public CustomLRUCache(Long capacity) {
        if(capacity < 1){
            throw new RuntimeException("The capacity is too small.");
        }
        this.capacity = capacity;
        this.keyNodeMap = new HashMap<>();
        this.nodeKayMap = new HashMap<>();
        this.nodeList = new DoubleLinkedList<>();
    }

    public void setCache(K key,V value){

        if(keyNodeMap.containsKey(key)){
            Node<V> vNode = keyNodeMap.get(key);

            vNode.setValue(value);
            nodeList.moveNodeToTail(vNode);
        }else{

            if(keyNodeMap.size() == this.capacity){
                Node<V> headNode = nodeList.removeHeadNode();
                K removeKey = nodeKayMap.get(headNode);
                keyNodeMap.remove(removeKey);
                nodeKayMap.remove(headNode);
            }
            Node<V> newNode = new Node<>(value);
            keyNodeMap.put(key,newNode);
            nodeKayMap.put(newNode,key);

            nodeList.addNode(newNode);
        }
    }

    public V getCache(K key){
        if(keyNodeMap.containsKey(key)){
            Node<V> vNode = keyNodeMap.get(key);
            nodeList.moveNodeToTail(vNode);

            return vNode.getValue();
        }

        return null;
    }

    public void getAllCAche(){
        Node<V> head = nodeList.getHead();
        while(head != null){
            System.out.println(head.getValue());
            head = head.getNext();
        }
    }
}
