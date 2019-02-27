package contests.segmenttree;

class IntegerSumWithUpdatesOperation implements SegmentTreeOperation<Integer> {

    @Override
    public Integer aggregate(Integer left, Integer right) {
        return left + right;
    }

    /**
     * Define the update operation as setting the current value.
     */
    @Override
    public Integer update(Integer current, Integer update) {
        return update;
    }
    
    @Override
    public Integer nil() {
        return 0;
    }

}
