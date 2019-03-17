public class Solution {

    public int minMoves(int[] nums) {
        int min = nums[0];
        for (int j = 1; j < nums.length; j++)
            if (nums[j] < min)
                min = nums[j];
        int result = 0;
        for (int j = 0; j < nums.length; j++)
            result += nums[j] - min;
        return result;
    }
    
}
