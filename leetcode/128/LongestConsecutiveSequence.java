import java.util.Arrays;
import java.util.HashMap;
import java.util.Random;

import org.apache.commons.lang3.time.StopWatch;

public class LongestConsecutiveSequence {

	public static void main(String[] args) {
		int[] test = generateTest(1000000);
		StopWatch sw0 = new StopWatch();
		StopWatch sw1 = new StopWatch();
		sw0.start();
		int s0 = new Solution0().longestConsecutive(test);
		System.out.println(String.format("Alg 0 returned %d in %d miliseconds", s0, sw0.getTime()));
		sw1.start();
		int s1 = new Solution1().longestConsecutive(test);
		System.out.println(String.format("Alg 1 returned %d in %d miliseconds", s1, sw1.getTime()));
	}
	
	private static void print(int[] test) {
		for (int n: test) System.out.print(String.format("%d ", n));
		System.out.println();
		Arrays.sort(test);
		for (int n: test) System.out.print(String.format("%d ", n));
		System.out.println();
	}

	private static int[] generateTest(int length) {
		int[] result = new int[length];
		Random r = new Random();
		for (int j = 0; j < length; j++)
			result[j] = r.nextInt() % (length / 10);
		return result;
	}
	
	static class Solution1 {
		public int longestConsecutive(int[] nums) {
			Arrays.sort(nums);
			int j = 0;
			int best = 0;
			while (j < nums.length) {
				int k = j + 1;
				int d = 1;
				while (k < nums.length && (nums[k - 1] + 1 == nums[k] || nums[k - 1] == nums[k])) {
					if (nums[k - 1] + 1 == nums[k])
						d++;
					++k;
				}
				if (d > best)
					best = d;
				j = k;
			}
			return best;
		}
	}

	static class Solution0 {
	    public int longestConsecutive(int[] nums) {
	        boolean[] touch = new boolean[nums.length];
	        HashMap<Integer, Integer> s = new HashMap();
	        for (int j = 0; j < nums.length; j++)
	            s.put(nums[j], j);
	        int best = 0;
	        for (int n: nums) {
	            if (!touch[s.get(n)]) {
	                int consecutive = expand(n, s, touch);
	                if (consecutive > best)
	                    best = consecutive;
	            }
	        }
	        return best;
	    }
	    
	    int expand(int n, HashMap<Integer, Integer> s, boolean[] touch) {
	        touch[s.get(n)] = true;
	        // expand right
	        int right = n + 1;
	        while (s.containsKey(right)) {
	            touch[s.get(right)] = true;
	            right++;
	        }
	        // expand left
	        int left = n - 1;
	        while (s.containsKey(left)) {
	            touch[s.get(left)] = true;
	            left--;
	        }
	        return right - left - 1;
	    }
	}

}
