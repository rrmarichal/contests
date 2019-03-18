#include <fstream>
using namespace std;

int V, E;
bool G[5000][5000] = {false};

int now, root_sons;
int val[5000] = {0};
bool points[5000] = {false};
ofstream fout( "art.out" );

int art( int );

int main()
{   
    int i, j, k;
    ifstream fin( "art.in" );
    
    fin >> V >> E;
    for ( i = 0; i < E; i++ ) {
        fin >> j >> k;  
        G[j-1][k-1] = G[k-1][j-1] = true;  
    }
    
    now = root_sons = 0;
    art(0);
    points[0] = (root_sons > 1 ? true : false );
    for ( i = 0; i < V; i++ )
        if ( points[i] ) fout << i+1 << ' '; 
       
    return 0;
}

int art( int k ) {
    int m, min;
    val[k] = min = ++now;    
    for ( int i = 0; i < V; i++ )
        if ( G[k][i] )
            if ( !val[i] ) {
                m = art(i); 
                min <?= m;  
                if ( m >= val[k] ) points[k] = true;  
            } else
                min <?= val[i];  
    return (min);
}
