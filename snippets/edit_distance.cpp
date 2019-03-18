/*
TASK: Edit distance
LANG: C++
*/

#include <cstdio>
#include <string.h>

#define MAXL 100
#define DELETE 'D'
#define INSERT 'I'
#define CHANGE 'C'

char a[MAXL + 2], b[MAXL + 2];
unsigned short t[MAXL + 1][MAXL + 1], la, lb;
char how[MAXL + 1][MAXL + 1];

void reconstruct(int i, int j)
{
    if (!(i || j))
        return;
    if (how[i][j] == CHANGE)
    {
        reconstruct(i - 1, j - 1);
        if (a[i - 1] != b[j - 1])
            printf("C%c%d\n", a[i - 1], i);
    }
    else if (how[i][j] == DELETE)
    {
        reconstruct(i - 1, j);
        printf("D%c%d\n", a[i - 1], i);
    }
    else if (how[i][j] == INSERT)
    {
        reconstruct(i, j - 1);
        printf("I%c%d\n", b[j - 1], i);
    }
}
int main()
{
    freopen("edit.in", "r", stdin);
    freopen("edit.out", "w", stdout);
    scanf("%s\n%s", a, b);
    la = strlen(a);
    lb = strlen(b);
    for (int j = 0; j <= la; j++)
        t[j][0] = j, how[j][0] = DELETE;
    for (int j = 1; j <= lb; j++)
        t[0][j] = j, how[0][j] = INSERT;
    for (int j = 1; j <= la; j++)
        for (int k = 1; k <= lb; k++)
        {
            int plus = (a[j - 1] != b[k - 1]);
            if (t[j - 1][k - 1] + plus < t[j - 1][k] + 1 && t[j - 1][k - 1] + plus < t[j][k - 1] + 1)
            {
                t[j][k] = t[j - 1][k - 1] + plus;
                how[j][k] = CHANGE;
            }
            else if (t[j - 1][k] + 1 < t[j][k - 1] + 1)
            {
                t[j][k] = t[j - 1][k] + 1;
                how[j][k] = DELETE;
            }
            else
            {
                t[j][k] = t[j][k - 1] + 1;
                how[j][k] = INSERT;
            }
        }
    printf("%d\n", t[la][lb]);
    reconstruct(la, lb);
    fclose(stdin);
    fclose(stdout);
    return 0;
}
