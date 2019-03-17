public class FindDuplicateNumber {

    public int findDuplicate(int[] nums) {
        int left = 1, right = nums.length - 1;
        while (left < right) {
            int p = (right + left) >> 1;
            if (count(nums, left, p) > p - left + 1)
                right = p;
            else
                left = p + 1;
        }
        return left;
    }
    
    private int count(int[] nums, int down, int up) {
        int count = 0;
        for (int n : nums)
            if (n >= down && n <= up)
                count++;
        return count;
    }

}
