namespace MaximumLengthII;

class Solution
{
    private int FindSequence(int[] nums, int index0, int index1)
    {
        var run = 2;
        var parity = 0;

        for (var index = index1 + 1; index < nums.Length; index++)
        {
            if (parity == 0 && nums[index] == nums[index0])
            {
                run++;
                parity = 1 - parity;
            }
            else if (parity == 1 && nums[index] == nums[index1])
            {
                run++;
                parity = 1 - parity;
            }
        }

        return run;
    }

    public int MaximumLength(int[] nums, int k)
    {
        nums = nums.Select(n => n % k).ToArray();
        var maxSeq = 2;
        var paired = new Dictionary<int, HashSet<int>>();

        for (var i = 0; i < nums.Length - 1; i++)
        {
            for (var j = i + 1; j < nums.Length; j++)
            {
                if (!paired.ContainsKey(Math.Min(nums[i], nums[j])))
                {
                    paired.Add(Math.Min(nums[i], nums[j]), []);
                }
                if (paired[Math.Min(nums[i], nums[j])].Contains(Math.Max(nums[i], nums[j])))
                {
                    continue;
                }
                paired[Math.Min(nums[i], nums[j])].Add(Math.Max(nums[i], nums[j]));

                var seq = FindSequence(nums, i, j);

                if (seq > maxSeq)
                {
                    maxSeq = seq;
                }
            }
        }

        return maxSeq;
    }

    static void Main(string[] args)
    {
        var solution = new Solution();
        System.Console.WriteLine(solution.MaximumLength([1, 2, 3, 4, 5], 2));
        System.Console.WriteLine(solution.MaximumLength([1, 4, 2, 3, 1, 4], 3));
    }
}
