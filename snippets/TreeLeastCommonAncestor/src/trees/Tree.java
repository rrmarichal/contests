package trees;

public interface Tree<T extends Comparable<T>> extends Iterable<Tree<T>> {

	T value();

	int size();

}
