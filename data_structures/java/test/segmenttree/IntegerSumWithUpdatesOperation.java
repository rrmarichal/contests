package segmenttree;

class IntegerSumWithUpdatesOperation implements SegmentTreeOperation<Integer> {

    @Override
    public Integer aggregate(Integer left, Integer right) {
        return left + right;
    }

    /**
     * Define the update operation as setting the current value.
     */
    @Override
    public Integer transform(Integer current, Integer value) {
        return value;
    }
    
    @Override
    public Integer nil() {
        return 0;
    }

}
