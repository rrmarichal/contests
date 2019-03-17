#include <cstdio>

using namespace std;

int DR(long long n)
{
	if (n<10)
		return n;
	else
	{
		long long s=0;
		while (n)
		{
			s+=n%10;
			n=n/10;
		}
		return DR(s);
	}
}

int main()
{
	int n, c, q, s, ant;
	scanf("%d", &n);
	for (int j=0; j<n; j++)
	{
		scanf("%d", &c);
		s=0; ant=1;
		for (int k=0; k<c; k++)
		{
			scanf("%d", &q);
			ant=DR((long long)ant*(long long)q);
			s+=ant;
		}
		printf("%d\n", DR(s));
	}
	return 0;
}
