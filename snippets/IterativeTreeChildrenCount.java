import java.util.List;
import java.util.Stack;

class CountActivationRecord {
	public Tree tree;
	public int status;

	public CountActivationRecord(Tree tree, int status) {
		this.tree = tree;
		this.status = status;
	}
}

class Tree {
    public int value, count;
    public Tree[] children;

    public Tree(int value) {
        this.value = value;
    }

    public Tree(int value, Tree[] children) {
        this.value = value;
        this.children = children;
    }
}

public class IterativeTreeChildrenCount {
	public static void main(String[] args) {
        Tree tree = new Tree(0, new Tree[] {
            new Tree(-10),
            new Tree(4, new Tree[] {
                new Tree(1, new Tree[] {
                    new Tree(2, new Tree[] {
                        new Tree(3), new Tree(4)
                    }),
                    new Tree(5, new Tree[] {
                        new Tree(6), new Tree(7)
                    })
                })
            })}
        );
		countNodes(tree);
		System.out.println(tree.count);
	}

	private static int countNodes(Tree tree) {
		CountActivationRecord current = new CountActivationRecord(tree, 0);
		Stack<CountActivationRecord> s = new Stack<CountActivationRecord>();
		
		while (true) {
			if (current.tree.children != null && current.status < current.tree.children.length) {
				s.push(new CountActivationRecord(current.tree, current.status + 1));
				current = new CountActivationRecord(current.tree.children[current.status], 0);
			}
			else {
				current.tree.count++;
				CountActivationRecord tmp = s.size() > 0 ? s.pop() : null;
				if (tmp != null) {
					tmp.tree.count += current.tree.count;
					current = tmp;
				}
				else {
					return current.tree.count;
				}
			}
		}
	}
}
