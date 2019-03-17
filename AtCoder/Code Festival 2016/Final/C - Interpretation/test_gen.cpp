#define DEBUG

#include <iostream>
#include <vector>
#include <map>
#include <set>
#include <list>
#include <algorithm>

#include <ctime>

// Logs a debug message
#ifdef DEBUG
#define LOG(e) (std::cout << (e) << std::endl)
#endif
#ifndef DEBUG
#define LOG(e) 
#endif
// Log a collection
#define LOG_COLLECTION(c,i) for (int i=0; i<c.size(); i++) { LOG(c[i]); }
// Log a collection using type iterator
#define LOG_COLLECTION_ITERATOR(t,c) for (t::iterator it=c.begin(); it!=c.end(); it++) { LOG(*(it)); }
// Log an array
#define LOG_ARRAY(a,i,l) for (int i=0; i<l; i++) { LOG(a[i]); }

#define P 1000000007

using namespace std;

int range_rand(int l, int h) {
    return abs(P * rand()) % (h-l+1) + l;
}

int main() {
    int N, M, S;
    cin >> N >> M >> S;
    S = min(S, N*M);
    vector<set<int>> langs(N);
    int t = 0;
    time_t* tm = new time_t();
	time(tm);
    srand(*tm);
    while (t < S) {
        int p = range_rand(0, N-1);
        int l = range_rand(1, M);
        if (langs[p].find(l) == langs[p].end()) {
            langs[p].emplace(l);
            t++;
        }
    }
    cout << N << " " << M << endl;
    for (int j = 0; j < N; j++) {
        cout << langs[j].size();
        for (set<int>::iterator it = langs[j].begin(); it != langs[j].end(); it++)
            cout << " " << *it;
        cout << endl;
    }
}
