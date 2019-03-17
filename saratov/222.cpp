#include <iostream>

using namespace std;

int n, k;
int min(int a, int b) { return a<b?a:b; }

int nw(int n, int m, int k)
{
	if (k>min(n, m))
		return 0;
	else
	if (k==0)
		return 1;
	else
	if (k==1)
		return n*m;
	else
	{
		int o=0;
		for (int r=0; r<=n-k; r++)
			o+=m*nw(n-r-1, m-1, k-1);
		return o;
	}
}
int main()
{
	cin >> n >> k;
	cout << nw(n, n, k) << endl;
	return 0;
}
