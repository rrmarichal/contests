import java.util.Arrays;

public class TreeLongestPath {
	
	public static void main(String[] args) {
		Tree tree = buildTree1();
		System.out.println("Height: " + height(tree));
		
		Pair<Integer, Integer> max = longestPath(tree);
		System.out.println("LP: " + Math.max(max.first, max.second));
	}

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

	private static Tree buildTree0() {
		return new Tree(1, Arrays.asList(
			new Tree(2),
			new Tree(3, Arrays.asList(
				new Tree(7),
				new Tree(8)
			)),
			new Tree(4),
			new Tree(5),
			new Tree(6)
		));
	}
	
	private static Tree buildTree1() {
		return new Tree(1, Arrays.asList(
			new Tree(2, Arrays.asList(
				new Tree(5, Arrays.asList(
					new Tree(10, Arrays.asList(
						new Tree(13, Arrays.asList(
							new Tree(17, Arrays.asList(
								new Tree(21)))
							))
					))
				)),
				new Tree(6, Arrays.asList(
					new Tree(11, Arrays.asList(
						new Tree(14),
						new Tree(15, Arrays.asList(
							new Tree(18)
						))
					))
				)),
				new Tree(7, Arrays.asList(
					new Tree(12, Arrays.asList(
						new Tree(16, Arrays.asList(
							new Tree(19),
							new Tree(20)
						))
					))
				))
			)),
			new Tree(3),
			new Tree(4, Arrays.asList(new Tree(8), new Tree(9)))
		));
	}
}
