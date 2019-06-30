package trees;

import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * A tree representationn where each node is given with its children list.
 */
public class TopDownTree<T extends Comparable<T>> implements Tree<T> {

	private static class TopDownTreeIterator<E extends Comparable<E>> implements Iterator<Tree<E>> {

		private TopDownTree<E> tree;
		private int index;

		public TopDownTreeIterator(TopDownTree<E> tree) {
			this.tree = tree;
			this.index = 0;
		}

		@Override
		public boolean hasNext() {
			if (tree.children == null) {
				return false;
			}
			return index < tree.children.size();
		}

		@Override
		public Tree<E> next() {
			if (tree.children == null) {
				throw new NoSuchElementException();
			}
			if (index >= tree.children.size()) {
				throw new NoSuchElementException();
			}
			return tree.children.get(index++);
		}

	}

	private T value;
	private List<Tree<T>> children;

	public TopDownTree(T value, List<Tree<T>> children) {
		this.value = value;
		this.children = children;
	}

	@Override
	public T value() {
		return value;
	}

	@Override
	public Iterator<Tree<T>> iterator() {
		return new TopDownTreeIterator<T>(this);
	}

	@Override
	public int size() {
		if (children == null) {
			return 1;
		}
		return 1 + children.stream().mapToInt(child -> child.size()).sum();
	}

}
