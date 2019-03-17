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
    string** T;

    int rep(string s, int from, int to, int length) {
        int k = 1;
        while (1) {
            for (int j = 0; j < length; j++) {
                if (k*length+from+j > to)
                    return k;
                if (s[from+j] != s[k*length+from+j])
                    return k;
            }
            k++;
        }
    }

    string best(string s, int l, int h) {
        if (T[l][h].length() != 0)
            return T[l][h];
        T[l][h] = s.substr(l, h-l+1);
        for (int j = 1; j <= (h-l+1)/2; j++) {
            int count = rep(s, l, h, j);
            string q = (count > 1)
                ? to_string(count) + "[" + best(s, l, l+j-1) + "]"
                : best(s, l, l+j-1);
            if (count * j < h-l+1)
                q = q + best(s, l + count * j, h);
            if (q.length() < T[l][h].length())
                T[l][h] = q;
        }
        for (int p = 1; p < h-l+1; p++) {
            string q = s.substr(l, p);
            q += best(s, p, h);
            if (q.length() < T[l][h].length())
                T[l][h] = q;
        }
        return T[l][h];
    }

    string encode(string s) {
        T = new string*[s.length()];
        for (int j = 0; j < s.length(); j++)
            T[j] = new string[s.length()]; 
        return best(s, 0, s.length() - 1);
    }

    void solve(istream& in, ostream& out) {
        string s;
        in >> s;
        out << encode(s) << endl;
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
