package contests.ioi;

import java.io.PrintWriter;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Scanner;

class FenwickTree {
    
    private static final boolean CHECKED = false;

    private long[] tree;
    private long total;
    
    public static FenwickTree empty(int size) {
        if (CHECKED && size < 0) {
            throw new IllegalArgumentException("Invalid tree size (size < 0).");
        }
        return new FenwickTree(size);
    }

    public static FenwickTree withValue(int size, int value) {
        if (CHECKED && size < 0) {
            throw new IllegalArgumentException("Invalid tree size (size < 0).");
        }
        FenwickTree result = new FenwickTree(size);
        result.init(value);
        return result;
    }

    public static FenwickTree withValues(int[] values) {
        if (CHECKED && values == null) {
            throw new IllegalArgumentException("Invalid argument (values is null).");
        }
        FenwickTree result = new FenwickTree(values.length);
        result.init(values);
        return result;
    }

    /**
     * Internal Fenwick array indices from [1..size].
     */
    private FenwickTree(int size) {
        tree = new long[size + 1];
    }

    private void init(int value) {
        total = (this.tree.length - 1) * value;
        for (int j = 1; j < tree.length; j++) {
            tree[j] = value * (j&-j);
        }
    }

    private void init(int[] values) {
        long[] cummulative = new long[values.length+1];
        for (int j = 1; j < tree.length; j++) {
            cummulative[j] = cummulative[j - 1] + values[j-1];
            tree[j] = cummulative[j] - cummulative[j - (j&-j)];
        }
        total = cummulative[tree.length - 1];
    }

    /**
     * Return the sum of items from 1 to index.
     */
    private long _sum(int index) {
        long sum = 0;
        while (index > 0) {
            sum += tree[index];
            index -= index&-index;
        }
        return sum;
    }

    /**
     * Increment item at index.
     */
    private void _increment(int index, int value) {
        while (index < tree.length) {
            tree[index] += value;
            index += index&-index;
        }
    }

    /**
     * Return the sum of items from low to high.
     */
    public long sum(int low, int high) {
        if (CHECKED && low > high) {
            throw new IllegalArgumentException("Invalid range (low > high).");
        }
        if (CHECKED && low < 0) {
            throw new IllegalArgumentException("Invalid range (low < 0).");
        }
        if (CHECKED && high >= tree.length - 1) {
            throw new IllegalArgumentException("Invalid range (high > length).");
        }
        long ls = (low > 0) ? _sum(low) : 0;
        return _sum(high + 1) - ls;
    }
    
    /**
     * Increment item at <code>index</code> by <code>value</code>.
     */
    public void increment(int index, int value) {
        total += value;
        if (CHECKED && index < 0) {
            throw new IllegalArgumentException("Invalid argument (index < 0).");
        }
        if (CHECKED && index >= tree.length - 1) {
            throw new IllegalArgumentException("Invalid argument (index > length).");
        }
        _increment(index + 1, value);
    }

    public long getTotal() {
        return total;
    }

    public long belowOrEqual(int index) {
        return sum(0, index);
    }

    public long aboveOrEqual(int index) {
        return getTotal() - sum(0, index-1);
    }
    
}

class QueryInfoComparator implements Comparator<QueryInfo> {

    private int sqrtN;

    public QueryInfoComparator(int sqrtN) {
        this.sqrtN = sqrtN;
    }

    @Override
    public int compare(QueryInfo o1, QueryInfo o2) {
        int slot1 = o1.L / sqrtN;
        int slot2 = o2.L / sqrtN;
        if (slot1 == slot2) {
            return Integer.compare(o1.R, o2.R);
        }
        return Integer.compare(slot1, slot2);
    }

}

class QueryInfo { 
    int L, R, index;

    public QueryInfo(int L, int R, int index) {
        this.L = L;
        this.R = R;
        this.index = index;
    }

}

public class App {

    private final static int MAX = (int) 1e6;

	public long[] solve(int[] A, QueryInfo[] queries) {
        int sqrtN = (int) Math.sqrt(A.length);
        // Sort the queries using sqrt(N) slots arrangement.
        Arrays.sort(queries, new QueryInfoComparator(sqrtN));
        FenwickTree tree = null;
        long[] result = new long[queries.length];
        int left = 0, right = 0;
        long inversions = 0;
        for (int j = 0; j < queries.length; j++) {
            if (j == 0 || queries[j].L / sqrtN != queries[j-1].L / sqrtN) {
                // Reset fenwick tree 🙄
                tree = FenwickTree.empty(MAX + 1);
                inversions = 0;
                tree.increment(A[left = right = queries[j].L], 1);
            }
            if (left != queries[j].L) {
                // Move left pointer to the left.
                if (queries[j].L < left) {
                    while (queries[j].L < left) {
                        inversions += tree.belowOrEqual(A[left - 1] - 1);
                        left--;
                        tree.increment(A[left], 1);
                    }
                }
                // Move left pointer to the right.
                else {
                    while (queries[j].L > left) {
                        inversions -= tree.belowOrEqual(A[left] - 1);
                        tree.increment(A[left], -1);
                        left++;
                    }
                }
            }
            // Move the right pointer to reach the query interval right coordinate.
            while (right < queries[j].R) {
                inversions += tree.aboveOrEqual(A[right + 1] + 1);
                right++;
                tree.increment(A[right], 1);
            }
            result[queries[j].index] = inversions;
        }
        return result;
    }
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        PrintWriter pw = new PrintWriter(System.out);
        int N = sc.nextInt();
        int[] A = new int[N];
        for (int j=0; j < N; j++) {
            A[j] = sc.nextInt();
        }
        App app = new App();
        int Q = sc.nextInt();
        QueryInfo[] queries = new QueryInfo[Q];
        for (int j = 0; j < Q; j++) {
            int L = sc.nextInt(), R = sc.nextInt();
            queries[j] = new QueryInfo(L-1, R-1, j);
        }
        long[] inversions = app.solve(A, queries);
        for (long inv: inversions) pw.println(inv);
        pw.close();
        sc.close();
    }

}
