#include <cstdio>

#define MAXM 1001

int A, a, b, c, M, K;
int pr[MAXM], xi[MAXM];

int solve()
{
	int j;
	for (j=0; j<M; j++)
		pr[j]=-1;
	pr[A%M]=0;
	xi[0]=A;
	A%=M;
	for (j=1; ; j++)
	{
		A=(a*A*A+b*A+c)%M;
		if (pr[A]!=-1)
			break;
		pr[A]=j;
		xi[j]=A;
	}
	if (K<pr[A])
		return xi[K];
	else
	{
		int lsec=j-pr[A];
		int psec=(K-j+lsec)%(lsec);
		return xi[pr[A]+psec];
	}
}

int main()
{
	scanf("%d%d%d%d%d%d", &A, &a, &b, &c, &M, &K);
	printf("%d\n", solve());
	return 0;
}
