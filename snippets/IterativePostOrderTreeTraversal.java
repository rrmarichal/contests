import java.util.Stack;

class TraversalActivationRecord {
	Tree node;
	int nextChild;
	
	public TraversalActivationRecord(Tree node, int nextChild) {
		this.node = node;
		this.nextChild = nextChild;
	}
}

public class IterativePostOrderTreeTraversal {

	private static void postOrder(Tree t) {
		Stack<TraversalActivationRecord> stack = new Stack<TraversalActivationRecord>();
		stack.push(new TraversalActivationRecord(t, 0));
		while (!stack.empty()) {
			TraversalActivationRecord current = stack.pop();
			if (current.node.children == null || current.node.children.length == 0 || current.nextChild == current.node.children.length) {
				System.out.println(current.node.value);
			}
			else {
				stack.push(new TraversalActivationRecord(current.node, current.nextChild + 1));
				stack.push(new TraversalActivationRecord(current.node.children[current.nextChild], 0));
			}
		}
	}

	public static void main(String[] args) {
		if (args.length != 2) {
			System.err.println("Usage: java IterativePostOrderTreeTraversal [tree_order] [max_height].");
		}
		else {
			Tree t = TreeBuilder.getRandomTree(Integer.parseInt(args[0]), Integer.parseInt(args[1]));
			postOrder(t);
		}
	}

}
