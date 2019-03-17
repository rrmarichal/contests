#include <cstdio>
#include <cstring>

#define MAXL 10000

char seq[MAXL+1], rseq[MAXL+1];
int seql;
int open[MAXL];

char* solve()
{
	for (int j=seql-2; j; j--)
		if (seq[j]=='(' && 2*open[j-1]>j)
		{
			strncpy(rseq, seq, j);
			rseq[j]=')';
			int k;
			for (k=1; k<=seql/2-open[j-1]; k++)
				rseq[j+k]='(';
			for (; j+k<seql; k++) 
				rseq[k+j]=')';
			rseq[seql]=0;
			return rseq;
		}
	return "No solution";
}

int main()
{
	scanf("%s", seq);
	open[0]=1;
	seql=strlen(seq);
	for (int j=1; j<seql; j++)
		open[j]=open[j-1]+(seq[j]=='(');
	printf("%s\n", solve());
	return 0;
}
