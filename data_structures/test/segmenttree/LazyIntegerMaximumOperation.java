package segmenttree;

class LazyIntegerMaximumOperation implements LazySegmentTreeOperation<Integer> {

    @Override
    public Integer aggregate(Integer left, Integer right) {
        if (right == null) {
            return left;
        }
        if (left == null) {
            return right;
        }
        return Math.max(left, right);
    }

    @Override
    public Integer transformLazy(Integer current, Integer value) {
        return current + value;
    }

    @Override
    public Integer transformTree(Integer current, Integer value, int size) {
        return current + value;
    }

    @Override
    public Integer nil() {
        return Integer.MIN_VALUE;
    }

    @Override
    public Integer zero() {
        return 0;
    }

}
