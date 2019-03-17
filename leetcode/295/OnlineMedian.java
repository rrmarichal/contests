import java.util.Comparator;
import java.util.TreeSet;
import static java.lang.System.out;

public class OnlineMedian {

	static class Item {
		
		public Item(int id, int num) {
			this.id = id;
			this.num = num;
		}

		public int num, id;
		
		@Override
		public String toString() {
			return String.valueOf(num) + " [" + String.valueOf(id) + "]";
		}
	}
	
	TreeSet<Item> minHeap = new TreeSet<Item>(new Comparator<Item>() {
		@Override
		public int compare(Item o1, Item o2) {
			if (o1.num == o2.num) return o1.id - o2.id;
			return o1.num - o2.num;
		}
	});
	
	TreeSet<Item> maxHeap = new TreeSet<Item>(new Comparator<Item>() {
		@Override
		public int compare(Item o1, Item o2) {
			if (o1.num == o2.num) return o1.id - o2.id;
			return o2.num - o1.num;
		}
	});
	
	int id = 0;
	
	public static void main(String[] args) {
		OnlineMedian om = new OnlineMedian();
		om.addNum(6);
		om.addNum(10);
		om.addNum(2);
		om.addNum(6);
		om.addNum(5);
		out.println(om.findMedian());
		om.addNum(0);
		out.println(om.findMedian());
		
		/*,findMedian(),addNum(6),findMedian(),addNum(3),findMedian(),addNum(1)
		 * findMedian(),addNum(0),findMedian(),addNum(0),findMedian()*/

		/*om.addNum(3);
		out.println(om.findMedian());
		om.addNum(2);
		out.println(om.findMedian());
		om.addNum(1);
		out.println(om.findMedian());
		om.addNum(4);
		out.println(om.findMedian());
		om.addNum(-1);
		out.println(om.findMedian());*/
	}
	
	public double findMedian() {
		if (minHeap.size() > maxHeap.size())
			return minHeap.first().num;
		else
		if (minHeap.size() < maxHeap.size())
			return maxHeap.first().num;
		return (double) (minHeap.first().num + maxHeap.first().num) / 2;
	}
	
	public void addNum(int num) {
		addNum(new Item(id++, num));
	}
	
	private void addNum(Item item) {
		if (minHeap.size() == maxHeap.size()) {
			if (minHeap.size() == 0 || item.num <= minHeap.first().num)
				maxHeap.add(item);
			else
				minHeap.add(item);
		}
		else
		if (minHeap.size() < maxHeap.size()) {
			if (item.num <= maxHeap.first().num) {
				Item x = maxHeap.pollFirst();
				maxHeap.add(item);
				minHeap.add(x);
			}
			else {
				minHeap.add(item);
			}
		}
		else {
			if (item.num >= minHeap.first().num) {
				Item x = minHeap.pollFirst();
				minHeap.add(item);
				maxHeap.add(x);
			}
			else {
				maxHeap.add(item);
			}
		}
	}

}
