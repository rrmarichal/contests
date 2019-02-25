package contests.ioi;

public class Functions {

    public static long d(int[] X, int[] Y) {
        return e(X, X) - 2*e(X, Y) + e(Y, Y);
    }

    public static long e(int[] X) {
        long count = 0;
        for (int j = 0; j < X.length-1; j++)
            for (int k = j+1; k < X.length; k++)
                if (X[j] == X[k]) count++;
        return count;
    }

    public static long e(int[] X, int[] Y) {
        long count = 0;
        for (int j = 0; j < X.length; j++)
            for (int k = 0; k < Y.length; k++)
                if (X[j] == Y[k]) count++;
        return count;
    }

    public static long h(int[] X, int[] Y) {
        return g(X, Y, false) + g(Y, X, false);
    }

    public static long g(int[] X, int[] Y, boolean allowEquals) {
        long count = 0;
        for (int j = 0; j < X.length; j++)
            for (int k = 0; k < Y.length; k++)
                if (X[j] > Y[k] || (allowEquals && X[j] == Y[k])) count++;
        return count;
    }

    public static long g_1(int[] X, int[] Y) {
        long count = 0;
        for (int j = 0; j < X.length; j++)
            for (int k = 0; k < Y.length; k++)
                if (X[j] < Y[k]) count++;
        return count;
    }

    public static long f(int[] X) {
        long count = 0;
        for (int j = 0; j < X.length - 1; j++)
            for (int k = j+1; k < X.length; k++)
                if (X[j] > X[k]) count++;
        return count;
    }

    /**
     * "Inverse" funtion of f.
     */
    public static long f_1(int[] X) {
        long count = 0;
        for (int j = 0; j < X.length - 1; j++)
            for (int k = j+1; k < X.length; k++)
                if (X[j] < X[k]) count++;
        return count;
    }

	public static int[] reverse(int[] X) {
        int[] r = new int[X.length];
        for (int j = 0; j < X.length; j++)
            r[j] = X[X.length - j - 1];
        return r;
	}

}
