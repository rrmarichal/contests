#include <stdio.h>

int N, max = 0;
int A[100000];
int B[100000];
int C[100000];

void input();
void counting_sort();
void output();

int main()
{
    input();
    counting_sort();
    output();
    return 0;
}

void input()
{
    FILE *f = fopen("count.in", "r");
    int i;

    fscanf(f, "%d", &N);
    for (i = 0; i < N; i++)
    {
        fscanf(f, "%d ", &A[i]);
        max >?= A[i];
    }

    fclose(f);
}

void counting_sort()
{
    // A[1..n] source array
    // B[1..n] sorted array
    // C[1..k] aux. array
    int i;

    // Initialize
    for (i = 0; i <= max; i++)
        C[i] = 0; 
    // Count
    for (i = 0; i < N; i++)
        C[A[i]]++;
    // Accumulate
    for (i = 1; i <= max; i++)
        C[i] += C[i - 1];
    // Sort
    for (i = 0; i < N; i++)
    {
        B[C[A[i]] - 1] = A[i];
        C[A[i]] = C[A[i]] - 1;
    }
}

void output()
{
    FILE *f = fopen("count.out", "w");
    int i;

    for (i = 0; i < N; i++)
        fprintf(f, "%d ", B[i]);

    fclose(f);
}
