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

const char* PATTERN = "FESTIVAL";
const int CHUNK = 1 << 8;

class Node {
    public:
        int index;
        int64 count;

        Node(int index, int64 count) : index(index), count(count) {}
};

class Task {
    public:
        void solve(istream& in, ostream& out) {
            int64 K;
            in >> K;
            list<Node> ans;
            solve(K, strlen(PATTERN) - 1, ans);
            for (list<Node>::iterator i = ans.begin(); i != ans.end(); i++)
                for (int j = 0; j < i->count; j++)
                    out << PATTERN[i->index];
            out << endl;
        }
    private:
        void solve(int64 K, int index, list<Node>& ans) {
            if (index == 0)
                ans.emplace_back(Node(0, K));
            else {
                int64 r = K % CHUNK;
                int64 q = K / CHUNK;
                if (q == 0) {
                    for (int j = 0; j < index; j++)
                        ans.emplace_back(Node(j, 1));
                    ans.emplace_back(Node(index, r));
                }
                else {
                    if (r == 0) {
                        r = CHUNK;
                        q--;
                    }
                    solve(q, index - 1, ans);
                    list<Node>::iterator insert = find(index - 1, ans);
                    insert->count--;
                    ans.emplace(insert, Node(index - 1, 1));
                    ans.emplace(insert, Node(index, r));
                    if (q > 0)
                        ans.emplace_back(Node(index, CHUNK));
                }
            }
        }

        list<Node>::iterator find(int index, list<Node>& ans) {
            for (list<Node>::iterator i = ans.begin(); i != ans.end(); i++)
                if (i->index == index)
                    return i;
            return ans.end();
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
