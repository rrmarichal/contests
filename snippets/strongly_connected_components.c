#include <stdio.h>

int V, E, k;
int sorted[100];
int spanning_tree[100] = {0};
char G[100][100] = {0}, Gt[100][100] = {0};
char mark[100] = {0};
FILE *fin, *fout;

void DFS( int v ) {
    int i;  
    mark[v] = 1;
    for ( i = 1; i <= V; i++ )
        if ( !mark[i] && G[v][i] )
            DFS(i);
    sorted[k--] = v;
}

void DFS_t( int v ) {
    int i;  
    mark[v] = 1;
    for ( i = 1; i <= V; i++ )
        if ( !mark[i] && Gt[v][i] ) {
            DFS_t(i);
            spanning_tree[i] = v;
        }
}

int main()
{
    int i, u, v;
    fin = fopen( "strongc.in", "r" );
    fout = fopen( "strongc.out", "w" );
    fscanf( fin, "%d %d", &V, &E );
    for ( i = 0; i < E; i++ ) {
        fscanf( fin, "%d %d", &u, &v );
        G[u][v] = 1; 
        Gt[v][u] = 1;   
    }
    k = V-1;
    for ( i = 1; i <= V; i++ )
        if ( !mark[i] )
            DFS(i);
    for ( i = 0; i <= V; i++ )
        mark[i] = 0;
    for ( i = 0; i < V; i++ )
        if ( !mark[sorted[i]] )
            DFS_t(sorted[i]);
            
    for ( i = 1; i <= V; i++ )
        fprintf( fout, "%d ", spanning_tree[i] );    
    return 0;
}
