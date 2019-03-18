/*
TASK: longest increasing subsequence
LANG: C++
*/

#include <cstdio>
#include <vector>
#include <algorithm>

#define oo 1000000000

using namespace std;

int n;
vector<int> t;

int main()
{
    freopen("lis.in", "r", stdin);
    freopen("lis.out", "w", stdout);
    scanf("%d", &n);
    t.push_back(-oo);
    for (int j = 0; j < n; j++)
    {
        int x;
        scanf("%d", &x);
        if (x > t.back())
            t.push_back(x);
        else
        {
            vector<int>::iterator i = lower_bound(t.begin(), t.end(), x);
            t[i - t.begin()] = x;
        }
    }
    printf("%d\n", t.size() - 1);
    fclose(stdin);
    fclose(stdout);
    return 0;
}
