import java.io.*;
import java.util.*;

class InputReader {
    private static final int BUFFER_SIZE = 1<<16;

    public BufferedReader reader;
    public StringTokenizer tokenizer;

    public InputReader(InputStream stream) {
        reader = new BufferedReader(new InputStreamReader(stream), BUFFER_SIZE);
        tokenizer = null;
    }

    public String next() {
        while (tokenizer == null || !tokenizer.hasMoreTokens()) {
            try {
                tokenizer = new StringTokenizer(reader.readLine());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        }
        return tokenizer.nextToken();
    }

    public int nextInt() {
        return Integer.parseInt(next());
    }

    public long nextLong() {
        return Long.parseLong(next());
    }
}

class TestInfo {
    int A;
    String[] C;

    public TestInfo(int A, String[] C) {
        this.A = A;
        this.C = C;
    }
}

public class Solution {

    private int T;
    private TestInfo[] tests;

    protected Solution(InputReader in) {
        T = in.nextInt();
        tests = new TestInfo[T];
        for (int t = 0; t < T; t++) {
            int A = in.nextInt();
            String[] C = new String[A];
            for (int j = 0; j < A; j++) {
                C[j] = in.next();
            }
            tests[t] = new TestInfo(A, C);
        }
    }

    private int _defeatWithMove(String[] C, boolean[] defeated, int index, char move) {
        int count = 0;
        for (int j = 0; j < defeated.length; j++) {
            if (!defeated[j] && C[j].charAt(index % C[j].length()) == move) {
                defeated[j] = true;
                count++;
            }
        }
        return count;
    }

    private String _search(int A, String[] C) {
        boolean[] defeated = new boolean[A];
        StringBuilder win = new StringBuilder();
        int count = 0, index = 0;
        while (count < A) {
            int rock = 0, paper = 0, scissors = 0;
            for (int j = 0; j < A; j++) {
                if (!defeated[j]) {
                    if (C[j].charAt(index % C[j].length()) == 'R') {
                        rock++;
                    }
                    else
                    if (C[j].charAt(index % C[j].length()) == 'P') {
                        paper++;
                    }
                    else {
                        scissors++;
                    }
                }
            }
            if (rock > 0 && paper > 0 && scissors > 0) {
                return "IMPOSSIBLE";
            }
            int sum = rock + paper + scissors;
            // Adversaries only play rock at this point -> play paper.
            if (rock == sum) {
                return win.append('P').toString();
            }
            // Adversaries only play paper at this point -> play scissors.
            if (paper == sum) {
                return win.append('S').toString();
            }
            // Adversaries only play scissors at this point -> play rock.
            if (scissors == sum) {
                return win.append('R').toString();
            }
            // We have adversaries at this point playing exactly two different
            // types of moves. Play the winning and keep going with the ties.
            if (rock == 0) {
                // They play paper and scissors -> play scissors.
                win.append('S');
                count += _defeatWithMove(C, defeated, index, 'P');
            }
            else
            if (paper == 0) {
                // They play rock and scissors -> play rock.
                win.append('R');
                count += _defeatWithMove(C, defeated, index, 'S');
            }
            // scissors == 0
            else {
                // They play rock and paper -> play paper.
                win.append('P');
                count += _defeatWithMove(C, defeated, index, 'R');
            }
            index++;
        }
        return win.toString();
    }

    public String[] solve() {
        String[] result = new String[T];
        for (int t = 0; t < T; t++) {
            result[t] = String.format("Case #%d: %s", t + 1, _search(tests[t].A, tests[t].C));
        }
        return result;
    }

    public static void main(String[] args) {
        InputReader in = new InputReader(System.in);
        PrintWriter out = new PrintWriter(System.out);
        Solution solution = new Solution(in);
        String[] result = solution.solve();
        for (String c: result) {
            out.println(c);
        }
        out.close();
    }

}
