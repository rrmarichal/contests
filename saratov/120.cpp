#include <cstdio>
#include <cmath>

#define MAXN 150
#define PI 3.14159265358979323846

struct point
{
	point() {}
	point(double xx, double yy)
	{
		x=xx;
		y=yy;
	}
	double x, y;
};

int N, N1, N2;
point P[MAXN];

double dist(point p1, point p2)
{
	return sqrt((p1.x-p2.x)*(p1.x-p2.x)+(p1.y-p2.y)*(p1.y-p2.y));
}

point nextpoint(point p1, point p2, double beta, double l)
{
	double alfa, x, y;
	alfa=atan2(p2.y-p1.y, p2.x-p1.x);
	x=cos(alfa+beta)*l+p1.x;
	y=sin(alfa+beta)*l+p1.y;
	return point(x, y);
}

void solve()
{
	double C=(N-2)*PI/(double)N;
	double psin_a_i=1, psin_f_i=1, l;
	for (int k=N1; k<N2-1; k++)
	{
		double a_k=((-N+2*N2-2*k)*(PI-C)+2*C)/4;
		psin_a_i*=sin(a_k);
	}
	for (int k=N1+1; k<N2; k++)
	{
		double f_k=((N-2*N2+2*k)*(PI-C)+2*C)/4;
		psin_f_i*=sin(f_k);
	}
	l=dist(P[N1], P[N2])*psin_a_i/psin_f_i;
	P[(N1+1)%N]=nextpoint(P[N1], P[N2], ((-N+2*N2-2*N1)*(PI-C)+2*C)/4, l);
	for (int k=N1+2; k<N+N1; k++)
		P[k%N]=nextpoint(P[(k+N-1)%N], P[(k+N-2)%N], C, l);
	for (int j=0; j<N; j++)
		printf("%.6lf %.6lf\n", P[j].x, P[j].y);
}

int main()
{
	scanf("%d%d%d", &N, &N1, &N2);
	N1--; N2--;
	scanf("%lf%lf", &P[N1].x, &P[N1].y);
	scanf("%lf%lf", &P[N2].x, &P[N2].y);
	if (N1>N2) { int t=N1; N1=N2; N2=t; }
	solve();
	return 0;
}
