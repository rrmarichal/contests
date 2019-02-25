package contests.ioi;

import java.util.Scanner;

public class Cows {

    private int codeOf(char c) {
        if (c >= 'A' && c <= 'Z') return c - 65;
        if (c >= 'a' && c <= 'z') return 26 + c - 97;
        return -1;
    }

    public int count(int U, int L, int P, String[] words) {
        int[][][] T = new int[U + 1][L + 1][52];
        for (int j = 0; j < P; j++) {
            int M = 0, m = 0;
            if (words[j].charAt(0) < 'a') M++; else m++;
            if (words[j].charAt(1) < 'a') M++; else m++;
            int code = codeOf(words[j].charAt(1));
            T[M][m][code]++;
        }
        for (int l = 3; l <= U + L; l++) {
            for (int M = 0; M <= U; M++) {
                int m = l - M;
                if (m >= 0 && m <= L) {
                    for (int code = 0; code < 52; code++) {
                        for (int j = 0; j < P; j++) {
                            int wc1 = codeOf(words[j].charAt(1));
                            if (wc1 == code) {
                                int wc0 = codeOf(words[j].charAt(0));
                                if (m > 0 && wc1 >= 26) {
                                    T[M][m][code] = (T[M][m][code] + T[M][m - 1][wc0]) % 97654321;
                                } else if (M > 0) {
                                    T[M][m][code] = (T[M][m][code] + T[M - 1][m][wc0]) % 97654321;
                                }
                            }
                        }
                    }
                }
            }
        }
        int result = 0;
        for (int j = 0; j < 52; j++) {
            result = (result + T[U][L][j]) % 97654321;
        }
        return result;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        Cows app = new Cows();
        int U = sc.nextInt(), L = sc.nextInt(), P = sc.nextInt();
        String[] words = new String[P];
        sc.nextLine();
        for (int j = 0; j < P; j++) {
            words[j] = sc.nextLine();
        }
        System.out.println(app.count(U, L, P, words));
        sc.close();
    }

}
