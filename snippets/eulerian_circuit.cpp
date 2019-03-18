#include <fstream>
using namespace std;

int V, E;
int G[101][101];
int size[1000];

int circuitpos=0;
int circuit[1000];

void find_circuit( int x ) {
    int i=0,j,k;
    if ( !size[x] )
        circuit[circuitpos++] = x;
    else while ( size[x] ) {
        j = G[x][--size[x]];
        for ( i = 0; i < size[j]; i++ )
            if ( G[j][i] == x ) break;
        G[j][i] = G[j][--size[j]];
        find_circuit(j);
        circuit[circuitpos++] = j;
    }
}

int main()
{
    int i, u, v;
    ifstream fin( "euler.in" );
    ofstream fout( "euler.out" );
    
    fin >> V >> E;
    for ( i = 0; i < E; i++ ) {
        fin >> u >> v;
        G[u][size[u]++]=v; 
        G[v][size[v]++]=u; 
    }
    find_circuit(1);
    for ( i = 1; i < circuitpos; i++ )
        fout << circuit[i] << endl;
    return 0;
}
