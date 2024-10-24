package deque;

public class ArrayDeque<T> implements Deque<T> {
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

    private boolean isNull(){
        return size == 0;
    }

    private boolean outOfIndex(int index){
        return index < 0 || index > items.length - 1;
    }

    private void updateNextFirst(){
        nextFirst -= 1;
        if(outOfIndex(nextFirst)){
            nextFirst = items.length - 1;
        }
    }

    private void updateNextLast(){
        nextLast += 1;
        if(outOfIndex(nextLast)){
            nextLast = 0;
        }
    }

    private void resize(int capacity){
        T[] newArray = (T []) new Object[capacity];
        for(int i = 0;i<size;i++){
            newArray[i] = items[i];
        }
        items = newArray;
    }

    @Override
    public void addFirst(T item){
        if(isFull()){
            resize(items.length * 2);
        }
        if(isNull()){
            updateNextLast();
        }
        items[nextFirst] = item;
        updateNextFirst();
        size += 1;
    }

    @Override
    public void addLast(T item){
        if(isFull()){
            resize(items.length * 2);
        }
        if(isNull()){
            updateNextFirst();
        }
        items[nextLast] = item;
        updateNextLast();

        size += 1;
    }

    private boolean lowUsage(){
        if(items.length < 16){
            return false;
        }
        return (double) size /items.length < 0.25;
    }


    @Override
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

    @Override
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

    @Override
    public int size(){
        return size;
    }

    @Override
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

    @Override
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
