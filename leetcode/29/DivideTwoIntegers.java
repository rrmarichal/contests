public class DivideTwoIntegers {

	public static void main(String[] args) {
		DivideTwoIntegers solution = new DivideTwoIntegers();
		int r = solution.divide(-2147483648, 1);
		System.out.println(r);
	}

	public int divide(int dividend, int divisor) {
        if (divisor == 0) return Integer.MAX_VALUE;
        
        long dv = (long) Math.signum(dividend) * dividend;
        long ds = (long) Math.signum(divisor) * divisor;
        
        long result = 0; long dso = ds;
        
        while (dv >= ds) {
            long p = 1;
            while (ds + ds <= dv) {
                ds = ds + ds;
                p = p + p;
            }
            result += p;
            dv = dv - ds;
            ds = dso;
        }
        
        result = Math.signum(dividend) != Math.signum(divisor) ? -result : result;
        if (result < Integer.MIN_VALUE || result > Integer.MAX_VALUE)
        	return Integer.MAX_VALUE;
        return (int) result;
    }
	
}
