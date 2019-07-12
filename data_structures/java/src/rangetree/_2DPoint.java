package rangetree;

public class _2DPoint {

    public int x, y;
    public Object data;

    public _2DPoint(int x, int y) {
        this.x = x;
        this.y = y;
    }

    public _2DPoint(int x, int y, Object data) {
        this(x, y);
        this.data = data;
    }
    
}
