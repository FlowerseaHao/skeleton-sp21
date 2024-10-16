package deque;

public class ArrayDeque<T> {
    private T[] items;
    private int size;
    private int nextFirst;
    private int nextLast;

    public ArrayDeque(){
        items = (T []) new Object[8];
        size = 0;
        nextFirst = 0;
        nextLast = 0;
    }

    private boolean isFull(){
        return size == items.length;
    }

    private void resize(int capacity){
        T[] newArray = (T []) new Object[capacity];
        for(int i = 0;i<size;i++){
            newArray[i] = items[i];
        }
        items = newArray;
    }

    public void addFirst(T item){
        if(isFull()){
            resize(items.length * 2);
        }
        items[nextFirst] = item;
        nextFirst -= 1;
        if(nextFirst < 0){
            nextFirst = items.length -1;
        }
        size += 1;
    }

    public void addLast(T item){
        if(isFull()){
            resize(items.length * 2);
        }
        items[nextLast] = item;
        nextLast += 1;
        if(nextLast > items.length-1){
            nextLast = 0;
        }
        size += 1;
    }

    private boolean lowUsage(){
        if(items.length < 16){
            return false;
        }
        return (double) size /items.length < 0.25;
    }

    private boolean outOfIndex(int index){
        return index < 0 || index > items.length - 1;
    }
    public T removeFirst(){
        if(isEmpty()){
            return null;
        }
        nextFirst += 1;
        if(nextFirst == items.length){
            nextFirst = 0;
        }
        size -= 1;
        return items[nextFirst];
    }

    public T removeLast(){
        if(isEmpty()){
            return null;
        }
        if(lowUsage()){
            resize(items.length / 2);
        }
        nextLast -= 1;
        if(nextLast == -1){
            nextLast = items.length - 1;
        }
        size -= 1;
        return items[nextLast];
    }

    public int size(){
        return size;
    }

    public boolean isEmpty(){
        return size == 0;
    }

    public void printDeque(){
        int index = nextFirst + 1;
        if(outOfIndex(index)){
            index = 0;
        }

        for(int i = 0;i<size;i++){
            System.out.print(items[index]);
            index += 1;
            if(outOfIndex(index)){
                index = 0;
            }
        }
    }

    public T get (int index){
        int first = nextFirst + 1;
        if(outOfIndex(first)){
            first = 0;
        }
        int realIndex;
        if(first + index > items.length - 1){
            realIndex = first + index - items.length;
        } else {
            realIndex = first + index;
        }
        return items[realIndex];
    }
}
