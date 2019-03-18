#include <stdio.h>

typedef struct { //(u,v)=w & (u,v) c E
    int u;
    int v;
    int w;    
} edge; 

int V, E;
edge edges[100];
int disjoint[100];
FILE *fin, *fout;

void sort( int, int );
int FIND( int );
int UNION( int, int );

int main()
{
    int i, j, k;
    fin = fopen( "mst.in", "r" );
    fout = fopen( "mst.out", "w" );
    fscanf( fin, "%d %d", &V, &E );
    for ( i = 0; i < E; i++ )
        fscanf( fin, "%d %d %d", &edges[i].u, &edges[i].v, &edges[i].w );
    sort(0,E-1);
    for ( i = 1; i <= V; i++ )
        disjoint[i] = i;
        
    i = V-1; 
    j = k = 0;
    while (i) {
        if ( FIND(edges[j].u) != FIND(edges[j].v)) {
            UNION(edges[j].u,edges[j].v);
            fprintf( fout, "(%d,%d)\n", edges[j].u, edges[j].v );
            k += edges[j].w;  
            i--;  
        }
        j++;        
    } 
    
    fprintf( fout, "MST COST: %d", k );    
     
    return 0;
}

// + path compression
int FIND( int x ) {
    if ( disjoint[x] != x )
        disjoint[x] = FIND(disjoint[x]);
    return (disjoint[x]);   
}

int UNION( int x, int y ) {
    disjoint[x] = FIND(y);        
}

void swap( int *a, int *b ) { int c = *a; *a = *b; *b = c; }

void sort( int l, int r ) {
    int i = l, j = r, med = edges[(l+r)/2].w, tmp;
    
    do {
        while ( edges[i].w < med ) i++; 
        while ( med < edges[j].w ) j--;  
        if ( i <= j ) {
            swap(&edges[i].u,&edges[j].u);
            swap(&edges[i].v,&edges[j].v);
            swap(&edges[i].w,&edges[j].w);
            i++; j--;        
        } 
    } while ( i <= j );
    if (l<j) sort(l,j);
    if (i<r) sort(i,r);
}
