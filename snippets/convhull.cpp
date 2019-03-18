#include <fstream>
using namespace std;

bool hull[50000] = {false};

class CPoint
{
  public:
    int x, y;
};

inline int cp(CPoint p1, CPoint p2, CPoint p3)
{
    CPoint ab, ac;
    ab.x = p2.x - p1.x;
    ab.y = p2.y - p1.y;
    ac.x = p3.x - p1.x;
    ac.y = p3.y - p1.y;
    return (ab.x * ac.y - ab.y * ac.x);
}

void convex_hull(int, CPoint[]);

int main()
{
    int n;
    CPoint p[50000];
    ifstream fin("convhull.in");
    ofstream fout("convhull.out");
    fin >> n;
    for (int i = 0; i < n; i++)
        fin >> p[i].x >> p[i].y;
    convex_hull(n, p);
    for (int i = 0; i < n; i++)
        if (hull[i])
            fout << i + 1 << ' ';
    return 0;
}

// NO_COLLINEAR_POINTS
void convex_hull(int size, CPoint X[])
{
    int N = size, p = 0, i;

    //First find the leftmost point
    for (i = 0; i < N; i++)
        if (X[i].x < X[p].x)
            p = i;

    int start = p;
    do
    {
        int n = -1;
        for (i = 0; i < N; i++)
        {
            if (i == p)
                continue; //Don't go back to the same point you came from
            if (n == -1)
                n = i; //If there is no N yet, set it to i
            if (cp(X[i], X[n], X[p]) < 0)
                n = i; //As described above, set N=X
        }
        hull[p = n] = true;
    } while (start != p);
}
