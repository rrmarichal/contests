#include <cstdio>

#define MAXN 10000
#define e 0.0000000001

int N;
double s, v[MAXN];

double fracc(double d)
{
	return d-(int)d;
}

int main()
{
	scanf("%d", &N);
	for (int j=0; j<N; j++)
	{
		scanf("%lf", &v[j]);
		s+=v[j];
	}
	double fp=fracc(v[0]/s*100), p=v[0]/s*100;
	printf("%d", (int)p);
	for (int j=1; j<N; j++)
	{
		p=v[j]/s*100;
		if (fp+fracc(p)>=1-e)
		{
			fp+=fracc(p)-1;
			printf(" %d", (int)p+1);
		}
		else
		{
			fp+=fracc(p);
			printf(" %d", (int)p);
		}
	}
	printf("\n");
	return 0;
}
