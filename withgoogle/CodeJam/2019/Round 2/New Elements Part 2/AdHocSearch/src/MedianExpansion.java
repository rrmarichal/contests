import static java.lang.System.in;
import static java.lang.System.out;

import java.io.*;
import java.util.*;

/**
 * Tests on fractions median expansion.
 */
public class MedianExpansion {

    final static int ITERATIONS = 20;

    private static long gcd(long a, long b) {
        if (b == 0) {
            return a;
        }
        return gcd(b, a % b);
    }

    private static Fraction median(Fraction l, Fraction u) {
        Fraction result = new Fraction(l.n + u.n, l.d + u.d);
        long gcd = gcd(result.n, result.d);
        return new Fraction(result.n/gcd, result.d/gcd);
    }

    @SuppressWarnings("unused")
    private static void printList(List<Fraction> list) {
        for (Fraction f: list) {
            out.print(String.format("%d/%d ", f.n, f.d));
        }
        out.println();
    }
    
    /**
     * Find two neighboring fractions with least denominator median.
     */
    private static Fraction successiveMinMedians(Fraction l, Fraction u) {
        int iterations = 0;
        LinkedList<Fraction> list = new LinkedList<Fraction>();
        list.add(l);
        list.add(u);
        Fraction best = new Fraction(0, Long.MAX_VALUE);
        while (iterations++ < ITERATIONS) {
            int index = 1, minIndex = 0;
            Fraction stepBest = new Fraction(0, Long.MAX_VALUE);
            Iterator<Fraction> it = list.iterator();
            Fraction curr = it.next();
            while (it.hasNext()) {
                Fraction next = it.next();
                Fraction median = median(curr, next);
                if (median.d < stepBest.d) {
                    stepBest = median;
                    minIndex = index;
                }
                index++;
                curr = next;
            }
            if (stepBest.d < best.d) {
                best = stepBest;
            }
            list.add(minIndex, best);
            // printList(list);
        }
        return best;
    }

    /**
     * Pick a random pair of fractions and add the median.
     */
    private static Fraction randomizedMinMedians(Fraction l, Fraction u) {
        int iterations = 0;
        Random random = new Random();
        List<Fraction> list = new ArrayList<Fraction>();
        list.add(l);
        list.add(u);
        Fraction best = new Fraction(0, Long.MAX_VALUE);
        while (iterations++ < ITERATIONS) {
            int lidx = random.nextInt(list.size() - 1);
            int ridx = lidx + random.nextInt(list.size() - lidx);
            Fraction median = median(list.get(lidx), list.get(ridx));
            list.add(median);
            if (median.d < best.d) {
                best = median;
            }
            // printList(list);
        }
        return best;
    }

    /**
     * At each step expand neighbor fractions to their median.
     */
    private static Fraction fareyExpansion(Fraction l, Fraction u) {
        int iterations = 0;
        List<Fraction> curr = new ArrayList<Fraction>(2);
        curr.add(l);
        curr.add(u);
        Fraction best = new Fraction(0, Long.MAX_VALUE);
        while (iterations++ < ITERATIONS) {
            List<Fraction> next = new ArrayList<Fraction>(curr.size() * 2 - 1);
            for (int j = 1; j < curr.size(); j++) {
                next.add(curr.get(j - 1));
                Fraction median = median(curr.get(j - 1), curr.get(j));
                next.add(median);
                if (median.d < best.d) {
                    best = median;
                }
            }
            next.add(curr.get(curr.size() - 1));
            curr = next;
            // printList(curr);
        }
        return best;
    }

    /**
     * Expand the first two fractions.
     */
    private static Fraction leftExpansion(Fraction l, Fraction u) {
        int iterations = 0;
        List<Fraction> curr = new ArrayList<Fraction>();
        curr.add(l);
        curr.add(u);
        Fraction best = new Fraction(0, Long.MAX_VALUE);
        while (iterations++ < ITERATIONS) {
            Fraction median = median(l, curr.get(curr.size() - 1));
            curr.add(median);
            if (median.d < best.d) {
                best = median;
            }
            // printList(curr);
        }
        return best;
    }

    /**
     * Expand the last two fractions.
     */
    private static Fraction rightExpansion(Fraction l, Fraction u) {
        int iterations = 0;
        List<Fraction> curr = new ArrayList<Fraction>();
        curr.add(l);
        curr.add(u);
        Fraction best = new Fraction(0, Long.MAX_VALUE);
        while (iterations++ < ITERATIONS) {
            Fraction median = median(u, curr.get(curr.size() - 1));
            curr.add(median);
            if (median.d < best.d) {
                best = median;
            }
            // printList(curr);
        }
        return best;
    }

    /**
     * For two random numbers x and y, expand to the "weighted" median, that is weighted_median = xl + yu.
     */
    private static Fraction randomizedExpansion(Fraction l, Fraction u) {
        int BOUND = (int) Math.sqrt(ITERATIONS);
        Random random = new Random();
        int iterations = 0;
        List<Fraction> curr = new ArrayList<Fraction>(ITERATIONS + 2);
        curr.add(l);
        curr.add(u);
        Fraction best = new Fraction(0, Long.MAX_VALUE);
        while (iterations++ < ITERATIONS) {
            long x = 1 + random.nextInt(BOUND), y = 1 + random.nextInt(BOUND);
            Fraction f = new Fraction(x * l.n, x * l.d);
            Fraction g = new Fraction(y * u.n, y * u.d);
            Fraction median = median(f, g);
            curr.add(median);
            if (median.d < best.d) {
                best = median;
            }
            // printList(curr);
        }
        return best;
    }

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(in));
        String[] line = reader.readLine().split(" ");
        long n = Long.parseLong(line[0]);
        long d = Long.parseLong(line[1]);
        long np = Long.parseLong(line[2]);
        long dp = Long.parseLong(line[3]);

        Fraction sminBest = successiveMinMedians(new Fraction(n, d), new Fraction(np, dp));
        Fraction rndMinBest = randomizedMinMedians(new Fraction(n, d), new Fraction(np, dp));
        Fraction fareyBest = fareyExpansion(new Fraction(n, d), new Fraction(np, dp));
        Fraction lbest = leftExpansion(new Fraction(n, d), new Fraction(np, dp));
        Fraction rbest = rightExpansion(new Fraction(n, d), new Fraction(np, dp));
        Fraction rndExpansionBest = randomizedExpansion(new Fraction(n, d), new Fraction(np, dp));

        out.println(String.format("SuccessiveMinMedians %s", sminBest.toString()));
        out.println(String.format("RandomizedMinMedians: %s", rndMinBest.toString()));
        // Expands exponentially on each iteration,
        // ITERATIONS should be a small number (<22).
        out.println(String.format("FareyExpansion: %s", fareyBest.toString()));
        out.println(String.format("LeftExpansion: %s", lbest.toString()));
        out.println(String.format("RightExpansion: %s", rbest.toString()));
        out.println(String.format("RandomizedExpansion: %s", rndExpansionBest.toString()));

        Fraction bestApprox = sminBest;
        if (rndMinBest.d < bestApprox.d) {
            bestApprox = rndMinBest;
        }
        if (fareyBest.d < bestApprox.d) {
            bestApprox = fareyBest;
        }
        if (lbest.d < bestApprox.d) {
            bestApprox = lbest;
        }
        if (rbest.d < bestApprox.d) {
            bestApprox = rbest;
        }
        if (rndExpansionBest.d < bestApprox.d) {
            bestApprox = rndExpansionBest;
        }

        out.println(String.format("\nBestExpansion: %s", bestApprox.toString()));
    }

}
