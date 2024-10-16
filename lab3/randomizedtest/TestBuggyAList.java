package randomizedtest;

import edu.princeton.cs.algs4.In;
import edu.princeton.cs.algs4.StdRandom;
import org.junit.Test;
import timingtest.AList;

import static org.junit.Assert.*;

/**
 * Created by hug.
 */
public class TestBuggyAList {
  // YOUR TESTS HERE
    @Test
    public void testThreeAddThreeRemove(){
        AListNoResizing<Integer> lr = new AListNoResizing<>();
        BuggyAList<Integer> lv = new BuggyAList<>();
        for(int i = 4;i<=6;i++){
            lr.addLast(i);
            lv.addLast(i);
        }
        assertEquals(lr.size(),lv.size());
        for(int i = 0;i<3;i++){
            assertEquals(lr.removeLast(),lv.removeLast());
        }

    }

    @Test
    public void randomizedTest(){
        AListNoResizing<Integer> L = new AListNoResizing<>();
        BuggyAList<Integer> L2 = new BuggyAList<>();

        int N = 5000;
        for (int i = 0; i < N; i += 1) {
            int operationNumber = StdRandom.uniform(0, 4);
            if (operationNumber == 0) {
                // addLast
                int randVal = StdRandom.uniform(0, 100);
                L.addLast(randVal);
                L2.addLast(randVal);
                System.out.println("L and L2.addLast(" + randVal + ")");
            } else if (operationNumber == 1) {
                // size
                int size = L.size();
                int size2 = L2.size();
                assertEquals(size,size2);

                System.out.println("L and L2.size: " + size);
            } else if (operationNumber == 2 && L.size() > 0){
                System.out.println("L and L2.getLast():"+L.getLast());

                assertEquals(L.getLast(),L2.getLast());
            } else if (operationNumber == 3 && L.size() > 0){
                System.out.println("L and L2.getLast():"+L.getLast());

                assertEquals(L.removeLast(),L2.removeLast());
            }
        }
    }

}
