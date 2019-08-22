import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class PriorityQueueTests {

    @Test
	public void test0() {
		PriorityQueue queue = new PriorityQueue(1);
		assertEquals(0, queue.sum());
		queue.add(2);
		assertEquals(2, queue.sum());
		queue.add(4);
		assertEquals(2, queue.sum());
		queue.add(1);
		assertEquals(1, queue.sum());
	}

	@Test
	public void test1() {
		PriorityQueue queue = new PriorityQueue(5);
		queue.add(1);
		queue.add(2);
		queue.add(3);
		queue.add(4);
		queue.add(5);
		assertEquals(15, queue.sum());
		queue.add(1);
		assertEquals(11, queue.sum());
		queue.add(1);
		assertEquals(8, queue.sum());
		queue.add(1);
		assertEquals(6, queue.sum());
		queue.add(1);
		assertEquals(5, queue.sum());	
	}

	@Test
	public void test2() {
		PriorityQueue queue = new PriorityQueue(5);
		queue.add(1);
		queue.add(2);
		queue.add(3);
		queue.add(4);
		queue.add(5);
		assertEquals(15, queue.sum());
		queue.add(10);
		assertEquals(15, queue.sum());
		queue.add(20);
		assertEquals(15, queue.sum());
	}

}
