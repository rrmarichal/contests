package contests.ioi;

import java.util.Scanner;

public class App {

    private int[][] process(String S) {
        int[][] counts = new int[S.length()][26];
        for (int j=0; j < S.length(); j++) {
            for (int k=0; k < 26; k++) {
                counts[j][k] = j > 0 ? counts[j-1][k] : 0;
                if (S.charAt(j)-97 == k) {
                    counts[j][k]++;
                }
            }
        }
        return counts;
    }

    private int charCount(int start, int length, int[][] counts, int chr) {
        int base = start > 0 ? counts[start-1][chr] : 0;
        return counts[start+length-1][chr] - base; 
    }

    private boolean anagram(String S, int index, int length, int[][] counts) {
        for (int j = 0; j < 26; j++) {
            int ccj = charCount(index, length, counts, j);
            int cca = charCount(0, length, counts, j);
            if (ccj != cca) {
                return false;
            }
        }
        return true;
    }

    public String solve(String S) {
        int[][] counts = process(S);
        for (int l = 1; l <= S.length()/2; l++) {
            if (S.length() % l == 0) {
                boolean found = true;
                for (int j = l; j < S.length(); j+=l) {
                    if (!anagram(S, j, l, counts)) {
                        found = false;
                    }
                }
                if (found) {
                    return S.substring(0, l);
                }
            }
        }
        return "-1";
    }

    public static void main( String[] args ) {
        App app = new App();
        Scanner sc = new Scanner(System.in);
        System.out.println(app.solve(sc.nextLine()));
        sc.close();
    }

}
