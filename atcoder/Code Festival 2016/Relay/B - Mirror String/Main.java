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
        Task task = new Task(in, out);
        task.solve();
        out.close();
	}

	static class Task {
		
		private InputReader in;
		private PrintWriter out;

		public Task(InputReader in, PrintWriter out) {
			this.in = in;
			this.out = out;
		}

		public void solve() {
			String S = in.next();
			boolean ok = true;
			if (S.length() % 2 == 1) {
				out.println("No");
				return;
			}
			for (int j = 0; j < S.length(); j++) {
				char l = S.charAt(j);
				char r = S.charAt(S.length() - j - 1);
				if (!((l == 'b' && r == 'd') || (l == 'd' && r == 'b') || (l == 'p' && r == 'q') || (l == 'q' && r == 'p'))) {
					ok = false;
					break;
				}
			}
			out.println(ok ? "Yes" : "No");
		}
	}
	
}
