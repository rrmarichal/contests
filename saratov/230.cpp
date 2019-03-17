#include <cstdio>

#define MAXN 100
#define oo 10000

int N, M, x, y;
int c[MAXN], ID[MAXN], o[MAXN];
int L[MAXN][oo];
char T[MAXN];

void solve()
{
	for (int j=0; j<N; j++)
	{
		int v=-1;
		for (int k=0; k<N; k++)
			if (!T[k] && !ID[k])
			{
				v=k;
				break;
			}
		if (v==-1)
		{
			printf("No solution\n");
			return;
		}
		else
		{
			T[v]=1;
			o[v]=j;
			for (int k=0; k<c[v]; k++)
				ID[L[v][k]]--;
		}
	}
	for (int j=0; j<N-1; j++)
		printf("%d ", o[j]+1);
	printf("%d\n", o[N-1]+1);
}

int main()
{
	scanf("%d%d", &N, &M);
	for (int j=0; j<M; j++)
	{
		scanf("%d%d", &x, &y);
		x--; y--;
		L[x][c[x]++]=y;
		ID[y]++;
	}
	solve();
	return 0;
}
