#include <fstream>

using namespace std;

int V, E;
int G[100][100];
FILE *fin, *fout;

int main()
{
    int i, j, k, w;
    ifstream fin("floyd.in");
    ofstream fout("floyd.out");
    fin >> V >> E;
    for (i = 1; i <= V; i++)
        for (j = 1; j <= V; j++)
            G[i][j] = 9999;
    
    for (i = 0; i < E; i++)
    {
        fin >> j >> k >> w;
        G[j][k] = G[k][j] = w;
    }

    for (k = 1; k <= V; k++)
        for (i = 1; i <= V; i++)
            for (j = 1; j <= V; j++)
                G[i][j] <?= G[i][k] + G[k][j];

    for (i = 1; i <= V; i++)
    {
        for (j = 1; j <= V; j++)
            fout << G[i][j] << ' ';
        fout << endl;
    }

    return 0;
}
