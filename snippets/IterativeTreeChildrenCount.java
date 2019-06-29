import java.util.Stack;

class CountActivationRecord {
	public Tree tree;
	public int visitedChildren;

	public CountActivationRecord(Tree tree, int visitedChildren) {
		this.tree = tree;
		this.visitedChildren = visitedChildren;
	}
}

public class IterativeTreeChildrenCount {

	private static int countNodes(Tree tree) {
		CountActivationRecord current = new CountActivationRecord(tree, 0);
		Stack<CountActivationRecord> s = new Stack<CountActivationRecord>();
		while (current != null) {
			if (current.tree.children != null && current.visitedChildren < current.tree.children.length) {
				s.push(new CountActivationRecord(current.tree, current.visitedChildren + 1));
				current = new CountActivationRecord(current.tree.children[current.visitedChildren], 0);
			}
			else {
				current.tree.childrenCount++;
				CountActivationRecord next = s.size() > 0 ? s.pop() : null;
				if (next != null) {
					next.tree.childrenCount += current.tree.childrenCount;
				}
				current = next;
			}
		}
		return tree.childrenCount;
	}

	public static void main(String[] args) {
		if (args.length != 2) {
			System.err.println("Usage: java IterativeTreeChildrenCount [tree_order] [max_heigth].");
		}
		else {
			Tree tree = TreeBuilder.getRandomTree(Integer.parseInt(args[0]), Integer.parseInt(args[1]));
			System.out.println(countNodes(tree));
		}
	}

}
