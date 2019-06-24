package scripts;

import static java.lang.System.err;
import static java.lang.System.in;
import static java.lang.System.out;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import continuedfractions.ContinuedFraction;
import fractions.Fraction;

/**
 * Read from standard input a list of fraction expressions given in the format
 * <code>numerator/denominator</code> and print to standard output the corresponding
 * continued fraction expression.
 */
public class FractionToContinuedFraction {

    public static void main(String[] args) throws IOException {
        BufferedReader reader = new BufferedReader(new InputStreamReader(in));
        PrintWriter writer = new PrintWriter(out);
        int T = Integer.parseInt(reader.readLine());
        Pattern pattern = Pattern.compile("^(\\d+)/(\\d+)$");
        for (int t = 0; t < T; t++) {
            String fractionStr = reader.readLine();
            Matcher matcher = pattern.matcher(fractionStr);
            if (matcher.matches()) {
                Fraction fraction = new Fraction(
                    Integer.parseInt(matcher.group(1)),
                    Integer.parseInt(matcher.group(2)));
                ContinuedFraction cf = ContinuedFraction.fromFraction(fraction);
                writer.println(String.format("Case #%d: %s", t + 1, cf.toString()));
                writer.flush();
            }
            else {
                err.println(String.format("Test case #%d does not contain a valid pattern", t + 1));
            }
        }
        writer.close();
    }

}
