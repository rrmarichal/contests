#include <iostream>
#include <algorithm>
#include <vector>
#include <list>

using namespace std;

struct Segment {
    int id, index, gcd;
    Segment() {}
    Segment(int _id, int _index, int _gcd) : id(_id), index(_index), gcd(_gcd) {}
};

const int _oo = -1e9;

int D;
vector<long> A, T;
list<Segment> compressed;

void print_compressed() {
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

void update(int index) {
    int R = (index + 1) % D;
    T[index] = _oo;
    for (auto it = compressed.begin(); it != compressed.end(); it++) {
        if (index - it->index + 1 < D) {
            continue;
        }
        int back = it->index - 1;
        if (index - it->index + 1 > D + R) {
            back = index - D - R;
        }
        int tback = back < 0 ? 0 : T[back];
        if (T[index] < it->gcd + tback) {
            T[index] = it->gcd + tback;
        }
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
