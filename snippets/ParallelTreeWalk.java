import java.util.Random;

interface Action<T> {
    void execute(T arg);
}

public class ParallelTreeWalk {
    
	private static void walk(final BinaryTree tree, final Action<Integer> action) throws InterruptedException {
		action.execute(tree.getValue());
		Thread.sleep(Math.abs(new Random().nextLong()) % 3000);

		Thread leftThread = new Thread(() -> {
			try {
				if (tree.getLeft() != null) {
					walk(tree.getLeft(), action);
				}
			}
			catch (InterruptedException e) {
				e.printStackTrace();
			}
		});
		Thread rightThread = new Thread(() -> {
			try {
				if (tree.getRight() != null) {
					walk(tree.getRight(), action);
				}
			}
			catch (InterruptedException e) {
				e.printStackTrace();
			}
		});

		leftThread.start();
		rightThread.start();
		
		rightThread.join();
		leftThread.join();
	}

	public static void main(String[] args) throws InterruptedException {
		BinaryTree tree = TreeBuilder.getSampleBinaryTree();
		walk(tree, arg -> System.out.println(arg));
	}

}
