#include <cstdio>
#include <deque>

using namespace std;

int n, b, k, q, c;
deque<int> B;

int F(int q) //return q^b mod k
{
	int r=1;
	for (int j=0; j<B.size(); j++)
	{
		r=(r*r)%k;
		if (B[j]==1)
			r=(r*q)%k;
	}
	return r;
}

int main()
{
	scanf("%d%d%d", &n, &b, &k);
	while (b)
	{
		B.push_front(b%2);
		b/=2;
	}
	for (; n; n--)
	{
		scanf("%d", &q);
		if (F(q)==0)
			c++;
	}
	printf("%d\n", c);
	scanf("\n");
	return 0;
}

