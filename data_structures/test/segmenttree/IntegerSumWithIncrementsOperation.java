package segmenttree;

public class IntegerSumWithIncrementsOperation implements SegmentTreeOperation<Integer> {

    @Override
    public Integer aggregate(Integer left, Integer right) {
        return left + right;
    }

    /**
     * Define the update operation as incrementing the current value.
     */
    @Override
    public Integer transform(Integer current, Integer value) {
        return current + value;
    }
    
    @Override
    public Integer nil() {
        return 0;
    }

}
