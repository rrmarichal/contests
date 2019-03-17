#include <cstdio>
#include <cstring>

#define MAXN 200
#define MAXX 10000

int n, x;
int c[MAXN];
int b;
char p[MAXX + 1];
int q[MAXX + 1], qs;
int s[MAXN], ss = 0;

int pay()
{
	memset(p, 0, MAXX + 1);
	qs = 1;
	for (int j = 0; j < n; j++)
	{
		if (j != b)
		{
			int qss = qs;
			for (int k = 0; k < qs; k++)
				if (q[k] + c[j] <= x && !p[q[k] + c[j]])
				{
					q[qss++] = q[k] + c[j];
					p[q[k] + c[j]] = 1;
				}
			qs = qss;
		}
	}
	return p[x];
}

int main()
{
	scanf("%d%d", &n, &x);
	for (int j = 0; j < n; j++)
		scanf("%d", &c[j]);
	for (b = 0; b < n; b++)
	{
		if (!pay())
			s[ss++] = b;
	}
	printf("%d\n", ss);
	for (int j = 0; j < ss - 1; j++)
		printf("%d ", c[s[j]]);
	if (ss > 0)
		printf("%d\n", c[s[ss - 1]]);
	
	return 0;
}
