package deque;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class LinkedListDeque<T> implements Deque<T>,Iterable<T> {
    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>(){
            private int index = 0;

            @Override
            public boolean hasNext() {
                return index < size();
            }

            @Override
            public T next() {
                if(!hasNext()) {
                    throw new NoSuchElementException();
                }
                T item = get(index);
                index += 1;
                return item;
            }
        };
    }

    private class Node {
        T element;
        Node next;
        Node prev;

        public Node(T i) {
            element = i;
            next = null;
            prev = null;
        }
        public Node(T i,Node p,Node n) {
            element = i;
            next = n;
            prev = p;
        }
    }
    private int size;
    private Node sentinel;

    public LinkedListDeque() {
        sentinel = new Node(null);
        sentinel.prev = sentinel;
        sentinel.next = sentinel;
        size = 0;
    }
    public LinkedListDeque(T x) {
        sentinel = new Node(x);
        sentinel.prev = sentinel;
        sentinel.next = sentinel;
        size = 1;
    }

    @Override
    public void addFirst(T item) {
        sentinel.next.prev = new Node(item,sentinel,sentinel.next);
        sentinel.next = sentinel.next.prev;
        size += 1;
    }

    @Override
    public void addLast(T item) {
        sentinel.prev.next = new Node(item,sentinel.prev,sentinel);
        sentinel.prev = sentinel.prev.next;
        size += 1;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void printDeque() {
        for(Node node = sentinel.next;node != sentinel;node = node.next){
            if(node.next == sentinel){
                System.out.print(node.element);
            } else {
                System.out.print(node.element + " ");
            }
        }
        System.out.println();
    }

    @Override
    public T removeFirst() {
        if(isEmpty()) {
            return null;
        }
        T returnVal = sentinel.next.element;
        sentinel.next = sentinel.next.next;
        sentinel.next.prev = sentinel;
        size -= 1;
        return returnVal;
    }

    @Override
    public T removeLast() {
        if(isEmpty()) {
            return null;
        }
        T returnVal = sentinel.prev.element;
        sentinel.prev = sentinel.prev.prev;
        sentinel.prev.next = sentinel;
        size -= 1;
        return returnVal;
    }

    @Override
    public T get(int index) {
        if(size-1 < index) {
            return null;
        }
        Node node = sentinel;
        for(int i = 0;i<index;i++) {
            node = node.next;
        }
        return node.element;
    }

    private T helperGet(int index,Node first) {
        if(first == sentinel) {
            return null;
        } else if(index == 0) {
            return first.element;
        } else {
            return helperGet(index - 1,first.next);
        }
    }

    public T getRecursive(int index) {
        return helperGet(index,sentinel.next);
    }

}
