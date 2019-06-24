package scripts;

import static java.lang.System.err;
import static java.lang.System.in;
import static java.lang.System.out;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.StringTokenizer;

import continuedfractions.ContinuedFraction;
import fractions.Fraction;

/**
 * Read from standard input a list of continued fraction expressions given in the format
 * <code>a0 a1 ... an</code> and print to standard output the corresponding fraction.
 */
public class ContinuedFractionToFraction {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(in));
        PrintWriter writer = new PrintWriter(out);
        int T = Integer.parseInt(reader.readLine());
        for (int t = 0; t < T; t++) {
            String expression = reader.readLine();
            StringTokenizer tokenizer = new StringTokenizer(expression);
            if (tokenizer.countTokens() > 0) {
                List<Long> terms = new ArrayList<Long>();
                while (tokenizer.hasMoreElements()) {
                    terms.add(Long.parseLong(tokenizer.nextToken()));
                }
                ContinuedFraction cf = ContinuedFraction.fromTerms(terms);
                Fraction fraction = cf.getFraction();
                writer.println(String.format("Case #%d: %s", t + 1, fraction.toString()));
                writer.flush();
            }
            else {
                err.println(String.format("Test case #%d does not contain a valid pattern", t + 1));
            }
        }
        writer.close();
    }

}
