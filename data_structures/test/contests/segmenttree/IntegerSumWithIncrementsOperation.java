package contests.segmenttree;

public class IntegerSumWithIncrementsOperation implements SegmentTreeOperation<Integer> {

    @Override
    public Integer aggregate(Integer left, Integer right) {
        return left + right;
    }

    /**
     * Define the update operation as incrementing the current value.
     */
    @Override
    public Integer update(Integer current, Integer update) {
        return current + update;
    }
    
    @Override
    public Integer nil() {
        return 0;
    }

}
