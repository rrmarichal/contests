package priorityqueue;

import static org.junit.Assert.*;

import java.util.Arrays;
import java.util.Comparator;
import java.util.Random;

import org.junit.Test;

public class ValuesIndexedPriorityQueueTests {

    @Test
    public void test0() {
        IndexedPriorityQueue<Integer> queue = IndexedPriorityQueue.create(new Integer[] { 1, 2, 3 },
            new QueueIntegerOperation(0));
        assertNotNull(queue);
    }

    @Test
    public void test1() {
        IndexedPriorityQueue<Integer> queue = IndexedPriorityQueue.create(new Integer[] { 1, 2, 3 },
            new QueueIntegerOperation(0), null);
        assertNotNull(queue);
    }

    @Test
    public void testPeek0() {
        IndexedPriorityQueue<Integer> queue = IndexedPriorityQueue.create(new Integer[] { 3, 2, 1 },
            new QueueIntegerOperation(1));
        assertEquals(1, (int) queue.peek().value);
    }

    @Test
    public void testPeek1() {
        IndexedPriorityQueue<Integer> queue = IndexedPriorityQueue.create(new Integer[] { 1, 2, 3 },
            new QueueIntegerOperation(1), new Comparator<Integer>() {
                @Override
                public int compare(Integer o1, Integer o2) {
                    return -o1.compareTo(o2);
                }
            }
        );
        assertEquals(3, (int) queue.peek().value);
    }

    @Test
    public void testPoll() {
        IndexedPriorityQueue<Integer> queue = IndexedPriorityQueue.create(new Integer[] { 1, 2, 3 },
            new QueueIntegerOperation(1));
        assertEquals(1, (int) queue.poll().value);
        assertEquals(2, queue.size());
    }

    @Test
    public void testCustomComparator() {
        final int SIZE = 10;
        IndexedPriorityQueue<Integer> queue = IndexedPriorityQueue.create(new Integer[] { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 },
            new QueueIntegerOperation(1), new Comparator<Integer>() {
                @Override
                public int compare(Integer o1, Integer o2) {
                    return -o1.compareTo(o2);
                }
            }
        );
        queue.update(5, 10);
        assertEquals(16, (int) queue.poll().value);
        assertEquals(SIZE - 1, queue.size());
    }

    @Test
    public void testUpdate0() {
        IndexedPriorityQueue<Integer> queue = IndexedPriorityQueue.create(new Integer[] { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 },
            new QueueIntegerOperation(0));
        queue.update(0, -1);
        QueueItem<Integer> min = queue.peek();
        assertEquals(0, (int) min.value);
        assertEquals(0, (int) min.key);
    }

    @Test
    public void testUpdateIncrements() {
        final int SIZE = 10;
        IndexedPriorityQueue<Integer> queue = IndexedPriorityQueue.create(new Integer[] { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10 },
            new QueueIntegerOperation(0));
        for (int j = 0; j < SIZE; j++) {
            queue.update(j, j + 1);
        }
        for (int j = 0; j < SIZE; j++) {
            QueueItem<Integer> min = queue.poll();
            assertEquals(2 * (j + 1), (int) min.value);
            assertEquals(j, (int) min.key);
        }
        assertEquals(0, queue.size());
    }

    @Test
    public void randomTest() {
        final int SIZE = 100, OPERATIONS = 100;
        Random random = new Random();
        Integer[] source = new Integer[SIZE];
        Arrays.fill(source, 0);
        IndexedPriorityQueue<Integer> queue = IndexedPriorityQueue.create(source, new QueueIntegerOperation(0));
        for (int j = 0; j < OPERATIONS; j++) {
            int index = random.nextInt(SIZE);
            int value = random.nextInt();
            queue.update(index, value);
            source[index] += value;
            QueueItem<Integer> qmin = queue.peek();
            int sminIndex = findMinIndex(source);
            assertEquals(source[sminIndex], qmin.value);
        }
    }

    private static int findMinIndex(Integer[] array) {
        int minIndex = 0;
        for (int j = 1; j < array.length; j++) {
            if (array[minIndex] > array[j]) {
                minIndex = j;
            }
        }
        return minIndex;
    }

}
