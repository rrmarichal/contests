#include <cstdio>

#define MAXN 10000
#define oo 100000

const char* outs[]={ "OUTSIDE", "INSIDE", "BORDER" };

struct point { double x, y; };
struct seg { point p[2]; };

int N;
seg L[MAXN];
point p;

bool vertical(seg& s) { return s.p[0].x==s.p[1].x; }

bool bel(seg& s, point& p)
{
	if (vertical(s))
	{
		if (p.y==s.p[0].y || p.y==s.p[1].y)
			printf("Equals PRESENTATION\n");
		return ((p.y>s.p[0].y && p.y<s.p[1].y) || (p.y>s.p[1].y && p.y<s.p[0].y));
	}
	else
	{
		if (p.x==s.p[0].x || p.x==s.p[1].x)
			printf("Equals PRESENTATION\n");
		return ((p.x>s.p[0].x && p.x<s.p[1].x) || (p.x>s.p[1].x && p.x<s.p[0].x));
	}
}

bool border()
{
	for (int j=0; j<N; j++)
		if ((vertical(L[j]) && p.x==L[j].p[0].x))
			if ((p.y>=L[j].p[0].y && p.y<=L[j].p[1].y) || (p.y>=L[j].p[1].y && p.y<=L[j].p[0].y))
				return true;
			else;
		else
			if (p.y==L[j].p[0].y)
			if ((p.x>=L[j].p[0].x && p.x<=L[j].p[1].x) || (p.x>=L[j].p[1].x && p.x<=L[j].p[0].x))
				return true;
	return false;
}

int solve()
{
	if (border()) return 2;
	double A=oo, B=-oo+1, C=A*p.x+B*p.y;
	point pi;
	int count=0;
	for (int j=0; j<N; j++)
	{
		if (vertical(L[j]))
		{
			pi.x=L[j].p[0].x;
			pi.y=(C-A*pi.x)/B;
		}
		else
		{
			pi.y=L[j].p[0].y;
			pi.x=(C-B*pi.y)/A;
		}
		if (p.x<pi.x && bel(L[j], pi))
			count++;
	}
	return count%2;
}

int main()
{
	scanf("%d", &N);
	for (int j=0; j<N; j++)
		for (int k=0; k<2; k++)
			scanf("%lf%lfd", &L[j].p[k].x, &L[j].p[k].y);
	scanf("%lf%lf", &p.x, &p.y);
	printf("%s\n", outs[solve()]);
	return 0;
}
