#include <iostream>
#include <fstream>
#include <vector>
#include <algorithm>

using namespace std;

struct Edge { int a, b; };

vector<Edge> edges;
int N, S;
vector<vector<int>> graph;
vector<int> degree, mark;
vector<bool> visited;

int dfs(int current) {
    visited[current] = true;
    int ng = 0;
    for (vector<int>::iterator next = graph[current].begin(); next != graph[current].end(); next++) {
        if (!visited[*next]) {
            mark[current] = *next;
            ng += dfs(*next);
            mark[current] = -2;
        }
        else {
            // Cycle detected.
            if (mark[*next] >= 0 && mark[*next] != current) {
                if (degree[current] == 2) ng++;
                if (degree[mark[*next]] == 2) ng++;
            }
        }
    }
    // Leaf detected.
    if (degree[current] == 1 && mark[current] == -1) {
        ng++;
    }
    return ng;
}

int count() {
    graph.resize(N);
    degree.resize(N, 0);
    mark.resize(N, -1);
    visited.resize(N, false);
    for (vector<Edge>::iterator edge = edges.begin(); edge != edges.end(); edge++) {
        graph[edge->a].push_back(edge->b);
        graph[edge->b].push_back(edge->a);
        degree[edge->a]++;
        degree[edge->b]++;
    }
    return dfs(S-1);
}

int main() {
    int M, a, b;
    cin >> N >> M >> S;
    edges.resize(M);
    for (int j = 0; j < M; j++) {
        cin >> a >> b;
        edges[j] = { a-1, b-1 };
    }
    cout << count() << endl;
    return 0;
}
