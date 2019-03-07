package contests.ioi;

import java.util.Arrays;
import java.util.LinkedList;
import java.util.Scanner;

class Edge {
    int a, b;

    public Edge(int a, int b) {
        this.a = a;
        this.b = b;
    }
}

public class App {

    private LinkedList<Integer>[] graph;
    private int[] degree, mark;
    private boolean[] visited;

    public int count(int N, int S, Edge[] edges) {
        graph = new LinkedList[N];
        degree = new int[N];
        mark = new int[N];
        Arrays.fill(mark, -1);
        visited = new boolean[N];
        for (Edge e: edges) {
            if (graph[e.a] == null) {
                graph[e.a] = new LinkedList<Integer>();
            }
            graph[e.a].add(e.b);
            if (graph[e.b] == null) {
                graph[e.b] = new LinkedList<Integer>();
            }
            graph[e.b].add(e.a);
            degree[e.a]++;
            degree[e.b]++;
        }
        return dfs(S);
    }

    private int dfs(int current) {
        visited[current] = true;
        int ng = 0;
        for (int next: graph[current]) {
            if (!visited[next]) {
                mark[current] = next;
                ng += dfs(next);
                mark[current] = -2;
            }
            else {
                // Cycle detected.
                if (mark[next] >= 0 && mark[next] != current) {
                    if (degree[current] == 2) ng++;
                    if (degree[mark[next]] == 2) ng++;
                }
            }
        }
        // Leaf detected.
        if (degree[current] == 1 && mark[current] == -1) {
            ng++;
        }
        return ng;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        App app = new App();
        int N = sc.nextInt();
        int M = sc.nextInt();
        int S = sc.nextInt();
        Edge[] edges = new Edge[M];
        for (int j = 0; j < M; j++) {
            edges[j] = new Edge(sc.nextInt()-1, sc.nextInt()-1);
        }
        System.out.println(app.count(N, S-1, edges));
        sc.close();
    }

}
