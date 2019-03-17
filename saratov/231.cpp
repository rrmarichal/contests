#include <cstdio>
#include <cmath>

#define MAXN 1000000
#define MAXP 8169

char c[MAXN+1];
int o[MAXP+1];
int N;

int main()
{
	scanf("%d", &N);
	for (int j=2; j<=(int)sqrt(N); j++)
	{
		while (c[j]) j++;
		for (int k=2*j; k<=N; k+=j)
			c[k]=1;
	}
	int rez=0;
	for (int j=3; j<=N-2; j++)
		if (!c[j]&&!c[j+2])
			o[rez++]=j;
	printf("%d\n", rez);
	for (int j=0; j<rez; j++)
		printf("2 %d\n", o[j]);
	return 0;
}
