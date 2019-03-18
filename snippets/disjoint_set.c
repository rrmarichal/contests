#include <stdio.h>

#define MAX 1000

int n, p[MAX + 1], rank[MAX + 1] = {0};
FILE *ofile;

void input();
void output();
void print_set();

// DISJOINT SET OPs. //
int FIND_SET(int);
void UNION(int, int);
void LINK(int, int);

int main()
{
    input();
    output();
    return 0;
}

// FIND + PATH-COMPRESSION HEURISTIC //
int FIND_SET(int x)
{
    if (p[x] != x)
        p[x] = FIND_SET(p[x]);
    return (p[x]);
}

// UNION + UNION-BY-RANK HEURISTIC //
void UNION(int x, int y)
{
    LINK(FIND_SET(x), FIND_SET(y));
}

void LINK(int x, int y)
{
    if (rank[x] > rank[y])
        p[y] = x;
    else
        p[x] = y;
    if (rank[x] == rank[y])
        rank[y]++;
}

void input()
{
    FILE *f = fopen("disjoint.in", "r");
    ofile = fopen("disjoint.out", "w");
    int i;

    fscanf(f, "%d", &n);
    for (i = 1; i <= n; i++)
        fscanf(f, "%d", &p[i]);
}

void output()
{
    print_set();
}

void print_set()
{
    int i;

    fprintf(ofile, "SET = { ");
    for (i = 1; i <= n; i++)
        fprintf(ofile, "%d ", p[i]);
    fprintf(ofile, "}\n");
}
