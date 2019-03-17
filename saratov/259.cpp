#include <cstdio>
#include <cstdlib>

#define MAXN 100

struct Leaflet
{
	int p, d;
};

int N;
Leaflet L[MAXN];

int cmp(const void* a, const void* b)
{
	Leaflet* x=(Leaflet*)a;
	Leaflet* y=(Leaflet*)b;
	if (x->d>y->d)
		return -1;
	else
	if (x->d<y->d)
		return 1;
	else
	{
		if (x->p>y->p)
			return -1;
		else
		if (x->p<y->p)
			return 1;
		else
			return 0;
	}
}

int solve()
{
	qsort(L, N, sizeof(Leaflet), cmp);
	int q=0, max=0;
	for (int j=0; j<N; j++)
	{
		if (q+L[j].p+L[j].d>max)
			max=q+L[j].p+L[j].d;
		q+=L[j].p;
	}
	return max;
}

int main()
{
	scanf("%d", &N);
	for (int j=0; j<N; j++)
		scanf("%d", &L[j].p);
	for (int j=0; j<N; j++)
		scanf("%d", &L[j].d);
	printf("%d\n", solve());
	return 0;
}
