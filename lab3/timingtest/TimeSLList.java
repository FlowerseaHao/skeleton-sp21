package timingtest;
import edu.princeton.cs.algs4.Stopwatch;

/**
 * Created by hug.
 */
public class TimeSLList {
    private static void printTimingTable(AList<Integer> Ns, AList<Double> times, AList<Integer> opCounts) {
        System.out.printf("%12s %12s %12s %12s\n", "N", "time (s)", "# ops", "microsec/op");
        System.out.printf("------------------------------------------------------------\n");
        for (int i = 0; i < Ns.size(); i += 1) {
            int N = Ns.get(i);
            double time = times.get(i);
            int opCount = opCounts.get(i);
            double timePerOp = time / opCount * 1e6;
            System.out.printf("%12d %12.2f %12d %12.2f\n", N, time, opCount, timePerOp);
        }
    }

    public static void main(String[] args) {
        timeGetLast();
    }

    public static void timeGetLast() {
        // TODO: YOUR CODE HERE
        AList<Integer> Ns = new AList<>();
        AList<Double> times = new AList<>();
        AList<Integer> opCounts = new AList<>();

        int MaxSize = 128000;
        int opCount = 10000;

        for(int n = 1000; n<=MaxSize; n *= 2){
            Ns.addLast(n);

            SLList<Integer> l2 = new SLList<>();
            for(int i = 0;i<n;i++){
                l2.addLast(1);
            }
            Stopwatch sw = new Stopwatch();
            for(int i = 0;i<opCount; i++){
                l2.addLast(1);
            }
            double timeInSeconds = sw.elapsedTime();
            times.addLast(timeInSeconds);

            opCounts.addLast(opCount);
        }

        printTimingTable(Ns,times,opCounts);
    }

}
