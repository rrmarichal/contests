#include <cstdio>

int main()
{
    int x;
    printf("Enter a number: ");
    scanf("%d", &x);
    printf("x's complement: %d\n", ~x);
    printf("Index of least significant bit: %d\n", __builtin_ffs(x) - 1);
    printf("Number of leading zeroes: %d\n", __builtin_clz(x));
    printf("Index of highest set bit: %d\n", 31 - __builtin_clz(x));
    printf("Number of trailing zeroes: %d\n", __builtin_ctz(x));
    printf("Number of 1-bits: %d\n", __builtin_popcount(x));
    printf("Parity: %d\n", __builtin_parity(x));
    printf("File Name: %s\n", __FILE__);
    printf("DATE: %s\n", __DATE__);
    printf("TIME: %s\n", __TIME__);
}
