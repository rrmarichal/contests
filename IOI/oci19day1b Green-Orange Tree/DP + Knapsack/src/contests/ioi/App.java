package contests.ioi;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Scanner;

class TreeEdge {
    int a, b, green;

    public TreeEdge(int a, int b, int green) {
        this.a = a;
        this.b = b;
        this.green = green;
    }
}

class ChildInfo {
    int node;
    int green;

    public ChildInfo(int node, int green) {
        this.node = node;
        this.green = green;
    }
}

public class App {

    int[] parent, size;
    LinkedList<ChildInfo>[] tree;
    int[][][] dp;

    private LinkedList<ChildInfo>[] buildTree(int N, TreeEdge[] edges) {
        // ArrayList<Integer>[] tree = new ArrayList[edges.length + 1];
        LinkedList<ChildInfo>[] tree = new LinkedList[N];
        for (TreeEdge e: edges) {
            if (tree[e.a] == null) {
                tree[e.a] = new LinkedList<ChildInfo>();
            }
            tree[e.a].add(new ChildInfo(e.b, e.green));
            if (tree[e.b] == null) {
                tree[e.b] = new LinkedList<ChildInfo>();
            }
            tree[e.b].add(new ChildInfo(e.a, e.green));
        }
        return tree;
    }

    private void knapsack(int current, int K) {
        int childIndex = 0;
        for (ChildInfo child: tree[current]) {
            if (parent[child.node] == current) {
                for (int k = 2; k <= K; k++) {
                    // Do not take the edge from current to child.
                    dp[current][k][childIndex] = childIndex > 0
                        ? dp[current][k][childIndex - 1]
                        : 0;
                    // Take q nodes from this child and (k - q) for the 
                    // previous children.
                    for (int q = 1; q <= Math.min(k - 1, size[child.node]); q++) {
                        int prev = childIndex > 0
                            ? dp[current][k - q][childIndex - 1]
                            : 0;
                        int cm = tree[child.node].size() > 1
                            ? dp[child.node][q][tree[child.node].size() - 2]
                            : 0;
                        if (prev + cm + child.green > dp[current][k][childIndex]) {
                            dp[current][k][childIndex] = prev + cm + child.green;
                        }
                    }
                }
                childIndex++;
            }
        }
    }

    private int dfs(int current, int K) {
        int s = 1;
        for (ChildInfo child: tree[current]) {
            if (parent[child.node] == -1) {
                parent[child.node] = current;
                s += dfs(child.node, K);
            }
        }
        knapsack(current, K);
        return size[current] = s;
    }

    public int solve(int N, int K, TreeEdge[] edges) {
        tree = buildTree(N, edges);
        parent = new int[N];
        size = new int[N];
        Arrays.fill(parent, 1, parent.length, -1);
        dp = new int[N][K+1][];
        for (int j = 0; j < N; j++)
            for (int k = 0; k <= K; k++)
                dp[j][k] = new int[tree[j].size()];
        dfs(0, K);
        int max = 0;
        for (int j = 0; j < N; j++) {
            int cm = j == 0 || tree[j].size() == 1
                ? dp[j][K][tree[j].size() - 1]
                : dp[j][K][tree[j].size() - 2];
            if (cm > max) max = cm;
        }
        return max;
    }

    public static void main(String[] args) {
        App app = new App();
        Scanner sc = new Scanner(System.in);
        int N = sc.nextInt(), K = sc.nextInt();
        TreeEdge[] edges = new TreeEdge[N - 1];
        for (int j = 0; j < N-1; j++) {
            int a = sc.nextInt(), b = sc.nextInt(), c = sc.nextInt();
            edges[j] = new TreeEdge(a - 1, b - 1, c);
        }
        System.out.println(app.solve(N, K, edges));
        sc.close();
    }

}
