#include <cstdio>

int out[40], c = 0;

int main()
{
	int n;
	scanf("%d", &n);
	if (!(n % 2))
		printf("No solution\n");
	else
	{
		while (n > 1)
			if ((n / 2) % 2)
			{
				out[c++] = 2;
				n = n / 2;
			}
			else
			{
				out[c++] = 1;
				n = n / 2 + 1;
			}
		printf("%d\n", c);
		for (; c > 1; c--)
			printf("%d ", out[c - 1]);
		if (c)
			printf("%d\n", out[0]);
	}
	return 0;
}

