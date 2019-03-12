#include <iostream>
#include <algorithm>
#include <vector>
#include <list>

using namespace std;

struct Segment {
    int id, index;
    long gcd;
    Segment() {}
    Segment(int _id, int _index, long _gcd) : id(_id), index(_index), gcd(_gcd) {}
};

const int _oo = -1e9;

int D;
vector<long> A, T;
list<Segment> compressed;

void print_compressed() {
    cout << "compressed" << endl;
    for (auto it = compressed.begin(); it != compressed.end(); it++) {
        cout << it->gcd << " " << it->index << " " << it->id << endl;
    }
    cout << endl;
}

void print_table() {
    for (int j = 0; j < T.size(); j++) {
        cout << T[j] << " ";
    }
    cout << endl;
}

void expand(int index) {
    compressed.push_front(Segment(index, index, A[index]));
    auto current = compressed.begin();
    for (auto it = ++compressed.begin(); it != compressed.end(); it++) {
        it->gcd = __gcd(current->gcd, it->gcd);
        if (current->gcd != it->gcd) {
            current++;
        }
        current->index = it->index;
        current->gcd = it->gcd;
    }
    while (current->id != compressed.back().id) {
        compressed.pop_back();
    }
    // print_compressed();
}

long max(int index, int low, int high) {
    // cout << "max: " << index << " " << low << " " << high << endl;
    if (low >= high) {
        return 0;
    }
    return *max_element(T.begin() + low, T.begin() + high);
}


void update(int index) {
    if (index < D-1) {
        T[index] = _oo;
        return;
    }
    if (index < 2*D-1) {
        T[index] = compressed.back().gcd;
        return;
    }
    auto it = compressed.begin();
    while (index - it->index + 1 < D) {
        it++;
    }
    T[index] = it->gcd + T[index - D];
    int high = index - D + 1;
    for (; it != compressed.end(); it++) {
        int low = it->index;
        if (low < index - 2*D) {
            low = index - 2*D;
        }
        long tback = max(index, low, high);
        if (T[index] < it->gcd + tback) {
            T[index] = it->gcd + tback;
        }
        high = low;
    }
}

long solve() {
    T.resize(A.size(), 0);
    for (int j = 0; j < A.size(); j++) {
        expand(j);
        update(j);
    }
    // print_table();
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
