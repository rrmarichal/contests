#include <cstdio>

int a, b, apb[201], bpa[201], rez[201], napb, nbpa;

void print(int* A, int n)
{
	while (!A[n-1])
		n--;
	for (int j=n-1; j>=0; j--)
		printf("%d", A[j]);
	printf("\n");
}

int max(int a, int b)
{
	return a>b?a:b;
}

void ass(int* A, int* c, int n)
{
	while (n)
	{
		A[*c]=n%10;
		n/=10;
		(*c)++;
	}
}

// Raise a to the power of b.
void pow(int* A, int* c, int a, int b) 
{
	ass(A, c, a);
	for (int j=0; j<b-1; j++)
	{
		int rez=0, k;
		for (int k=0; k<*c; k++)
		{
			int q=A[k];
			A[k]=(rez+A[k]*a)%10;
			rez=(rez+q*a)/10;
		}
		if (rez>0)
			ass(A, c, rez);
	}
}


void substract(int* A, int na, int* B, int nb)
{
	for (int j=0; j<max(na, nb); j++)
	{
		if (A[j]<B[j])
			A[j+1]--;
		rez[j]=(A[j]-B[j]+10)%10;
	}
}

int compare(int* A, int na, int* B, int nb)
{
	if (na>nb)
		return 1;
	else
	if (nb>na)
		return -1;
	else
	{
		for (int j=na-1; j>=0; j--)
			if (A[j]>B[j])
				return 1;
			else
			if (B[j]>A[j])
				return -1;
	}
	return 0;
}

void solve()
{
	pow(apb, &napb, a, b);
	pow(bpa, &nbpa, b, a);
	int q=compare(apb, napb, bpa, nbpa);
	if (q==+1)
	{
		substract(apb, napb, bpa, nbpa);
		print(rez, max(napb, nbpa));
	}
	else
	if (q==-1)
	{
		substract(bpa, nbpa, apb, napb);
		printf("-");
		print(rez, max(napb, nbpa));
	}
	else
		printf("0\n");
}

int main()
{
	scanf("%d%d", &a, &b);
	solve();
	return 0;
}
