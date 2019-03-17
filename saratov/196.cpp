#include <cstdio>

#define MAXN 10001

int t[MAXN];

int main()
{
	int n, m, j, sol=0, x, y;
	scanf("%d%d", &n, &m);
	for (j=0; j<m; j++)
	{
		scanf("%d%d", &x, &y);
		sol+=t[x]+t[y];
		t[x]++; t[y]++;
	}
	printf("%d\n", 2*(sol+m));
	return 0;
}
