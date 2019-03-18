import java.util.Stack;

class BinaryStringsActivationRecord {
	public String s;
	public int status;

	public BinaryStringsActivationRecord(String s, int status) {
		this.s = s;
		this.status = status;
	}
}

class Pair<T, U> {
    public T first;
    public U second;

    public Pair(T first, U second) {
        this.first = first;
        this.second = second;
    }
}

public class IterativeBinaryStringsGenerator {

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		Pair<Integer, Integer> perf = printBinaryStrings(12);
		System.out.println(perf.first);
		System.out.println(perf.second);
	}

	private static Pair<Integer, Integer> printBinaryStrings(int length) {
		BinaryStringsActivationRecord current = new BinaryStringsActivationRecord("", 0);
		Stack<BinaryStringsActivationRecord> s = new Stack<BinaryStringsActivationRecord>();
		Integer count = 0, max = 0;
		
		while (current != null) {
			if (current.s.length() == length) {
				System.out.println(current.s);
				count++;
			}
			else {
				if (current.status == 0) {
					s.push(new BinaryStringsActivationRecord(current.s, 1));
					current = new BinaryStringsActivationRecord(current.s + "0", 0);
					continue;
				}
				if (current.status == 0 || current.status == 1) {
					s.push(new BinaryStringsActivationRecord(current.s, 2));
					current = new BinaryStringsActivationRecord(current.s + "1", 0);
					continue;
				}
			}
			if (s.size() > max) max = s.size();
			current = s.size() > 0 ? s.pop() : null;
		}
		return new Pair<Integer, Integer>(count, max);
	}
}
