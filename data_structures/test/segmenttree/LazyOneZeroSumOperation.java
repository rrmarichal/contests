package segmenttree;

/**
 * Initialy all elements in a list are present. Subsequent updates remove elements
 * from the list. Query how many elements remain after each operation.
 * 
 * Increments by -1 simulate the elimination in a specific range and queries return
 * how many are still present.
 */
class LazyOneZeroSumOperation implements LazySegmentTreeOperation<Integer> {

    @Override
    public Integer aggregate(Integer left, Integer right) {
        if (right == null) {
            return left;
        }
        if (left == null) {
            return right;
        }
        return left + right;
    }

    @Override
    public Integer transformLazy(Integer current, Integer value) {
        return current < 0
            ? current
            : current + value;
    }

    @Override
    public Integer transformTree(Integer current, Integer value, int size) {
        return value < 0
            ? 0
            : current + value * size;
    }

    @Override
    public Integer nil() {
        return 0;
    }

    @Override
    public Integer zero() {
        return 0;
    }

}
