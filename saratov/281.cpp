#include <cstdio>
#include <cstdlib>
#include <cstring>
#include <cassert>

#define MAXN 50000
#define MAXL 20

struct string
{
	char s[MAXL+1];
	int poz;
};

int N;
string C1[MAXN];
string C2[MAXN];

int cmp(const void* a, const void* b)
{
	string* pa=(string*)a;
	string* pb=(string*)b;
	return strcmp(pa->s, pb->s);
}

int search(string* s, int l, int h)
{
	int med, q;
	while (l<=h)
	{
		med=(l+h)/2;
		q=strcmp(s->s, C2[med].s);
		if (q<0)
			h=med-1;
		else
		if (q>0)
			l=med+1;
		else
			return C2[med].poz;
	}
	return -1;
}

void solve()
{
	qsort(C2, N, sizeof(string), cmp);
	int q, max=-1, tp=0;
	for (int j=0; j<N; j++)
	{
		q=search(&C1[j], 0, N);
		assert(q!=-1);
		max>?=q;
		if (j==max)
		{
			qsort(C1+tp, j-tp+1, sizeof(string), cmp);
			for (int k=tp; k<=j; k++)
				printf("%s\n", C1[k].s);
			tp=j+1;
		}
	}
}

int main()
{
	scanf("%d\n", &N);
	for (int j=0; j<N; j++)
		gets(C1[j].s);
	for (int j=0; j<N; j++)
	{
		gets(C2[j].s);
		C2[j].poz=j;
	}
	solve();
	return 0;
}
