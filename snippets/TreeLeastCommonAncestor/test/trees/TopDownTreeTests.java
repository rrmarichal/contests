package trees;

import java.util.Iterator;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import trees.Tree;
import trees.TreeBuilder;

public class TopDownTreeTests {

    @Test
    public void test0() {
		Tree<String> tree = TreeBuilder.getSampleTopDownTree();
		Iterator<Tree<String>> itr = tree.iterator();
		Assertions.assertTrue(itr.hasNext());
		Assertions.assertEquals("B", itr.next().value());
		Assertions.assertTrue(itr.hasNext());
		Assertions.assertEquals("C", itr.next().value());
		Assertions.assertFalse(itr.hasNext());
	}
	
	@Test
    public void test1() {
		Tree<String> tree = TreeBuilder.getSampleTopDownTree();
		Iterator<Tree<String>> itr = tree.iterator();
		Tree<String> b = itr.next();
		Iterator<Tree<String>> bitr = b.iterator();
		Assertions.assertTrue(bitr.hasNext());
		Assertions.assertEquals("D", bitr.next().value());
		Assertions.assertTrue(bitr.hasNext());
		Assertions.assertEquals("E", bitr.next().value());
		Assertions.assertTrue(bitr.hasNext());
		Assertions.assertEquals("F", bitr.next().value());
		Assertions.assertFalse(bitr.hasNext());
    }

	@Test
	public void sizeTest() {
		Tree<String> tree = TreeBuilder.getSampleTopDownTree();
		Assertions.assertEquals(11, tree.size());
	}
	
}
