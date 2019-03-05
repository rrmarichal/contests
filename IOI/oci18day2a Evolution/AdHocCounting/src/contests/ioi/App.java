package contests.ioi;

import java.util.HashSet;
import java.util.Scanner;

public class App {

    private static final long _3p = (long) 1e15;
    private static final long _2p = (long) 1e10;
    private static final long _1p = (long) 1e5;

    private long getCode(int[] c) {
        return c[0] + _1p * c[1] + _2p * c[2] + _3p * c[3];
    }

    private void update(int[] c, char a, int delta) {
        if (a == 'A') c[0]+=delta;
        else
        if (a == 'C') c[1]+=delta;
        else
        if (a == 'T') c[2]+=delta;
        else
        if (a == 'G') c[3]+=delta;
    }

    public long count(String S, int K) {
        int[] c = new int[4];
        HashSet<Long> occurrences = new HashSet<Long>();
        for (int j = 0; j < S.length(); j++) {
            update(c, S.charAt(j), 1);
            if (j >= K-1) {
                occurrences.add(getCode(c));
                update(c, S.charAt(j-K+1), -1);
            }
        }
        return occurrences.size();
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        App app = new App();
        sc.nextInt();
        int K = sc.nextInt();
        sc.nextLine();
        System.out.println(app.count(sc.nextLine(), K));
        sc.close();
    }

}
