import java.util.*;

public class Solution {

	public static void main(String[] args) {
		Solution sol = new Solution();
        int[] nums = new int[args.length];
        for (int j = 0; j < args.length; j++)
            nums[j] = Integer.parseInt(args[j]);
		System.out.println(sol.find132pattern(nums));
	}

    public boolean find132pattern(int[] nums) {
        if (nums.length == 0)
            return false;
    	int[] min = new int[nums.length];
    	min[0] = nums[0];
        for (int j = 1; j < nums.length; j++)
        	if (nums[j] < min[j - 1])
        		min[j] = nums[j];
        	else
        		min[j] = min[j - 1];
        TreeSet<Integer> s = new TreeSet<Integer>();
        s.add(nums[nums.length - 1]);
        for (int j = nums.length - 2; j > 0; j--) {
            Integer ceiling = s.ceiling(min[j - 1] + 1);
        	if (ceiling != null && ceiling < nums[j])
                return true;
            s.add(nums[j]);
        }
        return false;
    }

}
