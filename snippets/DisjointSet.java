import java.util.*;

public class DisjointSet {

    private List<Integer> parent, rank;

    public DisjointSet(int N) {
        parent = Arrays.asList(new Integer[N]);
        rank = Arrays.asList(new Integer[N]);
        Collections.fill(parent, -1);
        Collections.fill(rank, 0);
    }

    public int head(int item) {
        if (parent.get(item) == -1)
            return item;
        parent.set(item, head(parent.get(item)));
        return parent.get(item);
    }

    public void merge(int a, int b) {
        int ra = head(a), rb = head(b);
        if (ra == rb)
            return;
        if (rank.get(ra) < rank.get(rb))
            parent.set(ra, rb);
        else {
            parent.set(rb, ra);
            if (rank.get(ra) == rank.get(rb))
                rank.set(ra, rank.get(ra) + 1);
        }
    }
    
}
