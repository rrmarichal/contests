import static java.lang.System.out;

import java.util.*;

public class LRUCacheDriver {
	
	public static void main(String[] args) {
		LRUCache lru = new LRUCache(3);
		/*out.println(lru.get(10));
		lru.set(10, 20);
		out.println(lru.get(10));
		lru.set(11, 22);
		out.println(lru.get(10));
		out.println(lru.get(11));
		out.println(lru.get(12));
		lru.set(12, 24);
		out.println(lru.get(10));
		out.println(lru.get(11));
		out.println(lru.get(12));*/

		lru.set(1, 1);
		lru.set(2, 2);
		lru.set(3, 3);
		lru.set(4, 4);
		out.println(lru);
		out.println(lru.get(4));
		out.println(lru);
		out.println(lru.get(3));
		out.println(lru);
		out.println(lru.get(2));
		out.println(lru);
		out.println(lru.get(1));
		out.println(lru);
		lru.set(5, 5);
		out.println(lru);
		out.println(lru.get(1));
		out.println(lru);
		out.println(lru.get(2));
		out.println(lru);
		out.println(lru.get(3));
		out.println(lru);
		out.println(lru.get(4));
		out.println(lru);
		out.println(lru.get(5));
		out.println(lru);
	}

	static class LRUCache {
	    
	    PQ q;
	    static int time;
	    
	    public LRUCache(int capacity) {
	        q = new PQ(capacity);
	    }
	    
	    public int get(int key) {
	        time++;
	        if (q.contains(key)) {
	        	q.hit(key);
	        	return q.get(key);
	        }
	        return -1;
	    }
	    
	    public void set(int key, int value) {
	        time++;
	        if (q.contains(key))
	        	q.update(key, value);
	        else {
		        if (q.full())
		        	q.removeMin();
		        q.insert(key, value);
			}
	    }

    	@Override
    	public String toString() {
    		return q.toString();
    	}

	    static class PQ {

	    	HashMap<Integer, Integer> m;
	    	HeapItem[] heap;
	    	int count;

	    	public PQ(int capacity) {
	    		m = new HashMap();
	    		heap = new HeapItem[capacity];
	    	}

	    	public boolean contains(int key) {
	    		return m.containsKey(key);
	    	}

	    	public void hit(int key) {
	    		int index = m.get(key);
	    		heap[index].time = time;
	    		heapDown(index);
	    	}

	    	public int get(int key) {
	    		return heap[m.get(key)].value;
	    	}

	    	public void update(int key, int value) {
	    		int index = m.get(key);
	    		heap[index].value = value;
	    		heap[index].time = time;
	    		heapDown(index);
	    	}

	    	public boolean full() {
	    		return count == heap.length;
	    	}

	    	public void removeMin() {
	    		m.remove(heap[0].key);
	    		count--;
	    		if (count > 0) {
		    		heap[0] = heap[count];
		    		m.put(heap[0].key, 0);
		    		heapDown(0);
		    	}
	    	}

	    	public void insert(int key, int value) {
	    		heap[count] = new HeapItem(key, value, time);
	    		m.put(key, count);
	    		count++;
	    		heapUp(count - 1);
	    	}

	    	private void heapDown(int index) {
	    		while (index < count) {
		    		int right = getRightChild(index);
		    		int left = getLeftChild(index);
		    		int min = right < count
		    			? (heap[left].time < heap[right].time ? left : right)
		    			: (left < count ? left : -1);
		    		if (min == -1)
		    			break;
		    		if (heap[index].time > heap[min].time) {
		    			swap(index, min);
		    			index = min;
		    		}
		    		else break;
		    	}
	    	}

	    	private void heapUp(int index) {
	    		int parent = getParent(index);
	    		while (index > 0 && heap[parent].time > heap[index].time) {
	    			swap(parent, index);
	    			index = parent;
	    			parent = getParent(index);
	    		}
	    	}

	    	private void swap(int x, int y) {
	    		HeapItem temp = heap[x];
	    		heap[x] = heap[y];
	    		heap[y] = temp;
	    		m.put(heap[x].key, x);
	    		m.put(heap[y].key, y);
	    	}

	    	private int getParent(int index) { return (index - 1) / 2; }

	    	private int getLeftChild(int index) { return 2 * index + 1; }

	    	private int getRightChild(int index) { return 2 * index + 2; }

	    	@Override
	    	public String toString() {
	    		String hs = new String();
	    		for (int j = 0; j < count; j++)
	    			hs = hs + String.format("[%d %d %d] ", heap[j].key, heap[j].value, heap[j].time);
	    		return String.format("Heap: %s", hs);
	    	}

	    }

	    static class HeapItem {
	    	int key, value, time;

	    	public HeapItem(int key, int value, int time) {
	    		this.key = key;
	    		this.value = value;
	    		this.time = time;
	    	}
	    }

	}
}
