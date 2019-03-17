#include <cstdio>
#include <cstring>

int main()
{
	int n;
	scanf("%d\n", &n);
	char s[30];
	for (; n; n--)
	{
		gets(s);
		int l = strlen(s);
		if ((s[l - 2] == 'c' && s[l - 1] == 'h') || s[l - 1] == 'x' || s[l - 1] == 'o' || s[l - 1] == 's')
			printf("%ses\n", s);
		else
		if (s[l - 1] == 'f')
		{
			s[l - 1] = 0;
			printf("%sves\n", s);
		}
		else
		if (s[l - 2] == 'f' && s[l - 1] == 'e')
		{
			s[l - 2] = 0;
			printf("%sves\n", s);
		}
		else
		if (s[l - 1] == 'y')
		{
			s[l - 1] = 0;
			printf("%sies\n", s);
		}
		else
			printf("%ss\n", s);
	}
	return 0;
}
