public class BinaryTree extends Tree {

    public BinaryTree(int value) {
		super(value);
    }

    public BinaryTree(int value, BinaryTree left, BinaryTree right) {
		super(value, new Tree[] { left, right });
    }

    public BinaryTree getLeft() {
        return (BinaryTree) children[0];
    }

    public BinaryTree getRight() {
        return (BinaryTree) children[1];
	}
	
}
