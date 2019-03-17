#include <cstdio>

#define MAXN 10000

struct stree
{
	int node, cost, pnext;
};

int N;
int first[MAXN], parent[MAXN];
stree tree[MAXN];
int MD[MAXN], sMD[MAXN], MU[MAXN], nMD[MAXN];

int cMD(int node)
{
	int k=first[node];
	if (k!=-1)
	{
		do
		{
			int q=cMD(tree[k].node)+tree[k].cost;
			if (q>MD[node])
			{
				sMD[node]=MD[node];
				MD[node]=q;
				nMD[node]=tree[k].node;
			}
			else
			if (q>sMD[node])
				sMD[node]=q;
			k=tree[k].pnext;
		} while (k!=-1);
	}
	return MD[node];
}

inline int max(int a, int b)
{
	return a>?b;
}

void cMU(int node, int edgecost)
{
	if (parent[node]==-1)
		MU[node]=0;
	else
	{
		int p=parent[node];
		if (nMD[p]==node)
			MU[node]=max(MU[p], sMD[p])+edgecost;
		else
			MU[node]=max(MU[p], MD[p])+edgecost;
	}
	int k=first[node];
	while (k!=-1)
	{
		cMU(tree[k].node, tree[k].cost);
		k=tree[k].pnext;
	}
}

void solve()
{
	cMD(0);
	cMU(0, 0);
	for (int j=0; j<N; j++)
		printf("%d\n", max(MD[j], MU[j]));
}

int main()
{
	scanf("%d", &N);
	for (int j=0; j<N; j++)
		first[j]=-1;
	parent[0]=-1;
	for (int j=1; j<N; j++)
	{
		int x, c;
		scanf("%d%d", &x, &c);
		x--;
		parent[j]=x;
		tree[j-1].node=j;
		tree[j-1].cost=c;
		tree[j-1].pnext=first[x];
		first[x]=j-1;
	}
	solve();
	return 0;
}
