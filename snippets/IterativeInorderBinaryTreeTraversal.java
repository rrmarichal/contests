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

class BinaryTree {
    private int value;
    private BinaryTree left, right;

    public BinaryTree(int value) {
        this.value = value;
    }

    public BinaryTree(int value, BinaryTree left, BinaryTree right) {
        this.value = value;
        this.left = left;
        this.right = right;
    }

    public int getValue() {
        return value;
    }

    public BinaryTree getLeft() {
        return left;
    }

    public BinaryTree getRight() {
        return right;
    }
}

public class IterativeInorderBinaryTreeTraversal {

	public static void main(String[] args) {
		traverse(buildTree());
	}
	
	private static BinaryTree buildTree() {
		return new BinaryTree(0, null, new BinaryTree(1, new BinaryTree(2, new BinaryTree(3), new BinaryTree(4)), new BinaryTree(5, new BinaryTree(6), new BinaryTree(7))));
	}

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
}
