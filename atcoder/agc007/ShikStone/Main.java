import java.io.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
		String[] ld = r.readLine().split("\\s");
		int H = Integer.parseInt(ld[0]);
		int W = Integer.parseInt(ld[1]);
		String[] m = new String[H];
		for (int j = 0; j < H; j++)
			m[j] = r.readLine();
		for (int j = 0; j < H; j++)
			for (int k = 0; k < W; k++) {
				if (j == 0 && k == 0) continue;
				if (m[j].charAt(k) == '#') {
					// up
					if (j > 0 && m[j - 1].charAt(k) == '#') {
						if (count(m, j, k) - 1 != count(m, j - 1, k)) {
							System.out.println("Impossible");
							return;
						}
					}
					else
					// left
					if (k > 0 && m[j].charAt(k - 1) == '#') {
						if (count(m, j, k) - 1 != count(m, j, k - 1)) {
							System.out.println("Impossible");
							return;
						}
					}
					else {
						System.out.println("Impossible");
						return;
					}
				}
			}
		System.out.println("Possible");
	}

	private static int count(String[] m, int r, int c) {
		int result = 0;
		for (int j = 0; j <= r; j++)
			for (int k = 0; k <= c; k++)
				if (m[j].charAt(k) == '#')
					result++;
		return result;
    }
    
}
