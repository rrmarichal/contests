#include <cstdio>
#include <cstdlib>

#define MAXN 50000

struct ninfo
{
	int k, a, n;
};

struct interval
{
	interval() {}
	interval(int ff, int ll)
	{
		f=ff; l=ll;
	}
	int f, l;
};

struct rtreenode
{
	interval i;
	int pleft, pright, minvp;
};

struct treenode
{
	int parent, left, right;
};

struct tlist
{
	interval i;
	int pnode, pleft, pright;
};

int N;
int rtreeroot, nnodes;
ninfo nodes[MAXN];
rtreenode rtree[2*MAXN];
treenode tree[MAXN+1];
tlist L[MAXN];

int min(int a, int b) { return a<b?a:b; }
int max(int a, int b) { return a>b?a:b; }

int cmp(const void* a, const void* b)
{
	if (((ninfo*)a)->k<((ninfo*)b)->k)
		return -1;
	else
		return 1;
}

int length(const interval& i)
{
	return i.l-i.f+1;
}

bool inside(const interval& r, const interval& i)
{
	return (r.f>=i.f && r.l<=i.l);
}

int Find_Min(interval i, rtreenode r)
{
	if (inside(r.i, i))
		return r.minvp;
	else
	{
		rtreenode left=rtree[r.pleft];
		rtreenode right=rtree[r.pright];
		int ml=-1, mr=-1;
		if (i.l>=right.i.f)
			mr=Find_Min(i, right);
		if (i.f<=left.i.l)
			ml=Find_Min(i, left);
		if (ml==-1)
			return mr;
		else
		if (mr==-1)
			return ml;
		else
			return (nodes[ml].a<nodes[mr].a)?ml:mr;
	}
}

void BuildTree()
{
	int cp=1;
	L[0].i=interval(0, N-1);
	for (int j=0; j<N; j++)
	{
		int poz=Find_Min(L[j].i, rtree[rtreeroot]);
		L[j].pnode=poz;
		if (poz>L[j].i.f)
		{
			L[j].pleft=cp;
			L[cp].i=interval(L[j].i.f, poz-1);
			cp++;
		}
		if (poz<L[j].i.l)
		{
			L[j].pright=cp;
			L[cp].i=interval(poz+1, L[j].i.l);
			cp++;
		}
	}
	for (int j=0; j<N; j++)
	{
		int node=nodes[L[j].pnode].n;
		if (L[j].pleft!=0)
		{
			tree[node].left=nodes[L[L[j].pleft].pnode].n;
			tree[tree[node].left].parent=node;
		}
		if (L[j].pright!=0)
		{
			tree[node].right=nodes[L[L[j].pright].pnode].n;
			tree[tree[node].right].parent=node;
		}
	}
}

int BuildRangeTree(interval i)
{
	if (length(i)==1)
	{
		rtree[nnodes].i=i;
		rtree[nnodes].pleft=-1;
		rtree[nnodes].pright=-1;
		rtree[nnodes].minvp=i.f;
		return nnodes++;
	}
	else
	{
		int pleftchild=BuildRangeTree(interval(i.f, (i.f+i.l)/2));
		int prightchild=BuildRangeTree(interval((i.f+i.l)/2+1, i.l));
		rtree[nnodes].i=i;
		rtree[nnodes].pleft=pleftchild;
		rtree[nnodes].pright=prightchild;
		int pminleft=rtree[pleftchild].minvp;
		int pminright=rtree[prightchild].minvp;
		rtree[nnodes].minvp=(nodes[pminleft].a<nodes[pminright].a)?pminleft:pminright;
		return nnodes++;
	}
}

void out()
{
	printf("YES\n");
	for (int j=1; j<=N; j++)
		printf("%d %d %d\n", tree[j].parent, tree[j].left, tree[j].right);
}

void solve()
{
	qsort(nodes, N, sizeof(ninfo), cmp);
	nnodes=0;
	rtreeroot=BuildRangeTree(interval(0, N-1));
	BuildTree();
	out();
}

int main()
{
	scanf("%d", &N);
	for (int j=0; j<N; j++)
	{
		scanf("%d%d", &nodes[j].k, &nodes[j].a);
		nodes[j].n=j+1;
	}
	solve();
	fclose(stdout);
	return 0;
}
