#include <cstdio>

int N;

int nz(int n)
{
	int rez=0;
	for (int q=5; q<=n; q*=5)
		rez+=n/q;
	return rez;
}

int solve()
{
	if (N==0)
		return 1;
	int l=5, h=5*N;
	while (l<=h)
	{
		int med=(l+h)/2;
		int z=nz(med);
		if (z==N)
			return med-(med%5);
		if (z>N)
			h=med-1;
		else
			l=med+1;
	}
	return -1;
}

int main()
{
	scanf("%d", &N);
	int q=solve();
	if (q==-1)
		printf("No solution\n");
	else
		printf("%d\n", q);
	return 0;
}
