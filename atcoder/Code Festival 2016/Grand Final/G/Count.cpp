#include <iostream>
#include <vector>
#include <map>
#include <set>
#include <list>
#include <algorithm>
#include <cstdlib>
#include <string>

using namespace std;

// Returns 1 if the i-th bit of x is set, 0 otherwise.
#define BIT_SET(x, i) (((x) & (1<<(i))) != 0)
// Sets the i-th bit of x to 1
#define SET_BIT(x, i) ((x) | (1<<(i)))
// Sets the i-th bit of x to 0
#define UNSET_BIT(x, i) ((x) & ~(1<<(i)))
// types
#define int64 long long
#define uint64 unsigned long long

class Task {
    public:
        void solve(istream& in, ostream& out) {
            string text;
            string pattern("FESTIVAL");
            in >> text;
            int64** count = new int64*[2];
            count[0] = new int64[pattern.length()];
            count[1] = new int64[pattern.length()];
            for (int j = 0; j < text.length(); j++) {
                for (int k = 0; k < pattern.length(); k++) {
                    count[j%2][k] = lookup(count, j-1, k);
                    if (pattern[k] == text[j])
                        count[j%2][k] += lookup(count, j-1, k-1); 
                }
            }
            out << count[1 - text.length()%2][pattern.length() - 1] << endl;
        }

        int64 lookup(int64** count, int j, int k) {
            if (k == -1)
                return 1;
            if (j < 0)
                return 0;
            return count[j%2][k];
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
