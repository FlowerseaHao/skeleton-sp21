package deque;

public class LinkedListDeque<T> {
    private class Node{
        T element;
        Node next;
        Node prev;

        public Node(T i,Node p,Node n){
            element = i;
            next = n;
            prev = p;
        }
    }
    private int size;
    private Node sentinel;

    public LinkedListDeque(){
        sentinel = null;
        size = 0;
    }
    public LinkedListDeque(T x){
        sentinel.next = new Node(x,sentinel,sentinel);
        sentinel.prev = sentinel.next;
        size = 1;
    }
    public void addFirst(T item){
        sentinel.next.prev = new Node(item,sentinel,sentinel.next);
        sentinel.next = sentinel.next.prev;
        size += 1;
    }

    public void addLast(T item){
        sentinel.prev.next = new Node(item,sentinel.prev,sentinel);
        sentinel.prev = sentinel.prev.next;
        size += 1;
    }

    public boolean isEmpty(){
        return size == 0;
    }

    public int size(){
        return size;
    }
    public void printDeque(){
        for(Node node = sentinel.next;node != sentinel;node = node.next){
            if(node.next == sentinel){
                System.out.print(node.element);
            } else {
                System.out.print(node.element + " ");
            }
        }
        System.out.println();
    }

    public T removeFirst(){
        if(isEmpty()){
            return null;
        }
        T returnVal = sentinel.next.element;
        sentinel.next = sentinel.next.next;
        sentinel.next.prev = sentinel;
        size -= 1;
        return returnVal;
    }

    public T removeLast(){
        if(isEmpty()){
            return null;
        }
        T returnVal = sentinel.prev.element;
        sentinel.prev = sentinel.prev.prev;
        sentinel.prev.next = sentinel;
        size -= 1;
        return returnVal;
    }

    public T get(int index){
        if(size-1 < index){
            return null;
        }
        Node node = sentinel;
        for(int i = 0;i<index;i++){
            node = node.next;
        }
        return node.element;
    }

    private T helperGet(int index,Node first){
        if(first == sentinel){
            return null;
        } else if(index == 0){
            return first.element;
        } else {
            return helperGet(index - 1,first.next);
        }
    }

    public T getRecursive(int index){
        return helperGet(index,sentinel.next);
    }

}
