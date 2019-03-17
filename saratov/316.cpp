#include <cstdio>

int N, S;
int sc[6], hp[6];

int main()
{
	scanf("%d%d", &N, &S);
	for (int j=0; j<N; j++)
		hp[j]=100;
	for (int j=0; j<S; j++)
	{
		int x, y;
		scanf("%d%d", &x, &y);
		x--;
		y--;
		if (hp[y]>0)
			sc[x]+=3;
		hp[y]-=8;
	}
	for (int j=0; j<N; j++)
	{
		printf("%d ", hp[j]);
		if (hp[j]>0)
			printf("%d\n", sc[j]+hp[j]/2);
		else
			printf("%d\n", sc[j]);
	}
	return 0;
}
