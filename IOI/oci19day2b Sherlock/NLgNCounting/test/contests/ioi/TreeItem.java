package contests.ioi;

class TreeItem implements Comparable<TreeItem> {

    private int value;

    public TreeItem(int value) {
        this.value = value;
    }

    public int compareTo(TreeItem other) {
        return Integer.compare(value, other.value);
    }

    public int getValue() {
        return value;
    }

}
