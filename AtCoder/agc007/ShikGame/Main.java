import java.io.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
		String[] ld = r.readLine().split("\\s");
		int N = Integer.parseInt(ld[0]);
		long E = Long.parseLong(ld[1]);
		long T = Long.parseLong(ld[2]);
		String[] ps = r.readLine().split("\\s");
		long[] p = new long[N];
		for (int j = 0; j < N; j++)
			p[j] = Long.parseLong(ps[j]);
		int current = 0;
		long result = p[0];
		while (current < N) {
			int k = current + 1;
			long currentBest = T;
			while (k < N) {
				long kbest = 3 * (p[k] - p[current]);
				if (2 * (p[k] - p[current]) < T)
					kbest += T - 2 * (p[k] - p[current]);
				if (kbest > currentBest)
				k++;
			}
			result += 3 * (p[k - 1] - p[current]);
			if (2 * (p[k - 1] - p[current]) < T)
				result += T - 2 * (p[k - 1] - p[current]);
			if (k < N)
				result += p[k] - p[k - 1];
			current = k;
		}
		PrintWriter pw = new PrintWriter(System.out);
		pw.println(result + E - p[N - 1]);
		pw.close();
    }
    
}
