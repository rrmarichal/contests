#include <cstdio>
#include <cstdlib>
#include <cstring>
#include <malloc.h>

#define MAXN 1000
#define MAXL 501

int N, o[MAXN], lens[MAXN];
char t[MAXN][MAXL+1];

int compare(char* s1, char* s2, int len)
{
	for (int j=0; j<len; j++)
		if (s1[j]<s2[j])
			return -1;
		else
		if (s1[j]>s2[j])
			return 1;
	return 0;
}

int cmp(const void* a, const void* b)
{
	int x=*(int*)a;
	int y=*(int*)b;
	if (lens[x]<lens[y])
		return -1;
	else
	if (lens[x]>lens[y])
		return 1;
	else
		return compare(t[x], t[y], lens[x]);
}

int max(int a, int b)
{
	return a>b?a:b;
}

char* suma(char* s1, char* s2)
{
	int ls1=strlen(s1), ls2=strlen(s2), m=max(ls1, ls2);
	char* rez=(char*)malloc(m+2);
	rez[m+1]=0;
	int prez=m, ps1=ls1-1, ps2=ls2-1, c=0;
	while (ps1>=0 || ps2>=0)
	{
		char s;
		if (ps1<0)
			s=s2[ps2]-48+c;
		else
		if (ps2<0)
			s=s1[ps1]-48+c;
		else
			s=s1[ps1]+s2[ps2]-96+c;
		if (s>9) c=1; else c=0;
		rez[prez]=(s%10)+48;
		prez--; ps1--; ps2--;
	}
	if (c) rez[prez--]=c+48;
	return rez+prez+1;
}

int mayor(char* s1, char* s2)
{
	if (strlen(s1)>strlen(s2))
		return 1;
	else
	if (strlen(s1)<strlen(s2))
		return 0;
	else
	{
		int q=compare(s1, s2, strlen(s1));
		return (q==1)?1:0;
	}
}

void solve()
{
	qsort(o, N, sizeof(int), cmp);
	for (int j=0; j<N-2; j++)
	{
		char* sum=suma(t[o[j]], t[o[j+1]]);
		if (mayor(sum, t[o[j+2]]))
		{
			printf("%s %s %s\n", t[o[j]], t[o[j+1]], t[o[j+2]]);
			return;
		}
	}
	printf("0 0 0\n");
}

int main()
{
	scanf("%d\n", &N);
	for (int j=0; j<N; j++)
	{
		scanf("%s\n", t[j]);
		lens[j]=strlen(t[j]);
		o[j]=j;
	}
	solve();
	return 0;
}
