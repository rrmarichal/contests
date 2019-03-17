#include <cstdio>

#define MAXN 100

int N, nd=0;
char d[MAXN][MAXN];

void del(int r, int c, int dr, int dc)
{
	while (r>=0 && r<N && c>=0 && c<N)
	{
		if (!d[r][c])
		{
			printf(" %d", r*N+c+1);
			nd++;
			d[r][c]=1;
		}
		r+=dr;
		c+=dc;
	}
}

int main()
{
	scanf("%d", &N);
	if (N%2)
	{
		int step=0, j;
		for (j=N; nd<N*N-5; j+=2, step++)
		{
			printf("%d", j);
			del(0, step, 1, -1);
			del(0, N-step-1, 1, 1);
			del(N-step-1, 0, 1, 1);
			del(N-1, N-step-1, -1, 1);
			printf("\n");
		}
		printf("%d", j);
		printf(" %d", (N/2-1)*N+N/2+1);
		printf(" %d", (N/2)*N+N/2);
		printf(" %d", (N/2)*N+N/2+2);
		printf(" %d\n", (N/2+1)*N+N/2+1);
	}
	else
	{
		if (N==2)
		{
			printf("3 1\n");
			printf("5 2 3\n");
		}
		else
		{
			int step=0, j;
			printf("%d %d %d\n", N+1, 1, N*N);
			nd=2;
			for (j=N+3; nd<N*N-8; j+=2, step++)
			{
				printf("%d", j);
				del(0, step+1, 1, -1);
				del(0, N-step-1, 1, 1);
				del(N-step-1, 0, 1, 1);
				del(N-1, N-step-2, -1, 1);
				printf("\n");
			}
			printf("%d", j);
			printf(" %d", (N/2-2)*N+N/2+1);
			printf(" %d", (N/2-1)*N+N/2+2);
			printf(" %d", (N/2-1)*N+N/2);
			printf(" %d", (N/2)*N+N/2-1);
			printf(" %d\n", (N/2+1)*N+N/2);
			printf("%d", j+2);
			printf(" %d", (N/2-1)*N+N/2+1);
			printf(" %d\n", (N/2)*N+N/2);
		}
	}
	fclose(stdout);
	return 0;
}
