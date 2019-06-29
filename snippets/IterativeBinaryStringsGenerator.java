import java.util.Stack;

class BinaryStringsActivationRecord {
	public String permutation;
	public int status;

	public BinaryStringsActivationRecord(String s, int status) {
		this.permutation = s;
		this.status = status;
	}
}

public class IterativeBinaryStringsGenerator {
	
	private static int binaryStrings(int length) {
		BinaryStringsActivationRecord current = new BinaryStringsActivationRecord("", 0);
		Stack<BinaryStringsActivationRecord> s = new Stack<BinaryStringsActivationRecord>();
		Integer count = 0;
		while (current != null) {
			if (current.permutation.length() == length) {
				System.out.println(current.permutation);
				count++;
				current = s.size() > 0 ? s.pop() : null;
			}
			else
			if (current.status == 0) {
				s.push(new BinaryStringsActivationRecord(current.permutation, 1));
				current = new BinaryStringsActivationRecord(current.permutation + "0", 0);
			}
			else
			if (current.status == 1) {
				current = new BinaryStringsActivationRecord(current.permutation + "1", 0);
			}
		}
		return count;
	}

	public static void main(String[] args) {
		if (args.length != 1) {
			System.err.println("Usage: java IterativeBinaryStringsGenerator [length].");
		}
		else {
			System.out.println(binaryStrings(Integer.parseInt(args[0])));
		}
	}

}
