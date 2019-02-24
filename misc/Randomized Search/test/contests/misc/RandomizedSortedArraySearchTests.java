package contests.misc;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

import java.util.Arrays;
import java.util.Random;

import org.junit.Before;
import org.junit.Test;

import contests.misc.RandomizedSortedArraySearch.SearchResult;

public class RandomizedSortedArraySearchTests {

    private static final int SIZE = 1<<20;
    private static final int EXPERIMENTS = 25;

    private RandomizedSortedArraySearch alg;
    private Random random;

    @Before
    public void init() {
        alg = new RandomizedSortedArraySearch();
        random = new Random();
    }

    @Test
    public void basicSearchTest() {
        int[] list = new int[] { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11 };
        SearchResult result0 = alg.search(list, 1);
        assertEquals(0, result0.index);
        SearchResult result1 = alg.search(list, 2);
        assertEquals(1, result1.index);
        SearchResult result2 = alg.search(list, 7);
        assertEquals(6, result2.index);
        SearchResult result3 = alg.search(list, 11);
        assertEquals(10, result3.index);
    }

    @Test
    public void randomArrayTest0() {
        int[] list = new int[SIZE + 1];
        for (int j = 0; j < SIZE; j++) {
            list[j] = random.nextInt();
        }
        int target = random.nextInt();
        list[SIZE] = target;
        Arrays.sort(list);
        SearchResult result = alg.search(list, target);
        assertNotEquals(-1, result.index);
        assertEquals(list[result.index], target);
    }

    @Test
    public void randomArrayTest1() {
        int[] list = new int[SIZE];
        int target = random.nextInt();
        for (int j = 0; j < SIZE; j++) {
            list[j] = random.nextInt();
            if (list[j] == target) {
                j--;
            }
        }
        Arrays.sort(list);
        SearchResult result = alg.search(list, target);
        assertEquals(-1, result.index);
    }

    @Test
    public void iterationsCounterTest() {
        int[] list = new int[SIZE];
        int maxIterations = 0, total = 0;
        for (int k = 0; k < EXPERIMENTS; k++) {
            for (int j = 0; j < SIZE; j++) {
                list[j] = random.nextInt();
            }
            Arrays.sort(list);
            SearchResult result = alg.search(list, new Random().nextInt());
            total += result.iterations;
            if (result.iterations > maxIterations) {
                maxIterations = result.iterations;
            }
        }
        System.out.println(
            String.format("Max Iterations: %d. Average iterations: %.2f. Array size: %d",
                maxIterations, (float) total / EXPERIMENTS, SIZE));
    }

}
