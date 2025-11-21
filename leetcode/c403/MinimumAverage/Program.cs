namespace c403;

class Solution
{
    public double MinimumAverage(int[] nums)
    {
        Array.Sort(nums);
        double minAverage = int.MaxValue;

        for (var j = 0; j < nums.Length / 2; j++)
        {
            if ((nums[j] + nums[nums.Length - j - 1]) / 2 < minAverage)
            {
                minAverage = (nums[j] + nums[nums.Length - j - 1]) / 2.0;
            }
        }

        return minAverage;
    }

    static void Main(string[] args)
    {
        var solution = new Solution();
        Console.WriteLine(solution.MinimumAverage([1, 2, 3, 7, 8, 9]));
    }
}
