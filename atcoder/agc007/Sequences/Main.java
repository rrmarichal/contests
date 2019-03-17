import java.io.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(r.readLine());
		String[] ps = r.readLine().split("\\s");
		int[] p = new int[N];
		for (int j = 0; j < N; j++)
			p[j] = Integer.parseInt(ps[j]);
		PrintWriter pw = new PrintWriter(System.out);
		for (int j = 0; j < N; j++) {
			pw.print(1 + (N + 1) * j);
			if (j < N-1)
				pw.print(" ");
		}
		pw.println();
		int[] b = new int[N];
		for (int j = 0; j < N; j++) {
			int il = (N + 1) * (N - p[j] + 1);
			b[p[j] - 1] = il + j - 1;
		}
		for (int j = 0; j < N; j++) {
			pw.print(b[j]);
			if (j < N-1)
				pw.print(" ");
		}
		pw.println();
		pw.close();
		r.close();
	}
	
}
