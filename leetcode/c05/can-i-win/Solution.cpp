#include <iostream>
#include <vector>
#include <string>

using namespace std;

// Returns 1 if the i-th bit of x is set, 0 otherwise.
#define BIT_SET(x, i) (((x) & (1<<(i))) != 0)
// Sets the i-th bit of x to 1
#define SET_BIT(x, i) ((x) | (1<<(i)))
// Sets the i-th bit of x to 0
#define UNSET_BIT(x, i) ((x) & ~(1<<(i)))

using namespace std;

class Solution {
  public:
    bool canIWin(int maxChoosableInteger, int desiredTotal) {
        if (desiredTotal == 0)
            return true;
        if (desiredTotal > maxChoosableInteger * (maxChoosableInteger + 1) / 2)
            return false;
        vector<char> T((1 << maxChoosableInteger), 0);
        return minMax(0, 0, desiredTotal, maxChoosableInteger, T) == 1;
    }

    char minMax(int currentSum, int currentSet, int total, int pool, vector<char>& T) {
        if (currentSum >= total)
            return T[currentSet] = 2;
        if (T[currentSet] != 0)
            return T[currentSet];
        for (int b = 0; b < pool; b++)
            if (!BIT_SET(currentSet, b))
                if (minMax(currentSum + b + 1, SET_BIT(currentSet, b), total, pool, T) == 2)
                    return T[currentSet] = 1;
        return T[currentSet] = 2;
    }

};

int main() {
    Solution s;
    int maxChoosableInteger, desiredTotal;
    cin >> maxChoosableInteger >> desiredTotal;
    cout << s.canIWin(maxChoosableInteger, desiredTotal) << endl;
    return 0;
}
