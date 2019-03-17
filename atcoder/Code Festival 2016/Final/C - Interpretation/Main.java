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
		Solution s = new Solution();
		s.solve();
	}

	static class Solution {

		int count;
		boolean[] m;
		List<Integer>[] spoken;
		int[][] L;

		public void solve() throws IOException {
			InputReader in = new InputReader(System.in);
			int N = in.nextInt(), M = in.nextInt();
			count = 0;
			L = new int[N][];
			m = new boolean[N];
			spoken = new ArrayList[M + 1];
			for (int j = 0; j < N; j++) {
				L[j] = new int[in.nextInt()];
				for (int k = 0; k < L[j].length; k++) {
					L[j][k] = in.nextInt();
					if (spoken[L[j][k]] == null)
						spoken[L[j][k]] = new ArrayList();
					spoken[L[j][k]].add(j);
				}
			}
			dfs(0);
			System.out.println(count == N ? "YES" : "NO");
		}

		void dfs(int current) {
			count++;
			m[current] = true;
			for (int k = 0; k < L[current].length; k++) {
				int lang = L[current][k];
				for (int v: spoken[lang])
					if (!m[v])
						dfs(v);
			}
		}

	}
	
}
