import java.util.Arrays;
import java.util.Comparator;

public class RussianDolls {

    public int maxEnvelopes(int[][] envelopes) {
        if (envelopes.length == 0)
            return 0;
        Arrays.sort(envelopes, new Comparator<int[]>() {
			@Override
			public int compare(int[] o1, int[] o2) {
				return o1[0] - o2[0];
			}
		});
		int[] t = new int[envelopes.length];
		int max = 0;
		for (int j = 0; j < envelopes.length; j++) {
			t[j] = 1;
			for (int k = 0; k < j; k++)
				if (fits(envelopes[k], envelopes[j]) && t[k] + 1 > t[j])
					t[j] = t[k] + 1;
			if (t[j] > max)
				max = t[j];
		}
		return max;
    }

    private boolean fits(int[] x, int[] y) {
    	return x[0] < y[0] && x[1] < y[1];
    }

}
