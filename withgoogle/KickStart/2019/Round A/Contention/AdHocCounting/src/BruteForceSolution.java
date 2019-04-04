import java.io.*;
import java.util.*;

public class BruteForceSolution {

    private int T, best;
    private TestInfo[] tests;

	public BruteForceSolution(InputReader in) {
        T = in.nextInt();
        tests = new TestInfo[T];
        for (int j = 0; j < T; j++) {
            int N = in.nextInt(), Q = in.nextInt();
            SegmentInfo[] S = new SegmentInfo[Q];
            for (int k = 0; k < Q; k++) {
                S[k] = new SegmentInfo(in.nextInt() - 1, in.nextInt() - 1);
            }
            tests[j] = new TestInfo(N, Q, S);
        }
	}

    private int count(int[] permutation, SegmentInfo[] S, int N) {
        boolean[] taken = new boolean[N];
        int min = Integer.MAX_VALUE;
        for (int j = 0; j < permutation.length; j++) {
            int count = 0;
            for (int k = S[permutation[j]].L; k <= S[permutation[j]].R; k++) {
                if (!taken[k]) {
                    taken[k] = true;
                    count++;
                }
            }
            if (count < min) {
                min = count;
            }
        }
        return min;
    }

    private void combine(int current, int N, int Q, int[] permutation, boolean[] mark, SegmentInfo[] S) {
        if (current == Q) {
            int c = count(permutation, S, N);
            if (c > best) {
                best = c;
            }
        }
        else {
            for (int j = 0; j < Q; j++) {
                if (!mark[j]) {
                    permutation[current] = j;
                    mark[j] = true;
                    combine(current + 1, N, Q, permutation, mark, S);
                    mark[j] = false;
                }
            }
        }
    }

	public String[] solve() {
        String[] result = new String[T];
        for (int t = 0; t < T; t++) {
            int N = tests[t].N, Q = tests[t].Q;
            // Try all possible combinations to sell tickets.
            best = Integer.MIN_VALUE;
            combine(0, N, Q, new int[Q], new boolean[Q], tests[t].S);
            result[t] = String.format("Case #%d: %d", t + 1, best);
        }
		return result;
	}

}
