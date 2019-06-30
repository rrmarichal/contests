import java.util.HashMap;
import java.util.NoSuchElementException;

import trees.Tree;

public class AdHocLCA<T extends Comparable<T>> {

	private HashMap<T, Tree<T>> parents;
	private HashMap<T, Integer> heights;

	public AdHocLCA(Tree<T> tree) {
		parents = new HashMap<T, Tree<T>>();
		heights = new HashMap<T, Integer>();
		_dfs(tree, 0, null);
	}

    private void _dfs(Tree<T> tree, int height, Tree<T> parent) {
		parents.put(tree.value(), parent);
		heights.put(tree.value(), height);
		for (Tree<T> child: tree) {
			_dfs(child, height + 1, tree);
		}
	}

	public T lca(T u, T v) {
		if (!parents.containsKey(u)) {
			throw new NoSuchElementException("u");
		}
		if (!parents.containsKey(v)) {
			throw new NoSuchElementException("v");
		}
		// Walk u and v up to the level of the one that's upper in the tree.
		while (heights.get(u).compareTo(heights.get(v)) > 0) {
			u = parents.get(u).value();
		}
		while (heights.get(v).compareTo(heights.get(u)) > 0) {
			v = parents.get(v).value();
		}
		// Walk up to their common parent.
		while (u.compareTo(v) != 0) {
			u = parents.get(u).value();
			v = parents.get(v).value();
		}
		return u;
    }

}
