#include <bits/stdc++.h>

using namespace std;

// Returns 1 if the i-th bit of x is set, 0 otherwise.
#define BIT_SET(x, i) (((x) & (1<<(i))) != 0)
// Sets the i-th bit of x to 1
#define SET_BIT(x, i) ((x) | (1<<(i)))
// Sets the i-th bit of x to 0
#define UNSET_BIT(x, i) ((x) & ~(1<<(i)))
// Loop from [0..n)
#define forn(j, n) for (int j = 0; j < (int)(n); j++)
// Loop from [a..b]
#define fore(j, a, b) for (int j = (int)(a); j < (int)(b); j++)

int main() {
    ios_base::sync_with_stdio(false);
    cin.tie(NULL);

#ifdef LOCAL_ENV
    freopen("0.in", "rt", stdin);
#endif

    return 0;
}
