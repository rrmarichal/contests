#include <stdio.h>

typedef struct {
	int u, v, w;
} edge;

int V, E;
edge edges[100];
int disjoint[100];

int FIND(int x) {
	if (disjoint[x] != x)
		disjoint[x] = FIND(disjoint[x]);
	return disjoint[x];
}

void UNION(int x, int y) {
	disjoint[FIND(x)] = FIND(y);
}

void swape(edge* a, edge* b) {
	edge tmp = *a;
	*a = *b;
	*b = tmp;
}

void sort(int l, int r) {
	int i = l, j = r, med = edges[(l + r) / 2].w, tmp;
	do {
		while (edges[i].w < med) i++;
		while (med < edges[j].w) j--;
		if (i <= j) {
			swape(&edges[i], &edges[j]);
			i++;
			j--;
		}
	} while (i <= j);
	if (l < j) sort(l, j);
	if (i < r) sort(i, r);
}

int main() {
	int i, j, k;
	fscanf(stdin, "%d %d", &V, &E);
	for (i = 0; i < E; i++)
		fscanf(stdin, "%d %d %d", &edges[i].u, &edges[i].v, &edges[i].w);
	sort(0, E - 1);
	for (i = 1; i <= V; i++)
		disjoint[i] = i;

	i = V - 1;
	j = k = 0;
	while (i) {
		if (FIND(edges[j].u) != FIND(edges[j].v)) {
			fprintf(stderr, "u=%d, v=%d S(u)=%d, S(v)=%d\n",
				edges[j].u, edges[j].v,
				FIND(edges[j].u), FIND(edges[j].v)
			);

			UNION(edges[j].u, edges[j].v);
			fprintf(stdout, "(%d,%d)\n", edges[j].u, edges[j].v);
			k += edges[j].w;
			i--;
		}
		j++;
	}

	fprintf(stdout, "MST COST: %d", k);
	return 0;
}
