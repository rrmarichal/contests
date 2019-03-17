/*
TASK: 374
LANG: Java
*/

import java.math.BigInteger;
import java.util.Scanner;

public class Solution
{
	static long fact(int n)
	{
		if (n == 0)
			return 1;
		else
			return n * fact(n - 1);
	}
	
	static BigInteger Comb(int n, int k)
	{
		long l = fact(n) / (fact(k) * fact(n - k));
		return new BigInteger(Long.toString(l));
	}
	
	public static void main(String[] args)
	{
		Scanner scanner = new Scanner(System.in);
		int a = scanner.nextInt();
		int b = scanner.nextInt();
		int n = scanner.nextInt();
		BigInteger res = BigInteger.ZERO;
		BigInteger aBI = new BigInteger(Integer.toString(a));
		BigInteger bBI = new BigInteger(Integer.toString(b));
		for (int j = 0; j <= n; j++)
		{
			BigInteger ap = aBI.pow(n - j);
			BigInteger bp = bBI.pow(j);
			BigInteger prod = ap.multiply(bp).multiply(Comb(n, j));
			res = res.add(prod);
		}
		System.out.println(res);
	}
}
