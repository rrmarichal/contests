#include <cstdio>

#define MAXN 16000
#define ROOT 0

struct tnode { int node, pnext; };

int N;
tnode L[2*MAXN];
int first[MAXN], parent[MAXN], rank[MAXN], max[MAXN];
int out[MAXN];

int dfs(int node)
{
	int j=first[node];
	while (j!=-1)
	{
		if (L[j].node!=parent[node])
		{
			parent[L[j].node]=node;
			int q=dfs(L[j].node);
			rank[node]+=q;
			if (q>max[node])
				max[node]=q;
		}
		j=L[j].pnext;
	}
	rank[node]++;
	return rank[node];
}

int maxx(int a, int b)
{
	return a>b?a:b;
}

int solve()
{
	parent[ROOT]=-1;
	rank[ROOT]=dfs(ROOT);
	int minimax=N, cout=0;
	for (int j=0; j<N; j++)
	{
		int mchild=max[j];
		int mparent=j==ROOT?0:rank[ROOT]-rank[j];
		int q=maxx(mchild, mparent);
		if (q<minimax)
		{
			minimax=q;
			out[0]=j;
			cout=1;
		}
		else
		if (q==minimax)
			out[cout++]=j;
	}
	printf("%d %d\n", minimax, cout);
	for (int j=0; j<cout-1; j++)
		printf("%d ", out[j]+1);
	printf("%d\n", out[cout-1]+1);
	return 0;
}

int main()
{
	scanf("%d", &N);
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
	solve();
	return 0;
}
