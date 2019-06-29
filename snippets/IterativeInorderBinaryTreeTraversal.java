import java.util.Stack;

class InorderActivationRecord {
	private BinaryTree tree;
	private int status;

	public InorderActivationRecord(BinaryTree tree, int status) {
		this.tree = tree;
		this.status = status;
	}
	
	public BinaryTree getTree() {
		return tree;
	}
	
	public int getStatus() {
		return status;
	}
}

public class IterativeInorderBinaryTreeTraversal {

	private static void traverse(BinaryTree tree) {
		InorderActivationRecord current = new InorderActivationRecord(tree, 0); 
		Stack<InorderActivationRecord> stack = new Stack<InorderActivationRecord>();
		
		while (current != null) {
			if (current.getTree().getLeft() != null && current.getStatus() == 0) {
				stack.push(new InorderActivationRecord(current.getTree(), 1));
				current = new InorderActivationRecord(current.getTree().getLeft(), 0);
				continue;
			}
			
			if (current.getStatus() == 0 || current.getStatus() == 1) {
				System.out.println(current.getTree().getValue());
			}
			
			if (current.getTree().getRight() != null && (current.getStatus() == 0 || current.getStatus() == 1)) {
				stack.push(new InorderActivationRecord(current.getTree(), 2));
				current = new InorderActivationRecord(current.getTree().getRight(), 0);
				continue;
			}
			
			current = stack.size() > 0 ? stack.pop() : null;
		}
	}

	public static void main(String[] args) {
		BinaryTree tree = TreeBuilder.getSampleBinaryTree();
		traverse(tree);
	}
	
}
