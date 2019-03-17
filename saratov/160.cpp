#include <cstdio>

#define MAXN 10000
#define MAXM 1000

struct pmult
{
	pmult(int aa, int bb):a(aa),b(bb){}
	pmult() {}
	int a, b;
};

int N, M;
int t[MAXN];
int tom[MAXM+1];
int R[MAXM];
pmult dde[MAXM];

void solve()
{
	int nR=1;
	tom[1]=1;
	R[0]=1;
	dde[1]=pmult(-1, -1);
	for (int j=0; j<N; j++)
	{
		int snR=nR;
		for (int k=0; k<snR; k++)
			if (!tom[(t[j]*R[k])%M])
			{
				R[nR++]=(t[j]*R[k])%M;
				dde[(t[j]*R[k])%M]=pmult(j, R[k]);
				tom[(t[j]*R[k])%M]=1;
			}
		if (tom[M-1]) break;
	}
	int j, no=0;
	for (j=M-1; j && !tom[j]; j--);
	printf("%d\n", j);
	if (j!=1)
	{
		while (dde[j].a!=-1)
		{
			tom[no++]=dde[j].a;
			j=dde[j].b;
		}
		for (no--; no>0; no--)
			printf("%d ", tom[no]+1);
		printf("%d\n", tom[0]+1);
	}
}
	
int main()
{
	scanf("%d%d", &N, &M);
	for (int j=0; j<N; j++)
	{
		scanf("%d", t+j);
		t[j]%=M;
	}
	solve();
	return 0;
}
