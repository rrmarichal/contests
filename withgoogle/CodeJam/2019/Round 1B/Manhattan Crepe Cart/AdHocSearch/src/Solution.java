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

class PersonInfo {
    int x, y;
    String direction;

    public PersonInfo(int x, int y, String direction) {
        this.x = x;
        this.y = y;
        this.direction = direction;
    }
}

class TestInfo {
    int P, Q;
    ArrayList<PersonInfo> vertical, horizontal;

    public TestInfo(int P, int Q, ArrayList<PersonInfo> vertical, ArrayList<PersonInfo> horizontal) {
        this.P = P;
        this.Q = Q;
        this.vertical = vertical;
        this.horizontal = horizontal;
    }
}

public class Solution {

    private int T;
    private TestInfo[] tests;

    protected Solution(InputReader in) {
        T = in.nextInt();
        tests = new TestInfo[T];
        for (int t = 0; t < T; t++) {
            int P = in.nextInt(), Q = in.nextInt();
            ArrayList<PersonInfo> vertical = new ArrayList<PersonInfo>();
            ArrayList<PersonInfo> horizontal = new ArrayList<PersonInfo>();
            for (int j = 0; j < P; j++) {
                int x = in.nextInt(), y = in.nextInt();
                String direction = in.next();
                if (direction.equals("N")) {
                    y++;
                }
                else
                if (direction.equals("S")) {
                    y--;
                }
                else
                if (direction.equals("E")) {
                    x++;
                }
                else {
                    x--;
                }
                PersonInfo p = new PersonInfo(x, y, direction);
                if (direction.equals("N") || direction.equals("S")) {
                    vertical.add(p);
                }
                else {
                    horizontal.add(p);
                }
            }
            tests[t] = new TestInfo(P, Q, vertical, horizontal);
        }
    }

    public String[] solve() {
        String[] result = new String[T];
        for (int t = 0; t < T; t++) {
            int Q = tests[t].Q;
            int max = 0, maxx = 0, maxy = 0;
            for (int x = 0; x <= Q; x++) {
                for (int y = 0; y <= Q; y++) {
                    int head = 0;
                    for (PersonInfo p: tests[t].vertical) {
                        if ((p.direction.equals("S") && p.y >= y) ||
                            (p.direction.equals("N") && p.y <= y)) {
                            head++;
                        }
                    }
                    for (PersonInfo p: tests[t].horizontal) {
                        if ((p.direction.equals("W") && p.x >= x) ||
                            (p.direction.equals("E") && p.x <= x)) {
                            head++;
                        }
                    }
                    if (head > max) {
                        max = head;
                        maxx = x;
                        maxy = y;
                    }
                }   
            }
            result[t] = String.format("Case #%d: %d %d", t + 1, maxx, maxy);
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
