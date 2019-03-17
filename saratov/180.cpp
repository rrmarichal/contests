#include <cstdio>

#define MAXN 65537

int N, t[2][MAXN], p, g;
long long rez=0;

void MS(int l, int h, char b)
{
	if (l==h)
	{
		t[b][l]=t[0][l];
		return;
	}
	int m=(l+h)/2;
	MS(l, m, 1-b);
	MS(m+1, h, 1-b);
	p=m+1; g=l;
	while (l<=m && p<=h)
	{
		if (t[1-b][l]<=t[1-b][p])
		{
			t[b][g]=t[1-b][l];
			l++;
		}
		else
		{
			rez+=((long long)m-(long long)l+1);
			t[b][g]=t[1-b][p];
			p++;
		}
		g++;
	}
	if (l<=m)
	{
		while (l<=m)
			t[b][g++]=t[1-b][l++];
	}
	else
	{
		while (p<=h)
			t[b][g++]=t[1-b][p++];
	}
}

int main()
{
	scanf("%d", &N);
	for (int j=0; j<N; j++)
		scanf("%d", &t[0][j]);
	MS(0, N-1, 1);
	printf("%lld\n", rez);
	return 0;
}
