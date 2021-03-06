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

class Solution {
public:
    void solve(istream& in, ostream& out) {
        int N, X, k;
        in >> N >> X;
        vector<int> v(N);
        for (int j = 0; j < N; j++)
            in >> v[j];
        unordered_map<int, int> m;
        int64 result = 0;
        for (int j = N-1; j >= 0; j--) {
            result += m[v[j] ^ X];
            m[v[j]]++;
        }
        out << result << endl;
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
