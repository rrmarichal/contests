#include <cstdio>
#include <cassert>

#define MAXN 2005

class tadj
{
	public:
		tadj() {}
		tadj(short x, short y) { n=x; pnext=y; }
		short n, pnext;
};

class tedge
{
	public:
		tedge() {}
		tedge(short x, short y) { v1=x; v2=y; }
		short v1, v2;
};

class tout
{
	public:
		tout() {}
		tout(tedge e1, tedge e2) { added=e1; deleted=e2; }
		tedge added, deleted;
};

tadj adj[MAXN][MAXN];
short adjc[MAXN];
short first[MAXN];
char M1[MAXN][MAXN], M2[MAXN][MAXN];
tedge el[MAXN];
int P[MAXN], nvP;
short L[2][MAXN];
char tom[MAXN];
short dde[MAXN];
tout O[MAXN];
int nO;

int N, x, y;

void add_edge(int x, int y)
{
	adj[x][adjc[x]].n=y;
	adj[x][adjc[x]].pnext=adjc[x]+1;
	adj[y][adjc[y]].n=x;
	adj[y][adjc[y]].pnext=adjc[y]+1;
	adjc[x]++; adjc[y]++;
	M2[x][y]=M2[y][x]=1;
}

void del(int x, int y)
{
	int k=first[x], last;
	while (k<adjc[x] && adj[x][k].n!=y)
	{
		last=k;
		k=adj[x][k].pnext;
	}
	assert(k<adjc[x]);
	if (k==first[x])
		first[x]=adj[x][first[x]].pnext;
	else
		adj[x][last].pnext=adj[x][k].pnext;
}

void del_edge(int x, int y)
{
	M2[x][y]=M2[y][x]=0;
	del(x, y);
	del(y, x);
}

void FP(int v1, int v2)
{
	int b=0, cp=1, rj, v, vn, k;
	char found=0;
	for (int j=0; j<N; j++) tom[j]=0;
	tom[v1]=1;
	L[b][0]=v1;
	while (!found && cp)
	{
		rj=0;
		for (int j=0; !found && j<cp; j++)
		{
			v=L[b][j];
			k=first[v];
			while (!found && k<adjc[v])
			{
				vn=adj[v][k].n;
				if (!tom[vn])
				{
					tom[vn]=1;
					dde[vn]=v;
					L[1-b][rj++]=vn;
					found=(vn==v2);
				}
				k=adj[v][k].pnext;
			}
		}
		b=1-b; cp=rj;
	}
	nvP=0;
	while (v2!=v1)
	{
		P[nvP++]=v2;
		v2=dde[v2];
	}
	P[nvP++]=v1;
}

void solve()
{
	for (int j=0; j<N-1; j++)
		if (!M2[el[j].v1][el[j].v2])
		{
			FP(el[j].v1, el[j].v2);
			int k=0;
			while (k<nvP && M1[P[k]][P[k+1]]) k++;
			assert(k<nvP);
			del_edge(P[k], P[k+1]);
			add_edge(el[j].v1, el[j].v2);
			O[nO].added=tedge(el[j].v1, el[j].v2);
			O[nO].deleted=tedge(P[k], P[k+1]);
			nO++;
		}
	printf("%d\n", nO);
	for (int j=0; j<nO; j++)
		printf("2 %d %d %d %d\n", O[j].added.v1+1, O[j].added.v2+1, O[j].deleted.v1+1, O[j].deleted.v2+1);
}

int main()
{
	scanf("%d", &N);
	for (int j=0; j<N-1; j++)
	{
		scanf("%d%d", &x, &y);
		x--; y--;
		el[j]=tedge(x, y);
		M1[x][y]=M1[y][x]=1;
	}
	for (int j=0; j<N-1; j++)
	{
		scanf("%d%d", &x, &y);
		x--; y--;
		add_edge(x, y);
	}
	solve();
	fclose(stdin); fclose(stdout);
	return 0;
}
