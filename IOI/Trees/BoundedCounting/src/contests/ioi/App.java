package contests.ioi;

import java.util.HashMap;
import java.util.Scanner;

public class App {

    private HashMap<Integer, Long> lookup;

    public long perfectBalancedTressOfWeight(int N) {
        lookup = new HashMap<Integer, Long>();
        return balancedTrees(N);
    }

    private long balancedTrees(int N) {
        if (N == 1) {
            return 1L;
        }
        if (!lookup.containsKey(N)) {
            long result = 0L;
            int root = (int) Math.sqrt(N);
            for (int b = 2; b <= root; b++) {
                result = result + balancedTrees(N / b);
            }
            for (int w = 1; w < N / root; w++) {
                int factor = N / w - N / (w + 1);
                result += balancedTrees(w) * factor;
            }
            lookup.put(N, result);
            return result;
        }
        return lookup.get(N);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        App app = new App();
        System.out.println(app.perfectBalancedTressOfWeight(sc.nextInt()));
        sc.close();
    }

}
