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
    int64 max_sum(int64* b, int n) {
        int64 current = 0;
        int64 best = 0;
        for (int j = 0; j < n; j++) {
            current += b[j];
            if (current > best)
                best = current;
            if (current < 0)
                current = 0;
        }
        return best;
    }

    void solve(istream& in, ostream& out) {
        int n;
        in >> n;
        int64* a = new int64[n];
        for (int j = 0; j < n; j++)
            in >> a[j];
        int64* b = new int64[n-1];
        int p = 1;
        for (int j = 0; j < n-1; j++) {
            b[j] = abs(a[j] - a[j+1]) * p;
            p = -p;
        }
        int64 best = max_sum(b, n-1);
        p = -1;
        for (int j = 0; j < n-1; j++) {
            b[j] = abs(a[j] - a[j+1]) * p;
            p = -p;
        }
        int64 best2 = max_sum(b, n-1);
        out << max(best, best2) << endl;
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
