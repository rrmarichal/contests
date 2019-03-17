#include <cstdio>
#include <cmath>

double b, c, m;

double abs(double a) { return a<0?-a:a; }

int main()
{
	scanf("%lf%lf%lf", &c, &b, &m);
	double cosalfa=(4*m*m-b*b-c*c)/(2*b*c);
	if (abs(4*m*m-b*b-c*c)>2*b*c)
		printf("Mission impossible\n");
	else
	{
		printf("0.00000 0.00000\n");
		printf("%.5lf %.5lf\n", c*cosalfa, c*sqrt(1-cosalfa*cosalfa));
		printf("%.5lf 0.00000\n", b);
	}
	return 0;
}

