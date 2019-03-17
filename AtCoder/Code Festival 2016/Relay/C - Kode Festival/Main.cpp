#include <iostream>
#include <vector>
#include <map>
#include <set>
#include <list>
#include <algorithm>

using namespace std;

// Returns 1 if the i-th bit of x is set, 0 otherwise.
#define BIT_SET(x, i) (((x) & (1<<(i))) != 0)
// Sets the i-th bit of x to 1
#define SET_BIT(x, i) ((x) | (1<<(i)))
// Sets the i-th bit of x to 0
#define UNSET_BIT(x, i) ((x) & ~(1<<(i)))

class Task {
    public:
        void solve(istream& in, ostream& out) {
        	int N;
        	in >> N;
        	vector<int> v(1<<N);
        	for (int j = 0; j < 1 << N; j++)
        		in >> v[j];
        	out << fight(0, (1<<N) - 1, v) << endl;
        }

        int fight(int l, int r, vector<int>& v) {
        	if (l == r) {
        		return v[l];
        	}
        	else {
        		int lw = fight(l, l + (r-l)/2, v);
        		int rw = fight(l + (r-l)/2 + 1, r, v);
        		if (lw == rw)
        			return lw;
        		return max(lw, rw) - min(lw, rw);
        	}
        }
} task;

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);
    std::istream& in(std::cin);
    std::ostream& out(std::cout);
    task.solve(in, out);
    return 0;
}
