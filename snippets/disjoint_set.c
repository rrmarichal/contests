#include <stdio.h>

#define MAX 1000

int n, p[MAX + 1], rank[MAX + 1] = {0};

int FIND_SET(int x) {
    if (p[x] != x)
        p[x] = FIND_SET(p[x]);
    return (p[x]);
}

void LINK(int x, int y) {
    if (rank[x] > rank[y])
        p[y] = x;
    else
        p[x] = y;
    if (rank[x] == rank[y])
        rank[y]++;
}

void UNION(int x, int y) {
    LINK(FIND_SET(x), FIND_SET(y));
}

int main() {
    fscanf(stdin, "%d", &n);
    for (int i = 1; i <= n; i++)
        fscanf(stdin, "%d", &p[i]);

	// Add set operations here.
	
    fprintf(stdout, "{ ");
    for (int i = 1; i <= n; i++)
        fprintf(stdout, "%d ", p[i]);
    fprintf(stdout, "}\n");

    return 0;
}
