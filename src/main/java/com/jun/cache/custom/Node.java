package com.jun.cache.custom;

/**
 * 双向链表节点信息
 * @author ：userzhou
 * @date ：Created in 2020
 */
public class Node<V> {

    private V value;

    private Node<V> last;

    private Node<V> next;

    public Node(V value) {
        this.value = value;
        this.last = null;
        this.next = null;
    }

    public V getValue() {
        return value;
    }

    public void setValue(V value) {
        this.value = value;
    }

    public Node<V> getLast() {
        return last;
    }

    public void setLast(Node<V> last) {
        this.last = last;
    }

    public Node<V> getNext() {
        return next;
    }

    public void setNext(Node<V> next) {
        this.next = next;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {return true;}
        if (o == null || getClass() != o.getClass()) {return false;}

        Node<?> node = (Node<?>) o;

        if (value != null ? !value.equals(node.value) : node.value != null) {return false;}
        if (last != null ? !last.equals(node.last) : node.last != null) {return false;}
        return next != null ? next.equals(node.next) : node.next == null;
    }

    @Override
    public int hashCode() {
        int result = value != null ? value.hashCode() : 0;
        result = 31 * result + (last != null ? last.hashCode() : 0);
        result = 31 * result + (next != null ? next.hashCode() : 0);
        return result;
    }
}
