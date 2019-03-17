#include <iostream>
#include <vector>
#include <map>
#include <set>
#include <list>
#include <algorithm>

using namespace std;

// Returns 1 if the i-th bit of x is set, 0 otherwise.
#define BIT_SET(x, i) (((x) & (1<<(i))) != 0)
// Sets the i-th bit of x to 1
#define SET_BIT(x, i) ((x) | (1<<(i)))
// Sets the i-th bit of x to 0
#define UNSET_BIT(x, i) ((x) & ~(1<<(i)))

struct DisjointSet {
    private:
        vector<int> parent, size;

    public:
        DisjointSet(int N) {
            parent.resize(N, -1);
            size.resize(N, 1);
        }

        int rep(int item) {
            if (parent[item] == -1)
                return item;
            return parent[item] = rep(parent[item]);
        }

        void union2(int a, int b) {
            int ra = rep(a), rb = rep(b);
            if (ra == rb)
                return;
            if (set_size(a) < set_size(b)) {
                size[rb] += size[ra];
                parent[ra] = rb;
            }
            else {
                size[ra] += size[rb];
                parent[rb] = ra;
            }
        }

        int set_size(int item) {
            return size[rep(item)];
        }
};

struct Task {
    public:
        void solve(istream& in, ostream& out) {
            int N, M, Kj, Ljk;
			in >> N >> M;
			vector<list<int>> spoken(M+1);
			for (int j = 0; j < N; j++) {
				in >> Kj;
				for (int k = 0; k < Kj; k++) {
					in >> Ljk;
					spoken[Ljk].emplace_back(j);
				}
			}
            DisjointSet* ds = new DisjointSet(N);
            for (int j = 1; j <= M; j++) {
                for (list<int>::iterator vj = spoken[j].begin(); vj != spoken[j].end(); vj++) {
                    if (vj == spoken[j].begin())
                        continue;
                    ds->union2(*spoken[j].begin(), *vj);
                }
            }
            out << (ds->set_size(0) == N ? "YES" : "NO") << endl;
        }
} task;

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);
    std::istream& in(std::cin);
    std::ostream& out(std::cout);
    task.solve(in, out);
    return 0;
}
