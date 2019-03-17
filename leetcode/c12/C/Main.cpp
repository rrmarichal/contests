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
    int findMaxForm(vector<string>& strs, int m, int n) {
        int*** T = new int**[2];
        for (int j = 0; j < 2; j++) {
            T[j] = new int*[m+1];
            for (int k = 0; k <= m; k++)
                T[j][k] = new int[n+1];
        }
        int* ones = new int[strs.size()];
        for (int j = 0; j < strs.size(); j++) {
            int no = 0;
            for (int k = 0; k < strs[j].length(); k++)
                if (strs[j][k] == '1')
                    no++;
            ones[j] = no;
        }
        int b = 0;
        for (int j = 0; j < strs.size(); j++) {
            for (int k = 0; k <= m; k++)
                for (int l = 0; l <= n; l++)
                    T[1-b][k][l] = max(lookup(T, b, j-1, k, l),
                        1 + lookup(T, b, j-1, k-(strs[j].length() - ones[j]), l - ones[j]));
            b = 1-b;
        }
        return T[b][m][n];
    }

    int lookup(int*** T, int b, int i, int m, int n) {
        if (m < 0 || n < 0)
            return -1;
        if (i < 0)
            return 0;
        return T[b][m][n];
    }

    void solve(istream& in, ostream& out) {
        int n, m, c;
        in >> n >> m >> c;
        string s;
        vector<string> strs;
        for (int j = 0; j < c; j++) {
            in >> s;
            strs.push_back(s);
        }
        out << findMaxForm(strs, n, m) << endl;
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
