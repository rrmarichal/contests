#include <cstdio>

#define MAXN 524288

struct P
{
	int p, n;
};

int N;
char s[MAXN+1];
P L[2][MAXN];
char t[MAXN];

void print(P* A, int l)
{
	for (int j=0; j<l; j++)
		printf("%d %d\n", A[j].p, A[j].n);
}

void solve()
{
	int b=0, bits=0, rj, nt, step=0;
	for (int j=0; j<N; j++)
	{
		L[b][j].p=j;
		L[b][j].n=s[j]-'a';
		bits|=(s[j]-'a'+1);
	}
	if (bits!=3)
	{
		if (bits==1)
			printf("1\nb\n");
		else
			printf("1\na\n");
		return;
		
	}
	for (bits=2; ; bits++, b^=1, step++)
	{
		for (int j=0; j<(1<<bits); j++)
			t[j]=0;
		rj=0, nt=0;
		for (int j=0; j<N-step; j++)
		{
			P& x=L[b][j];
			if (x.p+bits>N)
				continue;
			L[1-b][rj].p=x.p;
			L[1-b][rj].n=2*x.n+s[x.p+bits-1]-'a';
			if (!t[L[1-b][rj].n])
			{
				t[L[1-b][rj].n]=1;
				nt++;
			}
			rj++;
		}
		if (nt<(1<<bits))
			break;
	}
	printf("%d\n", bits);
	for (int j=0; j<(1<<bits); j++)
	{
		if (!t[j])
		{
			for (int k=bits-1; k>=0; k--)
				if (j&(1<<k))
					printf("b");
				else
					printf("a");
			printf("\n");
			return;
		}
	}
}

int main()
{
	scanf("%d\n", &N);
	gets(s);
	solve();
	return 0;
}
