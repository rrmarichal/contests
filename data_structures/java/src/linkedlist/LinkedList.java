package linkedlist;

public class LinkedList<T> {

    private static final boolean CHECKED = true;

    public static class Node<T> {
        T item;
        Node<T> next, prev;

        Node(Node<T> prev, T item, Node<T> next) {
            this.item = item;
            this.next = next;
            this.prev = prev;
        }

        public Node(T item) {
            this.item = item;
        }

        public T getItem() {
            return item;
        }

        public Node<T> getPrev() {
            return prev;
        }

        public Node<T> getNext() {
            return next;
        }
    }

    private transient int size = 0;

    /**
     * Pointer to first node.
     * Invariant: (first == null && last == null) ||
     *            (first.prev == null && first.item != null)
     */
    private transient Node<T> first;

    /**
     * Pointer to last node.
     * Invariant: (first == null && last == null) ||
     *            (last.next == null && last.item != null)
     */
    private transient Node<T> last;

    public static <R> LinkedList<R> create() {
        return new LinkedList<R>();
    }

    /**
     * Create a linked list from the elements in {@code items} input argument.
     */
    public static <R> LinkedList<R> create(R[] items) {
        if (CHECKED && items == null) {
            throw new IllegalArgumentException("Invalid argument (items is null).");
        }
        return new LinkedList<R>(items);
    }

    private LinkedList() {}

    private LinkedList(T[] items) {
        _init(items);
    }

    private void _init(T[] items) {
        for (T item: items) {
            Node<T> newNode = new Node<T>(last, item, null);
            if (last == null) {
                first = newNode;
            }
            else {
                last.next = newNode;
            }
            last = newNode;
        }
        size += items.length;
    }

    public Node<T> first() {
        return first;
    }

    public Node<T> last() {
        return last;
    }

    /**
     * Adds a new element to the end of the list.
     * 
     * @return The new node instance.
     */
    public Node<T> add(T item) {
        final Node<T> l = last;
        final Node<T> newNode = new Node<T>(l, item, null);
        last = newNode;
        if (l == null) {
            first = newNode;
        }
        else {
            l.next = newNode;
        }
        size++;
        return newNode;
    }

    /**
     * Removes the specific node from the list.
     */
    public void remove(Node<T> node) {
        final Node<T> next = node.next;
        final Node<T> prev = node.prev;

        if (prev == null) {
            first = next;
        } else {
            prev.next = next;
            node.prev = null;
        }

        if (next == null) {
            last = prev;
        } else {
            next.prev = prev;
            node.next = null;
        }

        node.item = null;
        size--;
    }

	public int size() {
		return size;
	}

}
