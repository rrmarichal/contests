package sparsetree;

public class NodeInfo<T> implements Comparable<NodeInfo<T>> {
	T value;
	int index;

	public NodeInfo(T value, int index) {
		this.value = value;
		this.index = index;
	}

	@Override
	public int compareTo(NodeInfo<T> o) {
		return Integer.compare(index, o.index);
	}

	@Override
	public boolean equals(Object other) {
		return other instanceof NodeInfo && compareTo((NodeInfo<T>) other) == 0;
	}
}
