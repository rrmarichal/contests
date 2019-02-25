package rangetree;

public class RangeTree {

    private static final boolean CHECKED = true;
    
    class Point {
        public int x, y;

        public Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }

    public static RangeTree create(Point[] points) {
        if (CHECKED && points == null) {
            throw new IllegalArgumentException("Invalid argument (points is null).");
        }
        return new RangeTree(points);
    }

    private RangeTree(Point[] points) {
        _buildTree(points);
    }

    private void _buildTree(Point[] points) {
    }

}
