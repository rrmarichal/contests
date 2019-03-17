#include <iostream>
#include <vector>
#include <map>
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
        int N;
        in >> N;
        if (N==0)
            out << 1;
        else
        if (N%4 == 0)
            out << 6;
        else
        if (N%4 == 1)
            out << 8;
        else
        if (N%4 == 2)
            out << 4;
        else
            out << 2;
        out << endl;
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
