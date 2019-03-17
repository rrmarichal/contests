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

#define HOUR_SECONDS 3600
#define DAY_SECONDS (24*HOUR_SECONDS)

class Task {
    public:
        void solve(istream& in, ostream& out) {
        	int N, s, w, acc = 0;
        	in >> N;
        	vector<int> v(N);
        	for (int j = 0; j < N; j++) {
        		in >> s >> w;
        		v[j] = (acc + s) % DAY_SECONDS;
        		acc = (acc + s + w) % DAY_SECONDS;
        	}
            sort(v.begin(), v.end());
        	int max = 0;
        	for (int t = 0; t < DAY_SECONDS; t++) {
				int crs = 0;
				for (int d = 0; ; d++) {
					vector<int>::iterator l = upper_bound(v.begin(), v.end(), 4*HOUR_SECONDS - 1 + d*DAY_SECONDS - t);
					vector<int>::iterator r = lower_bound(v.begin(), v.end(), 7*HOUR_SECONDS + 1 + d*DAY_SECONDS - t);
					crs += r-l;
					if (l == v.end())
						break;
				}
				if (crs > max)
                    max = crs;
        	}
        	out << max << endl;
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
