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
        	double r1, r2;
        	in >> r1 >> r2;
        	printf("%.10f\n", 1/(1/r1 + 1/r2));
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
