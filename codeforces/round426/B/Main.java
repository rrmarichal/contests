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
            int n = in.nextInt(), k = in.nextInt();
            String guests = in.next();
            int[] guest_count = new int[26];
            int[] entered = new int[26];
            for (int j = 0; j < guests.length(); j++)
                guest_count[guests.charAt(j) - 'A']++;

            for (int j = 0; j < n; j++) {
                int gate = guests.charAt(j) - 'A';
                // A new gate should be guarded.
                if (entered[gate] == 0) {
                    if (k == 0) {
                        out.println("YES");
                        return;
                    }
                    k--;
                }
                entered[gate]++;
                // If all guests that are supposed to enter this gate already entered, then free this guard.
                if (entered[gate] == guest_count[gate])
                    k++;
            }
            out.println("NO");
		}
	}

}
