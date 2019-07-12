package segmenttree;

class IntegerMaximumOperation implements SegmentTreeOperation<Integer> {

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
    public Integer transform(Integer current, Integer value) {
        return current + value;
    }

    @Override
    public Integer nil() {
        return Integer.MIN_VALUE;
    }

}
