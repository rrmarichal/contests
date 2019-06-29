public class TreeLongestPath {
	
	private static Pair<Integer, Integer> longestPath(Tree tree) {
		if (tree.children == null) {
			return new Pair<Integer, Integer>(0, 0);
		}
		int childMax = 0, mh1 = -1, mh2 = -1;
		for (Tree child : tree.children) {
			Pair<Integer, Integer> m = longestPath(child);
			if (m.second > childMax) {
				childMax = m.second;
			}
			if (m.first > mh1) {
				mh2 = mh1;
				mh1 = m.first;
			}
			else if (m.first > mh2) {
				mh2 = m.first;
			}
		}
		return new Pair<Integer, Integer>(Math.max(mh1, mh2) + 1, Math.max(childMax, mh1 + mh2 + 2));
	}

	private static int height(Tree tree) {
		if (tree.children == null) {
			return 1;
		}
		int cmh = 0;
		for (Tree child : tree.children) {
			int ch = height(child); 
			if (ch > cmh) {
				cmh = ch;
			}
		}
		return cmh + 1;
	}

	public static void main(String[] args) {
		Tree tree = TreeBuilder.getSampleTree();
		Pair<Integer, Integer> max = longestPath(tree);
		System.out.println(String.format("Heigth: %d. Longest Path: %d", height(tree), Math.max(max.first, max.second)));
	}

}
