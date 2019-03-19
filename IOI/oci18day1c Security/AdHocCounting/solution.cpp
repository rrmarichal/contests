#include <bits/stdc++.h>

using namespace std;

// Returns 1 if the i-th bit of x is set, 0 otherwise.
#define BIT_SET(x, i) (((x) & (1<<(i))) != 0)
// Sets the i-th bit of x to 1
#define SET_BIT(x, i) ((x) | (1<<(i)))
// Sets the i-th bit of x to 0
#define UNSET_BIT(x, i) ((x) & ~(1<<(i)))
// Loop from [0..n)
#define forn(j, n) for (int j = 0; j < (int)(n); j++)
// Loop from [a..b]
#define fore(j, a, b) for (int j = (int)(a); j < (int)(b); j++)
// Loop backwards from [a..b]
#define forb(j, a, b) for (int j = (int)(a); j >= (int)(b); j--)

#define MAX 250000

int N, M, K;
vector<int> a, k, tree, decoded;
vector<list<int>> O(MAX + 1);

int sum(int index) {
    int sum = 0;
    while (index > 0) {
        sum += tree[index];
        index -= index&-index;
    }
    return sum;
}

// Return the number of items from l to r.
int count(int l, int r) {
    if (l < 0 || r >= N) {
        return 0;
    }
    return sum(r + 1) - sum(l);
}

// Add one item at index.
void increment(int index) {
    index++;
    while (index < tree.size()) {
        tree[index]++;
        index += index&-index;
    }
}

void solve() {
    decoded.resize(M);
    // Fenwick tree internally handles indices from 1..N.
    tree.resize(N + 1);
    forn (j, N) {
        O[a[j]].push_back(j);
    }
    forb (j, MAX, 1) {
        if (O[j].size() > 0) {
            for (auto index: O[j]) {
                // Search for the interval with maximum # of elements already seen.
                int max = 0;
                forn (k, M) {
                    int c = count(index + k + 1 - M, index + k);
                    if (c > max) {
                        max = c;
                    }
                }
                if (!decoded[M - max - 1]) {
                    decoded[M - max - 1] = j;
                }
                increment(index);
            }
        }
    }
    forn (j, K - 1) {
        cout << decoded[k[j] - 1] << " ";
    }
    cout << decoded[k[K - 1] - 1] << endl;
}

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);

#ifdef LOCAL_DEBUG
    freopen("0.in", "rt", stdin);
#endif

    cin >> N >> M >> K;
    a.resize(N);
    k.resize(K);
    forn (j, N) cin >> a[j];
    forn (j, K) cin >> k[j];

    solve();

    return 0;
}
