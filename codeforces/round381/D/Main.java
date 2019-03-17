import java.io.*;
import java.lang.*;
import java.util.*;

public class Main {

	public static void main(String[] args) throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(reader.readLine());
		String[] raw = reader.readLine().split("\\s");
		long[] a = new long[n];
		for (int j = 0; j < n; j++)
			a[j] = Long.parseLong(raw[j]);
		List<Edge>[] tree = new ArrayList[n];
		for (int j = 0; j < n; j++)
			tree[j]= new ArrayList<Edge>();
		for (int j = 1; j < n; j++) {
			raw = reader.readLine().split("\\s");
			tree[Integer.parseInt(raw[0]) - 1]
				.add(new Edge(j, Integer.parseInt(raw[1])));
		}
		Solution solution = new Solution(tree, a);
		solution.solve();
		for (int j = 0; j < n-1; j++)
			System.out.print(String.format("%d ", solution.controls[j]));
		System.out.println(solution.controls[n-1]);
	}

	static class Solution {

		private List<Edge>[] tree;
		private long[] a;
		private int[] controls;
		private Long[] path;
		private FenwickTree acc;

		public Solution(List<Edge>[] tree, long[] a) {
			this.tree = tree;
			this.a = a;
			controls = new int[a.length];
			path = new Long[a.length];
			acc = new FenwickTree(a.length);
		}

		public void solve() {
			dfs(0, 0, 1);
		}

		private void dfs(int current, long sum, int level) {
			path[level - 1] = sum;
			int index = Arrays.binarySearch(path, 0, level, sum - a[current]);
			acc.update(index >= 0 ? index + 1 : -index);
			int cc = acc.range(level);
			for (Edge child: tree[current])
				dfs(child.node, sum + child.value, level + 1);
			controls[current] = acc.range(level) - cc;
		}

	}

	static class FenwickTree {

		private int[] tree;

		public FenwickTree(int n) {
			this.tree = new int[n + 1];
		}

		public void update(int index) {
			while (index < tree.length) {
				tree[index]++;
				index += index & -index;
			}
		}

		public int range(int index) {
			int result = 0;
			while (index > 0) {
				result += tree[index];
				index -= index & -index;
			}
			return result;
		}

	}

	static class Edge {
		int node; long value; 

		public Edge(int node, long value) {
			this.node = node;
			this.value = value;
		}
	}
	
}
