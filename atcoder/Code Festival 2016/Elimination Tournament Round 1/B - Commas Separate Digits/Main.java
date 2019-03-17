import java.io.*;
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
            int K = in.nextInt();
            String S = in.next();
            int ssl = S.length() / (K+1);
            int sslp1 = S.length() - (K+1) * ssl;
            String[][] T = new String[S.length()][sslp1+1];
            for (int j = 0; j < S.length(); j++) {
                // T[j][k] = best splitting of chars from 0-j in k strings of length ssl+1
                for (int k = 0; k <= sslp1; k++) {
                    // cut j-(j-ssl)
                    if (k > 0 && j-ssl >= 0)
                        T[j][k] = max(S.substring(j-ssl, j+1), table(S, T, j-ssl-1, k-1));
                    // cut j-(j-ssl+1)
                    if (j-ssl+1 >= 0)
                        T[j][k] = min(T[j][k], max(S.substring(j-ssl+1, j+1), table(S, T, j-ssl, k)));
                }
            }
            out.println(T[S.length() - 1][sslp1]);
        }

        private String table(String S, String[][] T, int j, int k) {
            if (j < 0)
                return k == 0 ? "" : null;
            return T[j][k];
        }

        private String min(String a, String b) {
            if (a == null) return b;
            if (b == null) return a;
            if (a.length() < b.length())
                return a;
            if (a.length() > b.length())
                return b;
            return a.compareTo(b) < 0 ? a : b;
        }

        private String max(String a, String b) {
            if (a == null || b == null)
                return null;
            if (a.length() < b.length())
                return b;
            if (a.length() > b.length())
                return a;
            return a.compareTo(b) > 0 ? a : b;
        }
    }
    
}
