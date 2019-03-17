#include <cstdio>

#define MAXN 50

int al[MAXN][MAXN];
int ll[MAXN];
char fx[MAXN];
int sl = 0, n, x;
char fff[MAXN];

int main()
{
	scanf("%d%d", &n, &x);
	x--;
	for (int j = 0; j < n; j++)
	{
		scanf("%d", ll + j);
		for (int k = 0; k < ll[j]; k++)
		{
			scanf("%d", &al[j][k]);
			al[j][k]--;
			if (j == x)
				fx[al[j][k]] = 1;
		}
	}
	for (int j = 0; j < ll[x]; j++)
	{
		int f = al[x][j];
		for (int k = 0; k < ll[f]; k++)
		{
			int ff = al[f][k];
			if (!fx[ff] && ff != x && !fff[ff])
			{
				sl++;
				fff[ff] = 1;
			}
		}
	}
	printf("%d\n", sl);
	int p = 0;
	for (int j = 0; j < n; j++)
	{
		if (fff[j])
		{
			p++;
			if (p == sl)
				printf("%d\n", j + 1);
			else
				printf("%d ", j + 1);
		}
	}

	return 0;
}
