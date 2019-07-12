package rangetree;

import java.util.Comparator;

public class XYAxisComparator implements Comparator<_2DPoint> {

    @Override
    public int compare(_2DPoint o1, _2DPoint o2) {
        int cr = Integer.compare(o1.x, o2.x);
        if (cr != 0) {
            return cr;
        }
        return Integer.compare(o1.y, o2.y);
    }
    
}
