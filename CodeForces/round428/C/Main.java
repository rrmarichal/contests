import java.io.*;
import java.lang.*;
import java.util.*;

class Edge {
    int node, previous;

    public Edge(int node, int previous) {
        this.node = node;
        this.previous = previous;
    }
}

class InputReader {
    
    public BufferedReader reader;
    public StringTokenizer tokenizer;

    public InputReader(InputStream stream) {
        reader = new BufferedReader(new InputStreamReader(stream), 32768);
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

}

public class Main {

	public static void main(String[] args) throws IOException {
		InputReader in = new InputReader(System.in);
        PrintWriter out = new PrintWriter(System.out);
        Solution task = new Solution(in, out);
        task.solve();
        out.close();
	}

	static class Solution {
		
		private InputReader in;
		private PrintWriter out;
        double result = 0;

		public Solution(InputReader in, PrintWriter out) {
			this.in = in;
			this.out = out;
		}

		public void solve() {
            int n = in.nextInt();
            if (n == 1) {
                out.println(0);
                return;
            }
            int[] last = new int[n];
            for (int j = 0; j < n; j++)
                last[j] = -1;
            Edge[] tree = new Edge[2 * n - 2];
            for (int j = 0; j < n-1; j++) {
                int a = in.nextInt(), b = in.nextInt();
                a--; b--;
                tree[2*j] = new Edge(b, last[a]);
                tree[2*j + 1] = new Edge(a, last[b]);
                last[a] = 2*j;
                last[b] = 2*j + 1;
            }
            dfs(-1, 0, 0, 1, tree, last);
            out.println(result);
		}

        void dfs(int parent, int v, int l, double p, Edge[] tree, int[] last) {
            Edge node = tree[last[v]];
            int range = 0;
            while (true) {
                if (node.node != parent)
                    range++;
                if (node.previous == -1)
                    break;
                node = tree[node.previous];
            }
            // v is a leaf
            if (range == 0) {
                result += p * l;
            }
            else {
                node = tree[last[v]];
                while (true) {
                    if (node.node != parent)
                        dfs(v, node.node, l + 1, p / range, tree, last);
                    if (node.previous == -1)
                        break;
                    node = tree[node.previous];
                }
            }
        }

	}
	
}
