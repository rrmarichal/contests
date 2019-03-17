#include <cstdio>
#include <algorithm>

using namespace std;

int n, k, t[8000];

int main()
{
	scanf("%d%d", &k, &n);
	for (int j=0; j<n; j++)
		scanf("%d", t+j);
	sort(t, t+n);
	int c=1, ant=t[0], r=1;
	for (int j=1; j<n; j++)
	{
		if (c==k || t[j]/1000!=ant/1000)
		{
			r++;
			c=1;
			ant=t[j];
		}
		else
		{
			c++;
			ant=t[j];
		}
	}
	printf("%d\n", r+2);
	return 0;
}
