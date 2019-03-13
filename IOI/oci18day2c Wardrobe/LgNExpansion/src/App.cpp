#include <iostream>
#include <algorithm>
#include <vector>
#include <list>
#include <cmath>

using namespace std;

struct GCDSegment {
    int id, index;
    long gcd;
    GCDSegment() {}
    GCDSegment(int _id, int _index, long _gcd) : id(_id), index(_index), gcd(_gcd) {}
};

int D, tsize;
vector<long> A, T, tree;
list<GCDSegment> compressed;

void expand(int index) {
    compressed.push_front(GCDSegment(index, index, A[index]));
    auto current = compressed.begin();
    for (auto it = ++compressed.begin(); it != compressed.end(); it++) {
        it->gcd = __gcd(current->gcd, it->gcd);
        if (current->gcd != it->gcd) {
            current++;
        }
        current->index = it->index;
        current->gcd = it->gcd;
        if (index - current->index > 2*D) {
            break;
        }
    }
    while (current->id != compressed.back().id) {
        compressed.pop_back();
    }
}

long interval_max(int low, int high) {
    if (low > high) {
        return 0;
    }
    int keyLow = tsize + low, keyHigh = tsize + high;
    long result = max(tree[keyLow], tree[keyHigh]);
    for (; keyLow >> 1 != keyHigh >> 1; keyLow >>= 1, keyHigh >>= 1) {
        int lowParentKey = keyLow >> 1;
        int highParentKey = keyHigh >> 1;
        if (lowParentKey << 1 == keyLow) {
            result = max(result, tree[(lowParentKey << 1) + 1]);
        }
        if ((highParentKey << 1) + 1 == keyHigh) {
            result = max(result, tree[highParentKey << 1]);
        }
    }
    return result;
}

void update_tree(int index) {
    int key = tsize + index;
    tree[key] = T[index];
    do {
        key = key >> 1;
        tree[key] = max(tree[key<<1], tree[(key<<1) + 1]);
    }
    while (key > 0);
}

void update_table(int index) {
    if (index < D-1) return;
    if (index < 2*D-1) {
        T[index] = compressed.back().gcd;
        return;
    }
    auto it = compressed.begin();
    while (index - it->index + 1 < D) {
        it++;
    }
    T[index] = it->gcd + T[index - D];
    int high = index - D;
    for (; it != compressed.end(); it++) {
        int low = it->index;
        long tback = interval_max(low, high);
        if (T[index] < it->gcd + tback) {
            T[index] = it->gcd + tback;
        }
        high = low - 1;
    }
}

int nextPowerOfTwo(int value) {
    int lg = (int) log2(value);
    if ((1 << lg) == value) {
        return value;
    }
    return 1 << (lg + 1);
}

long solve() {
    T.resize(A.size(), 0);
    // Initialize the segment tree.
    tsize = nextPowerOfTwo(T.size());
    tree.resize(2 * tsize, 0);
    for (size_t j = 0; j < A.size(); j++) {
        expand(j);
        update_table(j);
        update_tree(j);
    }
    return T[A.size() - 1];
}

int main() {
    ios_base::sync_with_stdio(false);
    int N, M, k, c = 0;
    cin >> N >> M >> D;
    A.resize(M);
    for (int j = 0; j < N; j++) {
        cin >> k;
        for (; k; k--) cin >> A[c++];
    }
    cout << solve() << endl;
    return 0;
}
