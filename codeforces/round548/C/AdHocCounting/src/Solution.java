import java.io.*;
import java.util.*;

public class Solution {

    private final long MODULO = (long) 1e9 + 7;
    
    private int N, K;
    private ArrayList<ArrayList<NodeInfo>> tree;
    private boolean[] visited;

    protected Solution(InputReader in) {
        N = in.nextInt();
        K = in.nextInt();
        tree = new ArrayList<ArrayList<NodeInfo>>(N);
        for (int j = 0; j < N; j++) {
            tree.add(new ArrayList<NodeInfo>());
        }
        for (int j = 0; j < N - 1; j++) {
            int a = in.nextInt(), b = in.nextInt(), color = in.nextInt();
            a--; b--;
            tree.get(a).add(new NodeInfo(b, color));
            tree.get(b).add(new NodeInfo(a, color));
        }
    }

    private long modPow(int a, int b) {
        long p = 1;
        for (int j = 0; j < b; j++) {
            p = (p * a) % MODULO;
        }
        return p;
    }

    private int dfs(int current) {
        visited[current] = true;
        int size = 1;
        for (NodeInfo next: tree.get(current)) {
            if (!visited[next.node] && next.color == 0) {
                size += dfs(next.node);
            }
        }
        return size;
    }

    public long solve() {
        long count = 0;
        visited = new boolean[N];
        for (int j = 0; j < N; j++) {
            if (!visited[j]) {
                int cs = dfs(j);
                count = (count + modPow(cs, K)) % MODULO;
            }
        }
        return (modPow(N, K) + MODULO - count) % MODULO;
    }

    public static void main(String[] args) {
        InputReader in = new InputReader(System.in);
        PrintWriter out = new PrintWriter(System.out);
        Solution solution = new Solution(in);
        out.println(solution.solve());
        out.close();
    }

}

class NodeInfo {
    int node, color;

    public NodeInfo(int node, int color) {
        this.node = node;
        this.color = color;
    }
}

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
