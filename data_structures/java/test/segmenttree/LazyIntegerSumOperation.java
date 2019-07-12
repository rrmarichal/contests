package segmenttree;

class LazyIntegerSumOperation implements LazySegmentTreeOperation<Integer> {

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
        return current + value;
    }

    @Override
    public Integer transformTree(Integer current, Integer value, int size) {
        return current + value * size;
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
