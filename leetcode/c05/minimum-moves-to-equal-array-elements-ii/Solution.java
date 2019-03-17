import java.util.*;

public class Solution {
	
	public static void main(String[] args) {
		Solution sol = new Solution();
		System.out.println(sol.minMoves2(new int[] {
            203125577, -349566234, 230332704, 48321315,
            66379082,386516853,50986744,-250908656,-425653504,-212123143
        }));
	}

	public int minMoves2(int[] nums) {
        Arrays.sort(nums);
        long left = 0, right = 0;
        for (int j = 1; j < nums.length; j++)
        	right += nums[j] - nums[0];
        long best = right;
        int p = 0;
        while (++p < nums.length) {
        	left = left + (long) p * ((long) (nums[p] - nums[p-1]));
        	right = right - ((long) (nums.length - p)) * ((long) (nums[p] - nums[p-1]));
        	if (left + right < best)
        		best = left + right;
        }
        return (int) best;
    }
	
}
