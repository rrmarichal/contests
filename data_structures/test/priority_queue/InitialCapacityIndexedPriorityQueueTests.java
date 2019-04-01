package priority_queue;

import static org.junit.Assert.*;

import java.util.Comparator;
import java.util.Random;

import org.junit.Test;

public class InitialCapacityIndexedPriorityQueueTests {

    @Test
    public void test0() {
        IndexedPriorityQueue<Integer> queue = IndexedPriorityQueue.create(10, new QueueIntegerOperation(0));
        assertNotNull(queue);
    }

    @Test
    public void test1() {
        IndexedPriorityQueue<Integer> queue = IndexedPriorityQueue.create(10, new QueueIntegerOperation(0), null);
        assertNotNull(queue);
    }

    @Test
    public void testPeek() {
        IndexedPriorityQueue<Integer> queue = IndexedPriorityQueue.create(10, new QueueIntegerOperation(1));
        assertEquals(1, (int) queue.peek().value);
    }

    @Test
    public void testPoll() {
        final int SIZE = 10;
        IndexedPriorityQueue<Integer> queue = IndexedPriorityQueue.create(SIZE, new QueueIntegerOperation(1));
        assertEquals(1, (int) queue.poll().value);
        assertEquals(SIZE - 1, queue.size());
    }

    @Test
    public void testCustomComparator() {
        final int SIZE = 10;
        IndexedPriorityQueue<Integer> queue = IndexedPriorityQueue.create(SIZE, new QueueIntegerOperation(1), new Comparator<Integer>() {
            @Override
            public int compare(Integer o1, Integer o2) {
                return -o1.compareTo(o2);
            }
        });
        queue.update(5, 10);
        assertEquals(11, (int) queue.poll().value);
        assertEquals(SIZE - 1, queue.size());
    }

    @Test
    public void testUpdate0() {
        final int SIZE = 10, DEFAULT = 100;
        IndexedPriorityQueue<Integer> queue = IndexedPriorityQueue.create(SIZE, new QueueIntegerOperation(DEFAULT));
        queue.update(5, -1);
        QueueItem<Integer> min = queue.peek();
        assertEquals(DEFAULT - 1, (int) min.value);
        assertEquals(5, (int) min.key);
    }

    @Test
    public void testUpdateIncrements() {
        final int SIZE = 10, DEFAULT = 100;
        IndexedPriorityQueue<Integer> queue = IndexedPriorityQueue.create(SIZE, new QueueIntegerOperation(DEFAULT));
        for (int j = 0; j < SIZE; j++) {
            queue.update(j, SIZE - j);
        }
        for (int j = 0; j < SIZE; j++) {
            QueueItem<Integer> min = queue.poll();
            assertEquals(DEFAULT + j + 1, (int) min.value);
            assertEquals(SIZE - j - 1, (int) min.key);
        }
        assertEquals(0, queue.size());
    }

    @Test
    public void testUpdateDecrements() {
        final int SIZE = 10, DEFAULT = 100;
        IndexedPriorityQueue<Integer> queue = IndexedPriorityQueue.create(SIZE, new QueueIntegerOperation(DEFAULT));
        for (int j = 0; j < SIZE; j++) {
            queue.update(j, -(j + 1));
        }
        for (int j = 0; j < SIZE; j++) {
            QueueItem<Integer> min = queue.poll();
            assertEquals(DEFAULT - SIZE + j, (int) min.value);
            assertEquals(SIZE - j - 1, (int) min.key);
        }
        assertEquals(0, queue.size());
    }

    @Test
    public void randomTest() {
        final int SIZE = 100, OPERATIONS = 100;
        Random random = new Random();
        int[] source = new int[SIZE];
        IndexedPriorityQueue<Integer> queue = IndexedPriorityQueue.create(SIZE, new QueueIntegerOperation(0));
        for (int j = 0; j < OPERATIONS; j++) {
            int index = random.nextInt(SIZE);
            int value = random.nextInt();
            queue.update(index, value);
            source[index] += value;
            QueueItem<Integer> qmin = queue.peek();
            int sminIndex = findMinIndex(source);
            assertEquals(source[sminIndex], (int) qmin.value);
            assertEquals(sminIndex, (int) qmin.key);
        }
    }

    private static int findMinIndex(int[] array) {
        int minIndex = 0;
        for (int j = 1; j < array.length; j++) {
            if (array[minIndex] > array[j]) {
                minIndex = j;
            }
        }
        return minIndex;
    }

}
