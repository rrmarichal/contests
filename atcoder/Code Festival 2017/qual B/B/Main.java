import java.io.*;
import java.lang.*;
import java.util.*;

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

		public Solution(InputReader in, PrintWriter out) {
			this.in = in;
			this.out = out;
		}

		public void solve() {
            HashMap<Integer, Integer> p = new HashMap<Integer, Integer>();
            int N = in.nextInt();
            for (; N > 0; N--) {
                int d = in.nextInt();
                if (p.containsKey(d))
                    p.put(d, p.get(d) + 1);
                else
                    p.put(d, 1);
            }
            int M = in.nextInt();
            for (; M > 0; M--) {
                int d = in.nextInt();
                if (!p.containsKey(d) || p.get(d) == 0) {
                    out.println("NO");
                    return;
                }
                p.put(d, p.get(d) - 1);
            }
            out.println("YES");
		}
	}
	
}
