#include <cstdio>
#include <cstdlib>

#define MAXN 16000

struct post { int x, l, n; };
struct theap { int post, n; };

int N, nitems=0;
post L[2*MAXN];
bool in[MAXN];
theap heap[MAXN];
int pheap[MAXN];

int cmp(const void* a, const void* b)
{
	if (((post*)a)->x<((post*)b)->x)
		return -1;
	else
		return +1;
}

void swapp(int a, int b)
{
	pheap[heap[a].n]=b;
	pheap[heap[b].n]=a;
	theap temp=heap[a];
	heap[a]=heap[b];
	heap[b]=temp;
}

void UP(int poz)
{
	while (poz>0)
	{
		if (heap[poz].post>heap[(poz-1)/2].post)
			swapp(poz, (poz-1)/2);
		else
			break;
		poz=(poz-1)/2;
	}
}

void DOWN(int poz)
{
	while (2*poz+1<nitems)
	{
		if (heap[2*poz+1].post>heap[poz].post && heap[2*poz+1].post>heap[2*(poz+1)].post)
		{
			swapp(poz, 2*poz+1);
			poz=2*poz+1;
		}
		else
		if (heap[2*(poz+1)].post>heap[poz].post)
		{
			swapp(poz, 2*(poz+1));
			poz=2*(poz+1);
		}
		else break;
	}
}

void INSERT(int rpost, int n)
{
	pheap[n]=nitems;
	heap[nitems].post=rpost;
	heap[nitems].n=n;
	nitems++;
	UP(nitems-1);
}

void DELETE(int poz)
{
	swapp(poz, nitems-1);
	nitems--;
	DOWN(poz);
}

int solve()
{
	qsort(L, 2*N, sizeof(post), cmp);
	int sln=0;
	for (int j=0; j<N; j++)
		pheap[j]=-1;
	for (int j=0; j<2*N; j++)
	{
		if (in[L[j].n])
		{
			int p=pheap[L[j].n];
			DELETE(p);
		}
		else
		{
			if (nitems>0 && heap[0].post>L[j].x+L[j].l)
				sln++;
			in[L[j].n]=true;
			INSERT(L[j].x+L[j].l, L[j].n);
		}
	}
	return sln;
}

int main()
{
	scanf("%d", &N);
	for (int j=0; j<N; j++)
	{
		int x, y;
		scanf("%d%d", &x, &y);
		L[2*j].x=x;
		L[2*j].l=y-x;
		L[2*j].n=j;
		
		L[2*j+1].x=y;
		L[2*j+1].l=y-x;
		L[2*j+1].n=j;
	}
	printf("%d\n", solve());
	return 0;
}
