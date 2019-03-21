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

int N, M, K, _size;
vector<int> a, k, decoded;
vector<list<int>> O(MAX + 1);
vector<int> tree, lazy;

int _value(int index) {
    return tree[index] + lazy[index];
}

int _max(int low, int high) {
    int lowKey = _size + low, highKey = _size + high;
    int lowBest = _value(lowKey);
    int highBest = lowKey != highKey ? _value(highKey) : 0;
    for (; lowKey >> 1 != highKey >> 1; lowKey >>= 1, highKey >>= 1) {
        int lowParentKey = lowKey >> 1;
        if (lowParentKey << 1 == lowKey) {
            lowBest = max(lowBest, _value((lowParentKey << 1) + 1));
        }
        lowBest += lazy[lowParentKey];

        int highParentKey = highKey >> 1;
        if ((highParentKey << 1) + 1 == highKey) {
            highBest = max(highBest, _value(highParentKey << 1));
        }
        highBest += lazy[highParentKey];
    }
    int best = max(lowBest, highBest);
    do {
        lowKey >>= 1;
        best += lazy[lowKey];
    }
    while (lowKey > 1);
    return best;
}

void _increment(int low, int high) {
    int lowKey = _size + low, highKey = _size + high;
    tree[lowKey]++;
    if (lowKey != highKey) {
        tree[highKey]++;
    }
    while (lowKey >> 1 != highKey >> 1) {
        int lowParentKey = lowKey >> 1;
        if (lowParentKey << 1 == lowKey) {
            lazy[(lowParentKey << 1) + 1]++;
        }
        tree[lowParentKey] = max(_value(lowParentKey << 1), _value((lowParentKey << 1) + 1));
        
        int highParentKey = highKey >> 1;
        if ((highParentKey << 1) + 1 == highKey) {
            lazy[(highParentKey << 1)]++;
        }
        tree[highParentKey] = max(_value(highParentKey << 1), _value((highParentKey << 1) + 1));

        lowKey = lowParentKey;
        highKey = highParentKey;
    }
    do {
        lowKey = lowKey >> 1;
        tree[lowKey] = max(_value(lowKey << 1), _value((lowKey << 1) + 1));
    }
    while (lowKey > 1);
}

int nextPowerOfTwo(int value) {
    int lg = (int) log2(value);
    if ((1 << lg) == value) {
        return value;
    }
    return 1 << (lg + 1);
}

void _build_tree() {
    _size = nextPowerOfTwo(N);
    tree.resize(_size << 1, 0);
    lazy.resize(_size << 1, 0);
}

void solve() {
    decoded.resize(M);
    _build_tree();
    forn (j, N) {
        O[a[j]].push_back(j);
    }
    forb (j, MAX, 1) {
        if (O[j].size() > 0) {
            for (auto index: O[j]) {
                int r = min(N - 1, index + M - 1);
                int max = _max(index, r);
                if (!decoded[M - max - 1]) {
                    decoded[M - max - 1] = j;
                }
                _increment(index, r);
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
