package priority_queue;

public class QueueIntegerOperation implements PriorityQueueOperation<Integer> {

    private int _default;

    public QueueIntegerOperation(int _default) {
        this._default = _default;
    }

    @Override
    public Integer transform(Integer current, Integer value) {
        return current + value;
    }

    @Override
    public Integer _default() {
        return _default;
    }

}
