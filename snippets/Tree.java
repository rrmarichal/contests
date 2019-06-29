public class Tree {

	protected int value, childrenCount;
	protected Tree[] children;

	public Tree(int value) {
		this.value = value;
	}

	public Tree(int value, Tree[] children) {
		this.value = value;
		this.children = children;
	}

	public int getValue() {
		return value;
	}
	
	public Tree[] getChildren() {
		return children;
	}
	
}
