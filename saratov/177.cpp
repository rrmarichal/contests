#include <cstdio>

#define MAXM 5000

#define MINI(a,b) (a<b?a:b)
#define MAXI(a,b) (a<b?b:a)

class rect
{
	public:
		int lx, ly, ux, uy, cl;
	rect() {}
	rect(int llx, int lly, int uux, int uuy, int ccl)
	{
		lx=llx, ly=lly, ux=uux, uy=uuy, cl=ccl;
	}
	int inside(const rect &a)
	{
		return (lx>=a.lx && ly>=a.ly && ux<=a.ux && uy<=a.uy);
	}
	int left(const rect &a)
	{
		return (lx<a.lx && ux>=a.lx && uy>=a.ly && ly<=a.uy);
	}
	int right(const rect &a)
	{
		return (ux>a.ux && lx<=a.ux && uy>=a.ly && ly<=a.uy);
	}
	int down(const rect &a)
   {
		return (ly<a.ly && uy>=a.ly && ux>=a.lx && lx<=a.ux);
	}
	int up(const rect &a)
	{
		return (uy>a.uy && ly<=a.uy && ux>=a.lx && lx<=a.ux);
	}
};

int n, m, w=0;
rect r[MAXM+1];

void tt(rect rr, int i)
{
	int split=0;
	if (i<=m)
	{
		if (rr.inside(r[i])) return;
		if (rr.left(r[i]))
		{
			split=1;
			tt(rect(rr.lx, rr.ly, r[i].lx-1, rr.uy, rr.cl), i+1);
		}
		if (rr.right(r[i]))
		{
			split=1;
			tt(rect(r[i].ux+1, rr.ly, rr.ux, rr.uy, rr.cl), i+1);
		}
		if (rr.down(r[i]))
		{
			split=1;
			tt(rect(MAXI(rr.lx, r[i].lx), rr.ly, MINI(rr.ux, r[i].ux), r[i].ly-1, rr.cl), i+1);
		}
		if (rr.up(r[i]))
		{
			split=1;
			tt(rect(MAXI(rr.lx, r[i].lx), r[i].uy+1, MINI(rr.ux, r[i].ux), rr.uy, rr.cl), i+1);
		}
	}
	if (split) return;
	if (i>m && rr.cl==0)
		w+=(rr.ux-rr.lx+1)*(rr.uy-rr.ly+1);
	else
		tt(rr, i+1);
}

void swap(int& a, int& b)
{
	int t=a;a=b;b=t;
}

int main()
{
	scanf("%d%d", &n, &m);
	for (int j=1; j<=m; j++)
	{
		rect rr;
		char c;
		scanf("%d%d%d%d", &rr.lx, &rr.ly, &rr.ux, &rr.uy);
		do { scanf("%c", &c); } while (c==' ');
		rr.cl=(c=='w')?0:1;
		if (rr.lx>rr.ux)
			swap(rr.lx, rr.ux);
		if (rr.ly>rr.uy)
			swap(rr.ly, rr.uy);
		r[j]=rr;
	}
	r[0].lx=1; r[0].ly=1; r[0].ux=n; r[0].uy=n; r[0].cl=0;
	for (int j=m; j>=0; j--)
		if (r[j].cl==0)
			tt(r[j], j+1);
	printf("%d\n", w);
	fclose(stdin); fclose(stdout);   
	return 0;
}
