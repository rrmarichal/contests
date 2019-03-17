#include <cstdio>

#define MAXT 33

int a, b;

int solve()
{
	if (a==0 || b==0)
		return 0;
	else
	if ((a+b)%2)
		return -1;
	else
	{
		int nsteps=0;
		while (nsteps<MAXT && a!=b)
		{
			if (a>b)
			{
				a-=b;
				b*=2;
			}
			else
			{
				b-=a;
				a*=2;
			}
			nsteps++;
		}
		return (nsteps==MAXT)?-1:nsteps+1;
	}
}

int main()
{
	scanf("%d%d", &a, &b);
	printf("%d\n", solve());
	return 0;
}
