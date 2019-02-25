package contests.ioi;

import java.util.Random;
import java.util.SortedMap;
import java.util.TreeMap;

import org.junit.Ignore;
import org.junit.Test;

@Ignore
public class TreeMapPerformanceTests {

    @Test
    public void successiveHeadMapTest() {
        int N = 10000;
        int Q = 10000;
        TreeMap<Integer, Integer> t = new TreeMap<Integer, Integer>();
        Random r = new Random();
        for (int j = 0; j < N; j++) {
            t.put(r.nextInt(), 1);
        }
        long start = System.currentTimeMillis();
        long heads = 0;
        for (int j = 0; j < Q; j++) {
            SortedMap<Integer, Integer> head = t.headMap(r.nextInt());
            heads += head.size();
        }
        System.out.println(String.format("successiveHeadMapTest -> %d milis. %d heads", System.currentTimeMillis() - start, heads));
    }

}
