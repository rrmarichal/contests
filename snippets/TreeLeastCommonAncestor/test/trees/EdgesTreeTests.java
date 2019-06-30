package trees;

import java.util.Iterator;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import trees.Tree;
import trees.TreeBuilder;

public class EdgesTreeTests {

    @Test
    public void test0() {
		Tree<String> tree = TreeBuilder.getSampleEdgesTree();
		Iterator<Tree<String>> itr = tree.iterator();
		Assertions.assertTrue(itr.hasNext());
		Assertions.assertEquals("C", itr.next().value());
		Assertions.assertTrue(itr.hasNext());
		Assertions.assertEquals("B", itr.next().value());
		Assertions.assertFalse(itr.hasNext());
	}
	
	@Test
    public void test1() {
		Tree<String> tree = TreeBuilder.getSampleEdgesTree();
		Iterator<Tree<String>> itr = tree.iterator();
		Tree<String> c = itr.next();
		Iterator<Tree<String>> citr = c.iterator();
		Assertions.assertTrue(citr.hasNext());
		Assertions.assertEquals("J", citr.next().value());
		Assertions.assertTrue(citr.hasNext());
		Assertions.assertEquals("G", citr.next().value());
		Assertions.assertFalse(citr.hasNext());
	}
	
	@Test
    public void test2() {
		Tree<String> tree = TreeBuilder.getSampleEdgesTree();
		Iterator<Tree<String>> itr = tree.iterator();
		Tree<String> c = itr.next();
		Iterator<Tree<String>> citr = c.iterator();
		Tree<String> j = citr.next();
		Iterator<Tree<String>> jitr = j.iterator();
		Assertions.assertFalse(jitr.hasNext());
    }

	@Test
	public void sizeTest() {
		Tree<String> tree = TreeBuilder.getSampleEdgesTree();
		Assertions.assertEquals(11, tree.size());
	}

}
