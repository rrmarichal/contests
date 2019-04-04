package priorityqueue;

public class QueueItem<T> {

    public int key;
    public T value;

    public QueueItem(int key, T value) {
        this.key = key;
        this.value = value;
    }
    
}
