import java.io.*;
import java.lang.*;
import java.util.*;

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
            String s = in.next();
            out.println(s.substring(0, s.length() - 8));
		}
	}

	static class InputReader {
        
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
	
}
