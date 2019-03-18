#include <fstream>

using namespace std;

int V, E;
char G[100][100] = {0};
FILE *fin, *fout;

int main()
{
    int i, j, k;
    ifstream fin( "warshall.in" );
    ofstream fout( "warshall.out" );
    fin >> V >> E;
    for ( i = 0; i < E; i++ ) {
        fin >> j >> k;
        G[j][k] = 1;
    }
    
    for ( k = 1; k <= V; k++ )
        for ( i = 1; i <= V; i++ )
            for ( j = 1; j <= V; j++ )
                G[i][j] |= G[i][k]&&G[k][j];
       
    for ( i = 1; i <= V; i++ ) {
        for ( j = 1; j <= V; j++ )
            fout << (int)G[i][j] << ' ';
        fout << endl;
    }
     
    return 0;
}
