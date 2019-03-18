import java.util.Stack;

class FactorialActivationRecord {
	public int status;
	public int n;

	public FactorialActivationRecord(int n, int status) {
		this.n = n;
		this.status = status;
	}
}

public class IterativeSimlatedFactorial {

	public static void main(String[] args) {
		Integer s = iterativeFactorial(5);
		System.out.println(s);
		//System.out.println(recursiveFactorial(10));
	}

	@SuppressWarnings("unused")
	private static int recursiveFactorial(int n) {
		if (n < 1) return 1;
		return n * recursiveFactorial(n - 1);
	}
	
	private static int iterativeFactorial(int n) {
		Stack<FactorialActivationRecord> s = new Stack<FactorialActivationRecord>();
		FactorialActivationRecord current = new FactorialActivationRecord(n, 0);
		Integer factorial = 1;

		while (current != null) {
			if (current.n > 1 && current.status == 0) {
				s.push(new FactorialActivationRecord(current.n, 1));
				current.n--;
			}
			else {
				factorial = current.n * factorial;
				current = s.size() > 0 ? s.pop() : null;
			}
		}
		
		return factorial;
    }
    
}
