package fenwicktree;

import java.util.Random;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

@Ignore("Do not run compute intensive tests by default.")
public class PerformanceTests {

    private static final int SIZE = 1<<20;
    private static final int INCREMENT_OPERATIONS = 1<<22;
    private static final int SUM_OPERATIONS = 1<<22;

    private FenwickTree tree;
    private Random random;

    @Before
    public void init() {
        random = new Random();
        tree = FenwickTree.withValue(SIZE, 1);
    }

    @Test
    public void incrementMultipleTest() {
        int[] incrementValues = new int[INCREMENT_OPERATIONS];
        for (int j = 0; j < INCREMENT_OPERATIONS; j++) {
            incrementValues[j] = random.nextInt();
        }
        long start = System.currentTimeMillis();
        for (int j = 0; j < INCREMENT_OPERATIONS; j++)
            tree.increment(j, incrementValues[j]);
        System.out.println(
            String.format("contests.fenwick.PerformanceTests.incrementMultipleTest. Elapsed: %d",
                System.currentTimeMillis() - start));
    }

    @Test
    public void sumMultipleTest() {
        int[][] sumRanges = new int[SUM_OPERATIONS][2];
        for (int j = 0; j < SUM_OPERATIONS; j++) {
            int length = random.nextInt(SIZE) + 1;
            int left = random.nextInt(SIZE - length + 1);
            sumRanges[j][0] = left;
            sumRanges[j][1] = left + length - 1;
        }
        long start = System.currentTimeMillis();
        long total = 0;
        for (int j = 0; j < SUM_OPERATIONS; j++) {
            total += tree.sum(sumRanges[j][0], sumRanges[j][1]);
        }
        System.out.println(
            String.format("contests.fenwick.PerformanceTests.sumMultipleTest. Elapsed: %d. Total: %d",
                System.currentTimeMillis() - start, total));
    }
    
}
