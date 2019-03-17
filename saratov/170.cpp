#include <cstdio>
#include <cstring>

#define MAXL 5000

char s1[MAXL + 1], s2[MAXL + 1];
int l1, l2;

int eq()
{
	int c1 = 0, c2 = 0;
	for (int j = 0; j < l1; j++)
	{
		c1 += s1[j] == '+';
		c2 += s2[j] == '+';
	}
	return c1 == c2;
}

void shift(char* s, int l)
{
	for (int j = 0; j < l; j++)
		s[l - j] = s[l - j - 1];
}

int solve(char* s1, char* s2)
{
	if (s1[0] == 0)
		return 0;
	if (s1[0] == s2[0])
		return solve(s1 + 1, s2 + 1);
	else
	{
		char* p = strchr(s2 + 1, s1[0]);
		shift(s2, p - s2);
		return p - s2 + solve(s1 + 1, s2 + 1);
	}
}

int main()
{
	scanf("%s%s", s1, s2);
	l1 = strlen(s1);
	l2 = strlen(s2);
	if (l1 != l2 || !eq())
		printf("-1\n");
	else
		printf("%d\n", solve(s1, s2));
	return 0;
}
