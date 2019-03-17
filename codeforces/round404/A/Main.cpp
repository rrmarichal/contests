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
        string s; int n, sum = 0;
        in >> n;
        for (; n; n--) {
            in >> s;
            if (s[0] == 'T')
                sum += 4;
            else
            if (s[0] == 'C')
                sum += 6;
            else
            if (s[0] == 'O')
                sum += 8;
            else
            if (s[0] == 'D')
                sum += 12;
            else
                sum += 20;
        }
        out << sum << endl;
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
