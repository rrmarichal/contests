#include <ctime>
#include <cstdio>

#define BUFFSIZE 65536*8

int n;
char buff[BUFFSIZE];
char o[10001];

int min(int a, int b) { return a<b?a:b; }
int main()
{
	setbuf(stdout, buff);
	scanf("%d", &n);
	if (n<9) printf("0\n"); else
	if (n==9) printf("8\n"); else
	{
		printf("72");
		for (int j=0; j<min(10000, n-10); j++)
			o[j]='0';
		for (int j=0; j<(n-10)/10000; j++)
			printf("%s", o);
		printf("%s", o+min(10000, n-10)-(n-10)%10000);
		printf("\n");
	}
	fclose(stdout);
	return 0;
}
