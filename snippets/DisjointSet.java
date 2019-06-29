import java.util.*;

/**
 * Disjoint set implementation with path compression and ranks.
 */
public class DisjointSet {

    private List<Integer> parent, rank;

    public DisjointSet(int N) {
        parent = Arrays.asList(new Integer[N]);
        rank = Arrays.asList(new Integer[N]);
        Collections.fill(parent, -1);
        Collections.fill(rank, 0);
    }

    public int head(int item) {
        if (parent.get(item) == -1) {
			return item;
		}
        parent.set(item, head(parent.get(item)));
        return parent.get(item);
    }

    public void merge(int a, int b) {
        int ha = head(a), hb = head(b);
        if (ha == hb) {
			return;
		}
        if (rank.get(ha) < rank.get(hb)) {
			parent.set(ha, hb);
		}
        else {
            parent.set(hb, ha);
            if (rank.get(ha) == rank.get(hb))
                rank.set(ha, rank.get(ha) + 1);
        }
    }
    
}
