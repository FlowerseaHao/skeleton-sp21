package deque;

import java.util.Comparator;
public class MaxArrayDeque<T> extends ArrayDeque<T>{
    public MaxArrayDeque(Comparator<T> c){

    }

    public T max(){

    }

    public T max(Comparator<T> c){

    }

    private static class IntegerComparator implements Comparator<Integer>{

        @Override
        public int compare(Integer o1, Integer o2) {
            return o1.compareTo(o2);
        }
    }

    
}
