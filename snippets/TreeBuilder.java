import java.util.Random;

public class TreeBuilder {

	private static final Random random = new Random();

	private static Tree _getRandomTree(int order, int height, int maxHeight) {
		if (height == maxHeight) {
			return new Tree(random.nextInt());
		}
		int childrenCount = random.nextInt(1 + order);
		if (childrenCount > 0) {
			Tree[] children = new Tree[childrenCount];
			for (int j = 0; j < children.length; j++) {
				children[j] = _getRandomTree(order, height + 1, maxHeight);
			}
			return new Tree(random.nextInt(), children);
		}
		return new Tree(random.nextInt());
	}

	private static BinaryTree _getRandomBinaryTree(int height, int maxHeight) {
		if (height == maxHeight) {
			return new BinaryTree(random.nextInt());
		}
		BinaryTree left = random.nextBoolean()
			? _getRandomBinaryTree(height + 1, maxHeight)
			: null;
		BinaryTree right = random.nextBoolean()
			? _getRandomBinaryTree(height + 1, maxHeight)
			: null;
		return new BinaryTree(random.nextInt(), left, right);
	}

	/**
	 * Generate a random {@code order}-ary tree with a maximum height of {@code maxHeight}.
	 */
	public static Tree getRandomTree(int order, int maxHeight) {
		if (order < 1 || maxHeight < 1) {
			return new Tree(random.nextInt());
		}
		return _getRandomTree(order, 0, maxHeight);
	}

	public static BinaryTree getRandomBinaryTree(int maxHeight) {
		if (maxHeight < 1) {
			return new BinaryTree(random.nextInt());
		}
		return _getRandomBinaryTree(0, maxHeight);
	}

	public static Tree getSampleTree() {
		return new Tree(1, new Tree[] {
			new Tree(2, new Tree[] {
				new Tree(5, new Tree[] {
					new Tree(10, new Tree[] {
						new Tree(13, new Tree[] {
							new Tree(17, new Tree[] {
								new Tree(21)
							})
						})
					})
				}),
				new Tree(6, new Tree[] {
					new Tree(11, new Tree[] {
						new Tree(14),
						new Tree(15, new Tree[] {
							new Tree(18)
						})
					})
				}),
				new Tree(7, new Tree[] {
					new Tree(12, new Tree[] {
						new Tree(16, new Tree[] {
							new Tree(19),
							new Tree(20)
						})
					})
				})
			}),
			new Tree(3),
			new Tree(4, new Tree[] { new Tree(8), new Tree(9) })
		});
	}

	public static BinaryTree getSampleBinaryTree() {
		return new BinaryTree(0,
			new BinaryTree(1,
				new BinaryTree(3),
				new BinaryTree(4,
					new BinaryTree(7),
					new BinaryTree(8)
				)
			),
			new BinaryTree(2,
				new BinaryTree(5),
				new BinaryTree(6)
			)
		);
	}

}
