package contests.withgoogle;

import java.util.ArrayList;
import java.util.Scanner;

public class App {

    public long solve(int N, ArrayList<String> prefixes) {
        long excluded = 0;
        for (int j = 0; j < prefixes.size(); j++) {
            excluded += 1L << N-prefixes.get(j).length();
        }
        return (1L<<N) - excluded;
    }

    public ArrayList<String> removePrefixed(String[] prefixes) {
        ArrayList<String> unique = new ArrayList<String>();
        for (int j = 0; j < prefixes.length; j++) {
            boolean hasPrefix = false;
            for (int k = 0; k < prefixes.length; k++) {
                if (j != k
                    && prefixes[j].length() >= prefixes[k].length()
                    && prefixes[j].substring(0, prefixes[k].length()).equals(prefixes[k])) {
                    hasPrefix = true;
                    break;
                }
            }
            if (!hasPrefix) {
                unique.add(prefixes[j]);
            }
        }
        return unique;
    }

    public static void main( String[] args ) {
        App app = new App();
        Scanner sc = new Scanner(System.in);
        int T = sc.nextInt();
        for (int j = 0; j < T; j++) {
            int N = sc.nextInt(), P = sc.nextInt();
            sc.nextLine();
            String[] prefixes = new String[P];
            while (P-- > 0) {
                prefixes[P] = sc.nextLine();
            }
            ArrayList<String> unique = app.removePrefixed(prefixes);
            System.out.println(String.format("Case #%d: %d", j+1, app.solve(N, unique)));
        }
        sc.close();
    }

}
