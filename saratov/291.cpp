#include <cstdio>

#define MAXN 1000
#define MAXF 22204

const int mv[5][2]={{0,0},{-1,0},{0,1},{1,0},{0,-1}};

struct cell
{
	int x, y, f, r;
};

int q, c, n, t, x, y;
int T[MAXN][MAXN];
cell Q[MAXN*MAXN+1];
int qf=0, qt=-1;
int R[MAXF+1];

void Add(int x, int y, int f, int r)
{
	cell c;
	c.x=x; c.y=y; c.f=f; c.r=r;
	Q[++qt]=c;
}

bool empty()
{
	return qf>qt;
}

cell PopFront()
{
	return Q[qf++];
}

bool valid(int x, int y)
{
	return x>=0 && x<q && y>=0 && y<c;
}

void solve()
{
	while (!empty())
	{
		cell c=PopFront();
		if (c.r==t) break;
		for (int k=0; k<5; k++)
		{
			cell p=c;
			p.x+=mv[k][0];
			p.y+=mv[k][1];
			if (valid(p.x, p.y) && !T[p.x][p.y])
			{
				Add(p.x, p.y, p.f, p.r+1);
				T[p.x][p.y]=p.f;
			}
		}
	}
	for (int j=0; j<q; j++)
		for (int k=0; k<c; k++)
			R[T[j][k]]++;
	for (int j=1; j<=n; j++)
		printf("%d\n", R[j]);
}

int main()
{
	scanf("%d%d%d%d", &q, &c, &n, &t);
	for (int j=1; j<=n; j++)
	{
		scanf("%d%d", &x, &y);
		x--; y--;
		T[x][y]=j;
		Add(x, y, j, 0);
	}
	solve();
	return 0;
}
