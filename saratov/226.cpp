#include <cstdio>

#define MAXN 200
#define MAXC 3
#define oo 10000

struct inode
{
	inode() {}
	inode(int yy, int cc) { y=yy; c=cc; }
	int y, c;
};

int N, M;
bool T[MAXN][MAXN][MAXC];
inode adj[MAXN][MAXN];
int cadj[MAXN];
int d[MAXN][MAXC];
bool t[MAXN][MAXC];
int x, y, c;

int solve()
{
	for (int j=0; j<N; j++)
		for (int k=0; k<MAXC; k++)
			d[j][k]=oo;
	d[0][0]=d[0][1]=d[0][2]=0;
	int minv;
	inode m;
	while (1)
	{
		minv=oo;
		for (int j=0; j<N; j++)
			for (int k=0; k<MAXC; k++)
				if (d[j][k]<minv && !t[j][k])
				{
					minv=d[j][k];
					m=inode(j, k);
				}
		if (m.y==N-1)
			return minv;
		if (minv==oo)
			break;
		t[m.y][m.c]=true;
		for (int j=0; j<cadj[m.y]; j++)
		{
			inode& v=adj[m.y][j];
			if (v.c!=m.c && !t[v.y][v.c] && minv+1<d[v.y][v.c])
				d[v.y][v.c]=minv+1;
		}
	}
	return -1;
}

int main()
{
	scanf("%d%d", &N, &M);
	for (int j=0; j<M; j++)
	{
		scanf("%d%d%d", &x, &y, &c);
		x--; y--; c--;
		if (!T[x][y][c])
		{
			T[x][y][c]=true;
			adj[x][cadj[x]++]=inode(y, c);
		}
	}
	printf("%d\n", solve());
	return 0;
}
