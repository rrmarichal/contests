import java.util.Random;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

interface Action<T> {
    void execute(T arg);
}

class BinaryTree {
    int value;
    BinaryTree left, right;

    public BinaryTree(int value) {
        this.value = value;
    }

    public BinaryTree(int value, BinaryTree left, BinaryTree right) {
        this.value = value;
        this.left = left;
        this.right = right;
    }
}

public class ParallelTreeWalk {
    
	public static void main(String[] args) throws InterruptedException {
		BinaryTree tree = new BinaryTree(0,
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
		
		walk(tree, new Action<Integer>() {
			@Override
			public void execute(Integer arg) {
				System.out.println(arg);
			}
		});
	}
	
	private static void walk(final BinaryTree tree, final Action<Integer> action) throws InterruptedException {
		if (tree == null) return;
		
		action.execute(tree.value);
		Thread.sleep(Math.abs(new Random().nextLong()) % 5000);
		
		Thread leftThread = new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					walk(tree.left, action);
				}
				catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		});
		Thread rightThread = new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					walk(tree.right, action);
				}
				catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		});
		
		leftThread.start();
		rightThread.start();
		
		rightThread.join();
		leftThread.join();
	}
}
