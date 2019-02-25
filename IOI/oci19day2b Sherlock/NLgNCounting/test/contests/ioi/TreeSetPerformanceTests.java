package contests.ioi;

import java.util.Random;
import java.util.SortedSet;
import java.util.TreeSet;

import org.junit.Ignore;
import org.junit.Test;

@Ignore
public class TreeSetPerformanceTests {

    @Test
    public void insert10_6ItemsTest() {
        long start = System.currentTimeMillis();
        TreeSet<Integer> t = new TreeSet<Integer>();
        Random r = new Random();
        for (int j = 0; j < 1000000; j++) {
            t.add(r.nextInt());
        }
        System.out.println(String.format("insert10_6ItemsTest -> %d milis.", System.currentTimeMillis() - start));
    }

    @Test
    public void successiveHeadSetTest() {
        int N = 10000;
        int Q = 10000;
        TreeSet<Integer> t = new TreeSet<Integer>();
        Random r = new Random();
        for (int j = 0; j < N; j++) {
            t.add(r.nextInt());
        }
        long start = System.currentTimeMillis();
        long heads = 0;
        for (int j = 0; j < Q; j++) {
            SortedSet<Integer> head = t.headSet(r.nextInt());
            heads += head.size();
        }
        System.out.println(String.format("successiveHeadSetTest -> %d milis. %d heads", System.currentTimeMillis() - start, heads));
    }

}
