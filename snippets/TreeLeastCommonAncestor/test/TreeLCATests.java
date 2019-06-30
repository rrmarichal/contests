import java.util.Random;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import trees.Tree;
import trees.TreeBuilder;

public class TreeLCATests {

    @Test
    public void test0() {
		Tree<String> tree = TreeBuilder.getSampleTopDownTree();
		RMQLCA<String> rmq = new RMQLCA<String>(tree);
		AdHocLCA<String> adhoc = new AdHocLCA<String>(tree);
		Assertions.assertEquals(adhoc.lca("B", "C"), rmq.lca("B", "C"));
		Assertions.assertEquals(adhoc.lca("K", "F"), rmq.lca("K", "F"));
		Assertions.assertEquals(adhoc.lca("H", "J"), rmq.lca("H", "J"));
		Assertions.assertEquals(adhoc.lca("D", "I"), rmq.lca("D", "I"));
	}
	
	@Test
    public void test1() {
		Tree<String> tree = TreeBuilder.getSampleEdgesTree();
		RMQLCA<String> rmq = new RMQLCA<String>(tree);
		AdHocLCA<String> adhoc = new AdHocLCA<String>(tree);
		Assertions.assertEquals(adhoc.lca("B", "C"), rmq.lca("B", "C"));
		Assertions.assertEquals(adhoc.lca("K", "F"), rmq.lca("K", "F"));
		Assertions.assertEquals(adhoc.lca("H", "J"), rmq.lca("H", "J"));
		Assertions.assertEquals(adhoc.lca("D", "I"), rmq.lca("D", "I"));
	}
	
	@Test
	public void randomTreeTests() {
		final int T = 100;
		final Random random = new Random();
		for (int t = 0; t < T; t++) {
			Tree<Integer> tree = TreeBuilder.getRandomTree(2, 14);

			RMQLCA<Integer> rmq = new RMQLCA<Integer>(tree);
			AdHocLCA<Integer> adhoc = new AdHocLCA<Integer>(tree);

			int u = random.nextInt(tree.size()),
				v = random.nextInt(tree.size());

			Assertions.assertEquals(
				adhoc.lca(u, v),
				rmq.lca(u, v)
			);
		}
	}

}
