#include <cstdio>
#include <cstdlib>
using namespace std;

#define MAXG 606
#define MAXN 606

class teeth
{
	public:
		teeth() {}
		teeth(int mm, int gg, int nn)
		{
			m=mm; g=gg, n=nn;
		}
		int m, g, n;
};

class tsol
{
	public:
		tsol() {}
		tsol(int jj, int kk, int bb)
		{
			j=jj; k=kk; b=bb;
		}
		bool valid()
		{
			return j>=0;
		}
		int j, k, b;
};

int N, M, P;
int G[MAXG];
int O[MAXN][MAXN+1][2];
int AcS[MAXN];
teeth T[MAXN];
tsol W[MAXN][MAXN+1][2];

int cmpf(const void* aa, const void* bb)
{
	teeth* a=(teeth*)aa;
	teeth* b=(teeth*)bb;
	if (a->g<b->g) return -1; else
	if (a->g==b->g && a->m<=b->m) return -1; else
	return 1;
}

int sum(int a, int b)
{
	if (a==0) return AcS[b]; else
	return AcS[b]-AcS[a-1];
}

int FO(int r, int c, int b)
{
	if (r<0) return 0; else
	return O[r][c][b];
}

tsol solve()
{
	for (int j=0; j<N; j++)
		for (int k=0; k<=N; k++)
			O[j][k][0]=O[j][k][1]=-1;
	AcS[0]=T[0].m;
	for (int j=1; j<N; j++)
		AcS[j]=AcS[j-1]+T[j].m;
	O[0][0][0]=0;
	O[0][0][1]=-1;
	O[0][1][0]=-1;
	O[0][1][1]=G[T[0].g]+T[0].m;
	W[0][1][1]=tsol(-1, -1, -1);
	W[0][0][0]=tsol(-1, -1, -1);
	int cng=1, max=(O[0][1][1]<=P)?1:0;
	tsol rez=(O[0][1][1]<=P)?tsol(0,1,1):tsol(-1, 0, -1);
	for (int j=1; j<N; j++)
	{
		cng++;
		if (T[j].g!=T[j-1].g) cng=1;
		for (int k=0; k<j+2; k++)
		{
			W[j][k][0]=W[j][k][1]=tsol(-1, -1, -1);
			if (O[j-1][k][0]!=-1)
			{
				O[j][k][0]=O[j-1][k][0];
				W[j][k][0]=tsol(j-1, k, 0);
			}
			if (O[j-1][k][1]!=-1 && (O[j][k][0]==-1 || O[j-1][k][1]<O[j][k][0]))
			{
				O[j][k][0]=O[j-1][k][1];
				W[j][k][0]=tsol(j-1, k, 1);
			}
			if (cng<=k)
			{
				int q=sum(j-cng+1, j)+G[T[j].g];
				int FO0=FO(j-cng, k-cng, 0);
				int FO1=FO(j-cng, k-cng, 1);
				if (FO0!=-1)
				{
					O[j][k][1]=FO0;
					W[j][k][1]=tsol(j-1, k-1, (cng!=1));
				}
				if (FO1!=-1 && (O[j][k][1]==-1 || FO1<O[j][k][1]))
				{
					O[j][k][1]=FO1;
					W[j][k][1]=tsol(j-1, k-1, 1);
				}
				if (O[j][k][1]!=-1)
					O[j][k][1]+=q;
			}
			if (max<k && (O[j][k][0]<=P && O[j][k][0]!=-1))
			{
				max=k;
				rez=tsol(j, k, 0);
			}
			if (max<k && (O[j][k][1]<=P && O[j][k][1]!=-1))
			{
				max=k;
				rez=tsol(j, k, 1);
			}
		}
	}
	return rez;
}

void out(tsol sol)
{
	bool ft=true;
	while (sol.valid())
	{
		if (sol.b==1)
		{
			if (ft)
				printf("\n%d", T[sol.j].n+1);
			else
				printf(" %d", T[sol.j].n+1);
			ft=false;
		}
		sol=W[sol.j][sol.k][sol.b];
	}
	printf("\n");
}

int main()
{
	scanf("%d%d%d", &N, &M, &P);
	for (int j=0; j<M; j++)
		scanf("%d", &G[j]);
	for (int j=0; j<N; j++)
	{
		int m, g;
		scanf("%d%d", &m, &g);
		T[j]=teeth(m, g-1, j);
	}
	qsort(T, N, sizeof(teeth), cmpf);
	tsol sol=solve();
	printf("%d", sol.k);
	out(sol);
	return 0;
}
