#include <iostream>
#include <vector>
#include <map>

using namespace std;

class Solution {
public:
    int minTransfers(vector<vector<int>>& transactions) {
        map<int, int> debt;
        for (vector<int> i: transactions) {
            debt[i[0]] -= i[2];
            debt[i[1]] += i[2];
        }
        int p = 0, n = 0;
        for (map<int, int>::iterator i = debt.begin(); i != debt.end(); ++i)
             if (i->second > 0) p++;
             else
             if (i->second < 0) n++;
        return max(p, n);
    }
};

int main() {
    Solution s;
    vector<vector<int>> transactions;

    transactions.push_back(vector<int> {0,1,5});
    transactions.push_back(vector<int> {2,3,1});
    transactions.push_back(vector<int> {2,0,1});
    transactions.push_back(vector<int> {4,0,2});

    cout << s.minTransfers(transactions) << endl;

    return 0;
}
