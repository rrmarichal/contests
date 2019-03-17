#include <iostream>
#include <vector>
#include <map>
#include <unordered_map>
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

vector<int> parent;
vector<int> size;
unordered_map<int, vector<int>> spoken;
int N, M, Kj, Ljk;

int rep(int item) {
    if (parent[item] == -1)
        return item;
    return parent[item] = rep(parent[item]);
}

int setSize(int item) {
    return size[rep(item)];
}

void union2(int a, int b) {
    int ra = rep(a); int rb = rep(b);
    if (ra == rb)
        return;
    if (size[ra] < size[rb]) {
        size[rb] += size[ra];
        parent[ra] = rb;
    }
    else {
        size[ra] += size[rb];
        parent[rb] = ra;
    }
}

void solve() {
    cin >> N >> M;
    for (int j = 0; j < N; j++) {
        cin >> Kj;
        for (int k = 0; k < Kj; k++) {
            cin >> Ljk;
            spoken[Ljk].push_back(j);
        }
    }
    parent.resize(N, -1);
    size.resize(N, 1);
    for (auto l: spoken)
        for (int k = 1; k < l.second.size(); k++)
            union2(l.second[0], l.second[k]);
    cout << (setSize(1) == N ? "YES" : "NO") << endl;
}

int main() {
    //ios_base::sync_with_stdio(false);
    //cin.tie(NULL);
    //std::istream& in(std::cin);
    //std::ostream& out(std::cout);
    solve();
    return 0;
}
