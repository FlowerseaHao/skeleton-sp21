package deque;

import java.util.Iterator;
import java.util.NoSuchElementException;
import java.util.Objects;

public class ArrayDeque<T> implements Deque<T>, Iterable<T> {
    private T[] items;
    private int size;
    private int nextFirst;
    private int nextLast;


    public ArrayDeque() {
        items = (T []) new Object[8];
        size = 0;
        nextFirst = 0;
        nextLast = 0;
    }

    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null || getClass() != o.getClass()) {
            return false;
        }

        ArrayDeque<T> other = (ArrayDeque<T>) o;

        if (size() != other.size()) {
            return false;
        }

        for (int i = 0; i < size(); i++) {
            if (!Objects.equals(get(i), other.get(i))) {
                return false;
            }
        }

        return true;
    }


    private boolean isFull() {
        return size == items.length;
    }

    private boolean outOfIndex(int index) {
        return index < 0 || index > items.length - 1;
    }

    private void updateNextFirst() {
        nextFirst -= 1;
        if (outOfIndex(nextFirst)) {
            nextFirst = items.length - 1;
        }
    }

    private void updateNextLast() {
        nextLast += 1;
        if (outOfIndex(nextLast)) {
            nextLast = 0;
        }
    }

    private void resize(int capacity) {
        T[] newArray = (T []) new Object[capacity];
        if (size >= 0) {
            System.arraycopy(items, 0, newArray, 0, size);
        }
        items = newArray;
    }

    @Override
    public void addFirst(T item) {
        // if items is full, then resize it to double current length.
        if (isFull()) {
            resize(items.length * 2);
        }

        if (nextFirst == nextLast) {
            updateNextLast();
        }
        items[nextFirst] = item;
        updateNextFirst();
        size += 1;
    }

    @Override
    public void addLast(T item) {
        if (isFull()) {
            resize(items.length * 2);
        }
        if (nextFirst == nextLast) {
            updateNextFirst();
        }
        items[nextLast] = item;
        updateNextLast();

        size += 1;
    }

    private boolean lowUsage() {
        if (items.length < 16) {
            return false;
        }
        return (double) size / items.length < 0.25;
    }


    @Override
    public T removeFirst() {
        if (isEmpty()) {
            return null;
        }

        int firstIndex = nextFirst + 1;
        if (outOfIndex(firstIndex)) {
            firstIndex = 0;
        }

        T itemToReturn = items[firstIndex];

        items[firstIndex] = null;

        nextFirst = firstIndex;

        size -= 1;

        if (lowUsage()) {
            resize(items.length / 2);
        }
        return itemToReturn;
    }

    @Override
    public T removeLast() {
        if (isEmpty()) {
            return null;
        }

        int lastIndex = nextLast - 1;
        if (outOfIndex(lastIndex)) {
            lastIndex = items.length - 1;
        }

        T itemToReturn = items[lastIndex];

        items[lastIndex] = null;

        nextLast = lastIndex;
        size -= 1;

        if (lowUsage()) {
            resize(items.length / 2);
        }

        return itemToReturn;
    }

    @Override
    public int size() {
        return size;
    }

    @Override
    public void printDeque() {
        int index = nextFirst + 1;
        if (outOfIndex(index)) {
            index = 0;
        }

        for (int i = 0; i < size; i++) {
            System.out.print(items[index]);
            index += 1;
            if (outOfIndex(index)) {
                index = 0;
            }
        }
    }

    @Override
    public T get(int index) {
        if (index < 0 || index >= size) {
            return null;
        }
        int first = nextFirst + 1;
        if (outOfIndex(first)) {
            first = 0;
        }
        int realIndex;

        realIndex = (first + index) % items.length;

        return items[realIndex];
    }

    @Override
    public Iterator<T> iterator() {
        return new Iterator<T>() {
            private int index = 0;

            @Override
            public boolean hasNext() {
                return index < size();
            }

            @Override
            public T next() {
                if (!hasNext()) {
                    throw new NoSuchElementException();
                }
                T item = get(index);
                index += 1;
                return item;
            }
        };
    }
}
