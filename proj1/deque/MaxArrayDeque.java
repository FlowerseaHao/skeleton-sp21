package deque;

import java.util.Comparator;
public class MaxArrayDeque<T> extends ArrayDeque<T>{
    Comparator<T> maxComparator;

    public MaxArrayDeque(Comparator<T> c){
        maxComparator = c;
    }

    public T max(){
        return max(maxComparator);
    }

    public T max(Comparator<T> c){
        T maxValue = get(0);
        for(int i = 1;i<size();i++){
            if(c.compare(get(i),maxValue) > 0){
                maxValue = get(i);
            }
        }
        return maxValue;
    }

    private static class IntegerComparator implements Comparator<Integer>{

        @Override
        public int compare(Integer o1, Integer o2) {
            return o1.compareTo(o2);
        }
    }

    private static class StringComparator implements Comparator<String>{
        @Override
        public int compare(String o1, String o2) {
            return o1.compareTo(o2);
        }
    }

    private static class FloatComparator implements Comparator<Float>{

        @Override
        public int compare(Float o1, Float o2) {
            return o1.compareTo(o2);
        }
    }

    private static class DoubleComparator implements Comparator<Double>{

        @Override
        public int compare(Double o1, Double o2) {
            return o1.compareTo(o2);
        }
    }
}
