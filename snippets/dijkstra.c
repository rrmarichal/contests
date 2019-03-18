#include <stdio.h>

int V, E, source;
int G[100][100];
int D[100]; // Best Path costs
char S[100] = {0};
FILE *fin, *fout;

void dijkstra( int );

int main()
{
    int i, u, v, w;
    fin = fopen( "dijkstra.in", "r" );
    fout = fopen( "dijkstra.out", "w" );
    fscanf( fin, "%d %d %d", &V, &E, &source );
    for ( u = 0; u < V; u++ ) for ( v = 0; v < V; v++ ) G[u][v] = 99999;
    for ( i = 0; i < E; i++ ) {
        fscanf( fin, "%d %d %d", &u, &v, &w );
        G[u-1][v-1] = G[v-1][u-1] = w;    
    }
    dijkstra(source-1);
    for ( i = 0; i < V; i++ )
        fprintf( fout, "%d -> %d = %d\n", source, i+1, D[i] );
    return 0;
}

void dijkstra( int v ) {
    int i, j, k;
    for ( i = 0; i < V; i++ )
        D[i] = G[v][i];
    D[v]=0;
    
    for ( i = 0; i < V-1; i++ ) {
        for ( j = 0; j < V; j++ )
            if ( !S[j] ) { k = j; break; }
        for ( j = v+1; j < V; j++ )
            if ( !S[j] && (D[j] < D[k]) ) k = j;
        S[k] = 1;
        for ( j = 0; j < V; j++ )
            if ( !S[j] && (D[k]+G[k][j]<D[j]) )
                D[j] = D[k]+G[k][j];    
    }    
}
