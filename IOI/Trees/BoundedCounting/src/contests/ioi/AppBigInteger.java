package contests.ioi;

import java.math.BigInteger;
import java.util.HashMap;

public class AppBigInteger {

    private HashMap<Integer, BigInteger> lookup;

    public BigInteger perfectBalancedTressOfWeight(int N) {
        lookup = new HashMap<Integer, BigInteger>();
        return balancedTrees(N);
    }

    private BigInteger balancedTrees(int N) {
        if (N == 1) {
            return BigInteger.valueOf(1);
        }
        if (!lookup.containsKey(N)) {
            BigInteger result = BigInteger.valueOf(0);
            int root = (int) Math.sqrt(N);
            for (int b = 2; b <= root; b++) {
                result = result.add(balancedTrees(N / b));
            }
            for (int w = 1; w < N / root; w++) {
                int factor = N / w - N / (w + 1);
                result = result.add(balancedTrees(w).multiply(BigInteger.valueOf(factor)));
            }
            lookup.put(N, result);
            return result;
        }
        return lookup.get(N);
    }

}
