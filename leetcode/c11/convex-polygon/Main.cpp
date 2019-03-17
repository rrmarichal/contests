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
// types
#define int64 long long
#define uint64 unsigned long long

class Task {
    public:
        bool isConvex(vector<vector<int>>& points) {
            int dir = 0;
            for (int j = 0; j < points.size(); j++) {
                int c = cross(points[j], points[(j+1) % points.size()], points[(j+2) % points.size()]);
                if (c != 0) {
                    if (c > 0 && dir < 0)
                        return false;
                    if (c < 0 && dir > 0)
                        return false;
                    dir = c;
                } 
            }
            return true;
        }

        int cross(vector<int>& p0, vector<int>& p1, vector<int>& p2) {
            return (p1[0]-p0[0]) * (p2[1]-p0[1]) - (p2[0]-p0[0]) * (p1[1]-p0[1]);
        }
} task;

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);
    istream& in(cin);
    ostream& out(cout);
    vector<vector<int>> points = vector<vector<int>> { {0,0}, {0,10}, {5,5}, {10,10}, {10,0} };
    out << task.isConvex(points) << endl;
    return 0;
}
