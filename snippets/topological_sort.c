#include <stdio.h>

int V, E, k = 0;
int sorted[100];
char G[100][100] = {0};
char mark[100] = {0};
FILE *fin, *fout;

void topsort( int v ) {
    int i;  
    mark[v] = 1;
    for ( i = 1; i <= V; i++ )
        if ( !mark[i] && G[v][i] )
            topsort(i);
    sorted[k++] = v;
}

int main()
{
    int i, u, v;
    fin = fopen( "topsort.in", "r" );
    fout = fopen( "topsort.out", "w" );
    fscanf( fin, "%d %d", &V, &E );
    for ( i = 0; i < E; i++ ) {
        fscanf( fin, "%d %d", &u, &v );
        G[u][v] = 1;    
    }
    for ( i = 1; i <= V; i++ )
        if ( !mark[i] )
            topsort(i);
    for ( i = V-1; i >= 0; i-- )
        fprintf( fout, "%d ", sorted[i] );
    return 0;
}
