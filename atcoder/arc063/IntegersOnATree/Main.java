import java.io.*;
import java.lang.*;
import java.util.*;

public class Main {

	static boolean debug = false;

	public static void main(String[] args) throws IOException {
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		int N = Integer.parseInt(reader.readLine());
		Tree tree = new Tree(N);
		for (int j = 0; j < N - 1; j++) {
			String[] xy = reader.readLine().split("\\s");
			int x = Integer.parseInt(xy[0]) - 1, y = Integer.parseInt(xy[1]) - 1;
			tree.insert(x, y);
			tree.insert(y, x);
		}
		int K = Integer.parseInt(reader.readLine()), start = 0;
		Color[] color = new Color[N];
		for (int j = 0; j < K; j++) {
			String[] vp = reader.readLine().split("\\s");
			int v = Integer.parseInt(vp[0]) - 1;
			color[v] = new Color(Integer.parseInt(vp[1]), 1);
			start = v;
		}
		PrintWriter pw = new PrintWriter(System.out);
		if (solve(tree, color, -1, start)) {
			pw.println("Yes");
			paint(tree, color, -1, start);
			for (int j = 0; j < N; j++)
				pw.println(color[j].start);
		}
		else
			pw.println("No");
		pw.close();
	}

	private static void paint(Tree tree, Color[] color, int parent, int node) {
		Node child = tree.first(node);
		if (color[node].count > 1) {
			if (color[node].start < color[parent].start)
				color[node].start = color[parent].start - 1;
			else
				color[node].start = color[parent].start + 1;
		}
		while (child != null) {
			if (child.node != parent)
				paint(tree, color, node, child.node);
			child = tree.next(child);
		}
	}

	private static boolean solve(Tree tree, Color[] color, int parent, int node) {
		if (debug) System.out.println(String.format("solve(tree, color, %d, %d)", parent, node));
		if (color[node] == null)
			color[node] = color[parent].expand();
		Node child = tree.first(node);
		while (child != null) {
			if (child.node != parent) {
				if (!solve(tree, color, node, child.node))
					return false;
				color[node] = merge(color[node], color[child.node]);
				if (color[node] == null)
					return false;
			}
			child = tree.next(child);
		}
		return true;
	}

	private static Color merge(Color current, Color child) {
		if (debug) System.out.println(String.format("merge(current: [%d, %d] with child: [%d, %d])",
			current.start, current.count, child.start, child.count));
		Color merge;
		if (current.start % 2 == child.start % 2)
			merge = null;
		Color expand = child.expand();
		long start = Math.max(expand.start, current.start);
		long end = Math.min(current.start + 2 * (current.count - 1), expand.start + 2 * (expand.count - 1));
		merge = new Color(start, (int) (end - start + 2) / 2);
		if (merge.count <= 0)
			merge = null;
		if (debug) System.out.println(merge != null ? String.format("merge result: [%d, %d]", merge.start, merge.count) : "merge result: [null]");
		return merge;
	}

	static class Color {
		long start;
		int count;

		public Color(long start, int count) {
			this.start = start;
			this.count = count;
		}

		public Color expand() {
			 return new Color(start - 1, count + 1);
		}
	}

	static class Tree {
		
		private Node[] tree;
		private int[] last;
		private int current;

		public Tree(int N) {
			tree = new Node[2 * N + 1];
			last = new int[N];
			current = 1;
		}

		public Node next(Node node) {
			return node.next > 0
				? tree[node.next]
				: null;
		}
		public Node first(int node) {
			return tree[last[node]];
		}

		public void insert(int x, int y) {
			tree[current] = new Node(x, last[y]);
			last[y] = current++;
		}

	}
	static class Node {
		int node, next;

		public Node(int node, int next) {
			this.node = node;
			this.next = next;
		}
	}

}
