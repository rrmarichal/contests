#include <iostream>
#include <algorithm>
#include <vector>
#include <list>

using namespace std;

int D;
vector<long> A, T;

void print_table() {
    for (int j = 0; j < T.size(); j++) {
        cout << T[j] << " ";
    }
    cout << endl;
}

long solve() {
    T.resize(A.size(), 0);
    T[0] = A[0];
    for (int j = 1; j < A.size(); j++) {
        if ((j + 1)/D < 2) {
            T[j] = __gcd(A[j], T[j-1]);
        }
        else {
            long g = A[j];
            for (int k = j-1; j-k < D; k--) {
                g = __gcd(g, A[k]);
            }
            T[j] = g + T[j-D];
            for (int r = 0; j-D-r >= D; r++) {
                g = __gcd(g, A[j-D-r]);
                T[j] = max(T[j], g + T[j-D-r-1]);
            }
        }
    }
    print_table();
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
