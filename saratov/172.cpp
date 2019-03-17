#include <cstdio>
#include <list>
using namespace std;

#define MAXS 30000
#define MAXE 200

struct st
{
	st() { e1=e2=-1; }
	st(int ee1, int ee2)
	{
		e1=ee1;
		e2=ee2;
	}
	int e1, e2;
};

int E, S;
st s[MAXS];
list<int> L[MAXE];
int day[MAXE];
char cf=0;

void dfs(int e, int d)
{
	day[e]=d;
	for (list<int>::iterator i=L[e].begin(); !cf && i!=L[e].end(); i++)
	{
		int o;
		if (s[*i].e1!=e)
			o=s[*i].e1;
		else
		if (s[*i].e2!=e)
			o=s[*i].e2;
		else
			continue;
		if (day[o]==-1)
			dfs(o, 1-d);
		else
		if (day[o]==d)
		{
			printf("no\n");
			cf=1;
		}
	}
	if (cf) return;
}

void solve()
{
	for (int j=0; j<E; j++)
		day[j]=-1;
	for (int j=0; !cf && j<E; j++)
	{
		if (day[j]==-1)
			dfs(j, 0);
	}
	if (!cf)
	{
		printf("yes\n");
		int c=0, first;
		for (int j=0; j<E; j++)
		{
			c+=!day[j];
			if (c==1 && !day[j])
				first=j;
		}
		printf("%d\n%d", c, first+1);
		for (int j=0; j<E; j++)
			if (!day[j] && j!=first)
				printf(" %d", j+1);

	}
}

int main()
{
	scanf("%d%d", &E, &S);
	for (int j=0; j<S; j++)
	{
		scanf("%d%d", &s[j].e1, &s[j].e2);
		s[j].e1--; s[j].e2--;
		L[s[j].e1].push_back(j);
		L[s[j].e2].push_back(j);
	}
	solve();
	return 0;
}
