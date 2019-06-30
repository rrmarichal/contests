package trees;

import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;

/**
 * A tree representation described by its edges. The root is fixed to
 * provide the context for the LCA operations.
 */
public class EdgesTree<T extends Comparable<T>> {

	public static class Edge<E> {
		private E u, v;

		public Edge(E u, E v) {
			this.u = u;
			this.v = v;
		}

		public E U() {
			return u;
		}

		public E V() {
			return v;
		}
	}

	private static class EdgePtr<E> {
		private E v;
		private Integer next;

		public EdgePtr(E v, Integer next) {
			this.v = v;
			this.next = next;
		}
	}

	private static class TreeNode<E extends Comparable<E>> implements Tree<E> {

		private static class TreeNodeIterator<F extends Comparable<F>> implements Iterator<Tree<F>> {
			private F value;
			private EdgesTree<F> edgesTree;
			private EdgePtr<F> itr;

			public TreeNodeIterator(F value, EdgesTree<F> edgesTree) {
				this.value = value;
				this.edgesTree = edgesTree;
				itr = edgesTree.next[edgesTree.last.get(value)];
				if (_parent(itr)) {
					itr = _move(itr);
				}
			}

			private boolean _parent(EdgePtr<F> itr) {
				return edgesTree.parents.get(value) != null && itr.v.compareTo(edgesTree.parents.get(value)) == 0;
			}
	
			private EdgePtr<F> _move(EdgePtr<F> itr) {
				if (itr.next == null) {
					return null;
				}
				itr = edgesTree.next[itr.next];
				if (_parent(itr)) {
					return _move(itr);
				}
				return itr;
			}

			@Override
			public boolean hasNext() {
				return itr != null;
			}
	
			@Override
			public Tree<F> next() {
				if (itr == null) {
					throw new NoSuchElementException();
				}
				Tree<F> result = new TreeNode<F>(itr.v, edgesTree);
				itr = _move(itr);
				return result;
			}
		}
		
		private E value;
		private EdgesTree<E> edgesTree;

		public TreeNode(E value, EdgesTree<E> edgesTree) {
			this.value = value;
			this.edgesTree = edgesTree;
		}

		@Override
		public Iterator<Tree<E>> iterator() {
			return new TreeNodeIterator<E>(value, edgesTree);
		}

		@Override
		public E value() {
			return value;
		}

		@Override
		public int size() {
			return edgesTree.parents.size();
		}

	}
	
	private HashMap<T, Integer> last;
	private EdgePtr<T>[] next;
	private HashMap<T, T> parents;

	public static <E extends Comparable<E>> Tree<E> create(E root, List<Edge<E>> edges) {
		EdgesTree<E> edgesTree = new EdgesTree<E>(root, edges);
		return new TreeNode<E>(root, edgesTree);
	}

	@SuppressWarnings("unchecked")
	protected EdgesTree(T root, List<Edge<T>> edges) {
		last = new HashMap<T, Integer>();
		next = new EdgePtr[edges.size() << 1];
		parents = new HashMap<T, T>();
		for (int j = 0; j < edges.size(); j++) {
			Edge<T> e = edges.get(j);
			next[j << 1] = new EdgePtr<T>(e.V(), last.get(e.U()));
			last.put(e.U(), j << 1);
			next[(j << 1) + 1] = new EdgePtr<T>(e.U(), last.get(e.V()));
			last.put(e.V(), (j << 1) + 1);
		}
		_dfs(root, null);
	}

	private void _dfs(T current, T parent) {
		parents.put(current, parent);
		EdgePtr<T> itr = next[last.get(current)];
		while (itr != null) {
			if (parent == null || itr.v.compareTo(parent) != 0) {
				_dfs(itr.v, current);
			}
			if (itr.next == null) {
				break;
			}
			itr = next[itr.next];			
		}
	}

}
