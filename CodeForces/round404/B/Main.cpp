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

class Solution {
public:
    void solve(istream& in, ostream& out) {
        int n, m, x, y, a = 0, b = 1 << 30, c = 0, d = 1 << 30;
        in >> n;
        for (; n; n--) {
            in >> x >> y;
            if (x > a)
                a = x;
            if (y < b)
                b = y;
        }
        in >> m;
        for (; m; m--) {
            in >> x >> y;
            if (x > c)
                c = x;
            if (y < d)
                d = y;
        }
        int best = max(c - b, a - d);
        out << (best < 0 ? 0 : best) << endl;
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
