import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

import rmq.SparseTreeRMQ;
import trees.Tree;

public class RMQLCA<T extends Comparable<T>> {

	private static class TourItem<E> implements Comparable<TourItem<E>> {
		private E value;
		private int height;

		public TourItem(E value, int height) {
			this.value = value;
			this.height = height;
		}

		@Override
		public int compareTo(TourItem<E> o) {
			return Integer.compare(height, o.height);
		}
	}
	
	private Map<T, Integer> index;
	private List<TourItem<T>> tour;
	private SparseTreeRMQ<TourItem<T>> rmq;

	public RMQLCA(Tree<T> tree) {
		tour = new ArrayList<TourItem<T>>();
		index = new HashMap<T, Integer>();
		_eulerTour(tree, 0);
		rmq = new SparseTreeRMQ<TourItem<T>>(tour);
	}

    private void _eulerTour(Tree<T> tree, int height) {
		if (!index.containsKey(tree.value())) {
			index.put(tree.value(), tour.size());
		}
		tour.add(new TourItem<T>(tree.value(), height));
		for (Tree<T> child: tree) {
			_eulerTour(child, height + 1);
			tour.add(new TourItem<T>(tree.value(), height));
		}
	}

	public T lca(T u, T v) {
		if (!index.containsKey(u)) {
			throw new NoSuchElementException("u");
		}
		if (!index.containsKey(v)) {
			throw new NoSuchElementException("v");
		}
		return _lgSearch(u, v);
    }

	private T _lgSearch(T u, T v) {
		if (index.get(u) > index.get(v)) {
			T tmp = u;
			u = v;
			v = tmp;
		}
		return rmq.query(index.get(u), index.get(v)).value;
	}

	@SuppressWarnings("unused")
	private T _linearSearch(T u, T v) {
		if (index.get(u) > index.get(v)) {
			T tmp = u;
			u = v;
			v = tmp;
		}
		TourItem<T> min = null;
		for (int j = index.get(u); j <= index.get(v); j++) {
			if (min == null || tour.get(j).height < min.height) {
				min = tour.get(j);
			}
		}
		return min.value;
	}

}
