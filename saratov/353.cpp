#include <iostream>
using namespace std;

long long N, k1, k2, p1, p2, p3, sol;

long long solve()
{
	if (p1>N)
		return 0;
	int sol=k1;
	N-=p1;
	if (p2*k2<=N)
	{
		sol+=k2;
		N-=p2*k2;
	}
	else return sol+(N/p2)+(N%p2!=0);
	return sol+(N/p3)+(N%p3!=0);
}

int main()
{
	cin >> N >> k1 >> k2 >> p1 >> p2 >> p3;
	cout << solve() << endl;
	return 0;
}
