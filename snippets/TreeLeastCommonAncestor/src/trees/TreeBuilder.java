package trees;

import java.util.List;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

import trees.EdgesTree.Edge;

public class TreeBuilder {

	private final static Random random = new Random();
	private static int size;

	private static Tree<Integer> _getRandomTree(int order, int height, int maxHeight) {
		if (height == maxHeight) {
			return new TopDownTree<Integer>(size++, null);
		}
		int childrenCount = 1 + random.nextInt(order);
		List<Tree<Integer>> children = new ArrayList<Tree<Integer>>(childrenCount);
		for (int j = 0; j < childrenCount; j++) {
			children.add(_getRandomTree(order, height + 1, maxHeight));
		}
		return new TopDownTree<Integer>(size++, children);
	}

	public static Tree<Integer> getRandomTree(int order, int maxHeight) {
		if (order < 1 || maxHeight < 1) {
			return new TopDownTree<Integer>(0, null);
		}
		size = 0;
		return _getRandomTree(order, 0, maxHeight);
	}

	@SuppressWarnings("unchecked")
	public static Tree<String> getSampleTopDownTree() {
		return new TopDownTree<String>("A", Arrays.<Tree<String>>asList(new Tree[] {
			new TopDownTree<String>("B", Arrays.<Tree<String>>asList(new Tree[] {
				new TopDownTree<String>("D", null),
				new TopDownTree<String>("E", Arrays.<Tree<String>>asList(new Tree[] {
					new TopDownTree<String>("K", null)
				})),
				new TopDownTree<String>("F", null)
			})),
			new TopDownTree<String>("C", Arrays.<Tree<String>>asList(new Tree[] {
				new TopDownTree<String>("G", Arrays.<Tree<String>>asList(new Tree[] {
					new TopDownTree<String>("H", null),
					new TopDownTree<String>("I", null)
				})),
				new TopDownTree<String>("J", null)
			}))
		}));
	}

	@SuppressWarnings("unchecked")
	public static Tree<String> getSampleEdgesTree() {
		Edge<String>[] edges = new Edge[] {
			new Edge<String>("A", "B"),
			new Edge<String>("A", "C"),

			new Edge<String>("B", "D"),
			new Edge<String>("B", "E"),
			new Edge<String>("B", "F"),

			new Edge<String>("E", "K"),

			new Edge<String>("C", "G"),
			new Edge<String>("C", "J"),

			new Edge<String>("G", "H"),
			new Edge<String>("G", "I")
		};
		return EdgesTree.create("A", Arrays.asList(edges));
	}

}
