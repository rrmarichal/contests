#include <iostream>
#include <vector>
#include <map>
#include <unordered_map>
#include <set>
#include <list>
#include <algorithm>
#include <string>
#include <cstring>

using namespace std;

// Returns 1 if the i-th bit of x is set, 0 otherwise.
#define BIT_SET(x, i) (((x) & (1<<(i))) != 0)
// Sets the i-th bit of x to 1
#define SET_BIT(x, i) ((x) | (1<<(i)))
// Sets the i-th bit of x to 0
#define UNSET_BIT(x, i) ((x) & ~(1<<(i)))

#define int64 long long
#define uint64 unsigned long long

class DisjointSet {
private:
    vector<int> parent, rank;

public:
    DisjointSet(int N) {
        parent.resize(N, -1);
        rank.resize(N, 0);
    }

    int head(int item) {
        if (parent[item] == -1)
            return item;
        return parent[item] = head(parent[item]);
    }

    void merge(int a, int b) {
        int ra = head(a), rb = head(b);
        if (ra == rb)
            return;
        if (rank[ra] < rank[rb])
            parent[ra] = rb;
        else {
            parent[rb] = ra;
            if (rank[ra] == rank[rb])
                rank[ra]++;
        }
    }
};

class Solution {
public:
    void solve(istream& in, ostream& out) {
        int N, M, W, x, y;
        in >> N >> M >> W;
        vector<int> w(N), b(N);
        for (int j = 0; j < N; j++)
            in >> w[j];
        for (int j = 0; j < N; j++)
            in >> b[j];
        DisjointSet ds(N);
        for (int j = 0; j < M; j++) {
            in >> x >> y;
            ds.merge(x-1, y-1);
        }
        unordered_map<int, vector<int>> groups;
        for (int j = 0; j < N; j++)
            groups[ds.head(j)].push_back(j);

        int** T = new int*[groups.size()];
        for (int j = 0; j < groups.size(); j++)
            T[j] = new int[W+1];
        int gi = 0, gb, gw;
        for (auto g: groups) {
            for (int k = 0; k <= W; k++) {
                gb = gw = 0;
                T[gi][k] = gi > 0 ? T[gi-1][k] : 0;
                for (vector<int>::iterator x = g.second.begin(); x != g.second.end(); x++) {
                    T[gi][k] = max(T[gi][k], b[*x] + lookup(T, k-w[*x], gi-1));
                    gw += w[*x];
                    gb += b[*x];
                }
                T[gi][k] = max(T[gi][k], gb + lookup(T, k-gw, gi-1));
            }
            gi++;
        }

        out << T[groups.size() - 1][W] << endl;
    }

    int lookup(int** T, int ww, int gi) {
        if (ww < 0)
            return -1000000000;
        if (gi < 0)
            return 0;
        return T[gi][ww];
    }
} task;

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);
    istream& in(cin);
    ostream& out(cout);
    task.solve(in, out);
    return 0;
}
