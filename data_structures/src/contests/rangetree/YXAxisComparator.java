package contests.rangetree;

import java.util.Comparator;

public class YXAxisComparator implements Comparator<_2DPointEx> {

    @Override
    public int compare(_2DPointEx o1, _2DPointEx o2) {
        int cr = Integer.compare(o1.point.y, o2.point.y);
        if (cr != 0) {
            return cr;
        }
        return Integer.compare(o1.point.x, o2.point.x);
    }
    
}
