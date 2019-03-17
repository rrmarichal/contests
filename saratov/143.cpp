#include <cstdio>

#define MAXN 16000
#define ROOT 0

struct tnode { int node, pnext; };

int N, max=-MAXN;
int	p[MAXN], first[MAXN], parent[MAXN];
tnode L[2*MAXN];

int dfs(int node)
{
	int k=first[node], rez=p[node];
	while (k!=-1)
	{
		if (L[k].node!=parent[node])
		{
			parent[L[k].node]=node;
			int q=dfs(L[k].node);
			if (q>0)
				rez+=q;
		}
		k=L[k].pnext;
	}
	if (rez>max) max=rez;
	return rez;
}

int main()
{
	scanf("%d", &N);
	for (int j=0; j<N; j++)
		scanf("%d", &p[j]);
	for (int j=0; j<N; j++)
		first[j]=-1;
	for (int j=0; j<N-1; j++)
	{
		int a, b;
		scanf("%d%d", &a, &b);
		a--; b--;
		L[2*j].node=b;
		L[2*j].pnext=first[a];
		L[2*j+1].node=a;
		L[2*j+1].pnext=first[b];
		first[a]=2*j; first[b]=2*j+1;
	}
	parent[ROOT]=-1;
	dfs(ROOT);
	printf("%d\n", max);
	return 0;
}

