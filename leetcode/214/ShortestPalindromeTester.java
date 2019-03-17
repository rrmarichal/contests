import static java.lang.System.out;

import java.util.Random;

import org.apache.commons.lang3.time.StopWatch;

public class ShortestPalindromeTester {
	
	private final static int TESTS = 10, ALPHABET_SIZE = 2, INPUT_SIZE = 100000, START_CHARACTER = 0, MIDDLE = 50;
	private static Random rand = new Random(System.currentTimeMillis());
	
	public static void main(String[] args) {
		ShortestPalindromeNaive.Solution spnv = new ShortestPalindromeNaive.Solution();
		ShortestPalindromeHashBased.Solution sphb = new ShortestPalindromeHashBased.Solution();
		ShortestPalindromeKMP.Solution spkmp = new ShortestPalindromeKMP.Solution();
		
		int hbpass = 0, kmppass = 0, hbmisses = 0;
		long naivetime = 0, hbtime = 0, kmptime = 0;
		for (int j = 0; j < TESTS; j++) {
			String input = generateRandomString();
			//String input = generateRandomStringForNaiveAlgorithm();
			//out.println(String.format("Input: %s", input));
			
			StopWatch sw = new StopWatch();
			sw.start();
			String nvsp = spnv.shortestPalindrome(input);
			naivetime += sw.getTime();
			
			//out.println(String.format("NAIVE: %s", nvsp));
			
			sw = new StopWatch();
			sw.start();
			String hbsp = sphb.shortestPalindrome(input);
			hbtime += sw.getTime();
			hbmisses += sphb.misses;
			
			//out.println(String.format("HASHB: %s", hbsp));
			
			sw = new StopWatch();
			sw.start();
			String kmpsp = spkmp.shortestPalindrome(input);
			kmptime += sw.getTime();
			hbmisses += sphb.misses;
			
			//out.println(String.format("KNUMP: %s", kmpsp));
			
			if (nvsp.equals(hbsp))
				hbpass++;
			if (nvsp.equals(kmpsp))
				kmppass++;
			
			//out.println(String.format("Input length: %d. Characters Inserted: %d.", input.length(), nvsp.length() - input.length()));
		}
		out.println(String.format("HSB: %d tests out of %d passed.", hbpass, TESTS));
		out.println(String.format("KMP: %d tests out of %d passed.", kmppass, TESTS));
		out.println(String.format("NAIVE Time Overall: %d ms.", naivetime));
		out.println(String.format("HASHB Time Overall: %d ms. Missed %d times.", hbtime, hbmisses));
		out.println(String.format("KNUMP Time Overall: %d ms.", kmptime));
	}

	private static String generateRandomString() {
		StringBuilder sb = new StringBuilder(INPUT_SIZE);
		for (int j = 0; j < INPUT_SIZE; j++)
			sb.append((char) (Math.abs(rand.nextInt()) % ALPHABET_SIZE + START_CHARACTER));
		return sb.toString();
	}
	
	/**
	 * Generates the string a^nxb^n with a random (non palindromic) pattern.
	 * Naive algorithm should fail (n^2 runtime) with this input.
	 * */
	private static String generateRandomStringForNaiveAlgorithm() {
		StringBuilder sb = new StringBuilder(INPUT_SIZE);
		for (int j = 0; j < INPUT_SIZE / 2; j++) {
			sb.append('a');
		}
		
		StringBuilder rnd = new StringBuilder(MIDDLE);
		for (int j = 0; j < MIDDLE; j++)
			rnd.append((char) (Math.abs(rand.nextInt()) % ALPHABET_SIZE + 97));
		
		return sb.toString().concat(rnd.toString()).concat(sb.toString());
	}

}
