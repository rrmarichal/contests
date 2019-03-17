#include <cstdio>
#include <cstring>

#define MAXN 200

int rc[4], n;
char rep[4][16][3];
char s[MAXN + 2], c[MAXN + 2];
char t[MAXN + 2][MAXN + 2][4];

char enc(char c)
{
	return (c == 'b') ? 0 : (c == 'r' ? 1 : (c == 'y' ? 2 : 3));
}

char dec(char c)
{
	return (c == 0) ? 'b' : (c == 1 ? 'r' : (c == 2 ? 'y' : 'w'));
}

void solve()
{
	for (int j = 0; j < n; j++)
		t[j][j][enc(s[j])] = 1;
	for (int l = 2; l <= n; l++)
		for (int j = 0; j < n - l + 1; j++)
		{
			for (int i = 0; i < 4; i++)
			{
				int k = j;
				while (!t[j][j + l - 1][i] && k < j + l - 1)
				{
					//calc t[j][j + l - 1][i]
					//using t[j][k][p] - t[k+1][j+l-1][q] reduction
					//such that pq is a valid replacement for color i.
					for (int r = 0; r < rc[i]; r++)
					{
						char p = enc(rep[i][r][0]);
						char q = enc(rep[i][r][1]);
						if (t[j][k][p] && t[k + 1][j + l - 1][q])
						{
							t[j][j + l - 1][i] = 1;
							break;
						}
					}
					k++;
				}
			}
		}
	char p = 0;
	for (int j = 0; j < 4; j++)
		if (t[0][n - 1][j])
		{
			printf("%c", dec(j));
			p = 1;
		}
	if (!p)
		printf("Nobody");
	printf("\n");
}

int main()
{
	for (int j = 0; j < 4; j++)
		scanf("%d", rc + j);
	scanf("\n");
	for (int j = 0; j < 4; j++)
		for (int k = 0; k < rc[j]; k++)
			scanf("%s", rep[j][k]);
	scanf("%s", s);
	n = strlen(s);
	solve();
	return 0;
}
