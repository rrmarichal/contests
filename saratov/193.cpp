#include <cstdio>
#include <cstring>
#include <cstdlib>

#define MAXL 2000

const int m[]={1,0,2,0};

char n[MAXL+1];
char no2[MAXL/2+1];

int getnum(int p, int l)
{
	int r=n[p]-'0';
	if (l==2) r=r*10+n[p+1]-'0';
	return r;
}

void subs(int x)
{
	char* p=no2+strlen(no2)-1;
	while (p-no2>=0)
	{
		char temp=*p;
		*p=(*p-x-48+10)%10+48;
		if (temp-48>=x)
			x=0;
		else
			x=1;
		p--;
	}
	if (*no2=='0')
		printf(no2+1);
	else
		printf(no2);
}

void solve()
{
	int nl=strlen(n);
	int p=n[0]>'1'?1:2;
	int x=getnum(0, p);
	char* no2p=no2;
	for (; p<=nl; p++)
	{
		*no2p++=x/2+'0';
		x=x%2;
		x=10*x+getnum(p, 1);
	}
	p=nl>1?getnum(nl-2, 2):getnum(nl-1, 1);
	subs(m[p%4]);
}

int main()
{	
	scanf("%s", n);
	solve();
	return 0;
}
