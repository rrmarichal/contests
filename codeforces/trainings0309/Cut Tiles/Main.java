import java.io.*;
import java.util.*;

public class Main {
	
	public static void main(String args[]) throws IOException {
		BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
		int t = Integer.parseInt(r.readLine());
		for (int j = 0; j < t; j++) {
			String[] td = r.readLine().split("\\s");
			int n = Integer.parseInt(td[0]),
				m = Integer.parseInt(td[1]);
			int[] s = new int[n];
			for (int k = 0; k < n; k++)
				s[k] = 1 << Integer.parseInt(td[k + 2]);
			System.out.println(solve(n, m, s));
		}
	}

	private static int solve(int n, int m, int[] s) {
		Arrays.sort(s);
		int result = 0;
		List<Tile> tiles = new ArrayList<Tile>();
		for (int j = n - 1; j >= 0; j--) {
			int bm = findBestMatchTile(tiles, s[j]);
			if (bm == -1) {
				tiles.add(new Tile(m, m));
				bm = tiles.size() - 1;
				result++;
			}
			tiles.add(new Tile(s[j], tiles.get(bm).b - s[j]));
			tiles.get(bm).cut(s[j]);
		}
		return result;
	}

	private static int findBestMatchTile(List<Tile> tiles, int cut) {
		int bf = Integer.MAX_VALUE;
		int m = -1;
		for (int j = 0; j < tiles.size(); j++)
			if (tiles.get(j).a >= cut && tiles.get(j).a - cut <= bf) {
				bf = tiles.get(j).a - cut;
				m = j;
			}
		return m;
	}

	static class Tile {
		int a, b;

		public Tile(int a, int b) {
			if (a < b) {
				this.a = a;
				this.b = b;
			}
			else {
				this.a = b;
				this.b = a;
			}
		}

		public void cut(int cut) {
			a -= cut;
		}
	}

}
