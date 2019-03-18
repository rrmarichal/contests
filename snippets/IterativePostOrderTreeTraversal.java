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

	public static void main(String[] args) {
		Tree t = Tree.getRandomTree();
		postOrder(t);
	}

	private static void postOrder(Tree t) {
		Stack<TraversalActivationRecord> stack = new Stack<TraversalActivationRecord>();
		stack.push(new TraversalActivationRecord(t, 0));
		
		while (!stack.empty()) {
			TraversalActivationRecord current = stack.pop();
			if (current.node.children == null || current.node.children.size() == 0 || current.nextChild == current.node.children.size()) {
				System.out.println(current.node.data);
			}
			else {
				stack.push(new TraversalActivationRecord(current.node, current.nextChild + 1));
				stack.push(new TraversalActivationRecord(current.node.children.get(current.nextChild), 0));
			}
		}
	}

}
