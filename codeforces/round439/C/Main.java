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

    public long nextLong() {
        return Long.parseLong(next());
    }

}

public class Main {

    private static final long MODULE = 998244353;

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
            int r = in.nextInt(), b = in.nextInt(), p = in.nextInt();
            long[][] pascal = table(Math.max(r, Math.max(b, p)));
            long rb = pair(r, b, pascal);
            long rp = pair(r, p, pascal);
            long bp = pair(b, p, pascal);
            long rbrp = rb * rp % MODULE;
            out.println((1 + rb + rp + bp + rbrp + (rb * bp % MODULE) + (rp * bp % MODULE) + (rbrp * bp % MODULE)) % MODULE);
		}

        long[][] table(int m) {
            long[][] result = new long[m+1][m+1];
            result[0][0] = 1;
            for (int r = 1; r <= m; r++)
                for (int c = 0; c <= r; c++) {
                    result[r][c] = result[r-1][c];
                    if (c > 0)
                        result[r][c] = (result[r][c] + result[r-1][c-1]) % MODULE;
                }
            return result;
        }

        long pair(int a, int b, long[][] pascal) {
            if (a > b) {
                int tmp = a;
                a = b;
                b = tmp;
            }
            long result = 0, p = (long) b;
            for (int j = 1; j <= a; j++) {
                result = (result + (pascal[a][j] * p)) % MODULE;
                p = (p * (long)(b-j)) % MODULE;
            }
            return result;
        }
        
	}

}
