namespace TotalCost;

class DynamicSolution
{
    internal long MaximumTotalCost(int[] nums)
    {
        var best = new long[nums.Length];
        best[0] = nums[0];
        for (var i = 1; i < nums.Length; i++)
        {
            best[i] = nums[i] + best[i - 1];

            if (i == 1)
            {
                best[i] = Math.Max(best[i], nums[0] + -nums[1]);
            }
            else
            {
                best[i] = Math.Max(best[i], nums[i - 1] + -nums[i] + best[i - 2]);
            }
        }

        return best[nums.Length - 1];
    }
}

class AdHocSolution
{
    private bool Sign(int num)
    {
        return num >= 0;
    }

    private long Sum(int[] nums, int start, int length)
    {
        var sum = 0L;
        for (var i = 0; i < length; i++)
        {
            sum += nums[start + i];
        }
        return sum;
    }

    private long SplitCost(int[] nums, int start, int length)
    {
        var altSum = 0L;
        var sign = 1;
        for (var i = 0; i < length; i++)
        {
            altSum += nums[start + i] * sign;
            sign = -sign;
        }
        return altSum;
    }

    private long OptimalNegativeRunSplit(int[] nums, int start, int length)
    {
        if (length == 0)
        {
            return 0;
        }
        if (length == 1)
        {
            return nums[start];
        }
        // try spliting at 0 or at 1
        return Math.Max(SplitCost(nums, start, length), nums[start] + SplitCost(nums, start + 1, length - 1));
    }

    public long MaximumTotalCost(int[] nums)
    {
        var totalCost = 0L;
        var i = 0;
        while (i < nums.Length)
        {
            var runIndex = i;
            var runLength = 1;

            while (i < nums.Length - 1 && Sign(nums[i]) == Sign(nums[i + 1]))
            {
                i++;
                runLength++;
            }

            Console.WriteLine("runIndex: {0} runLength: {1}", runIndex, runLength);

            if (Sign(nums[runIndex]))
            {
                totalCost += Sum(nums, runIndex, runLength);
            }
            else
            {
                // Negative run prefixed by positive run
                // If it's not the beginning of the array, add first item to previous run
                if (runIndex > 0)
                {
                    var runCost = SplitCost(nums, runIndex, runLength);
                    var shiftedCost = -nums[runIndex] + OptimalNegativeRunSplit(nums, runIndex + 1, runLength - 1);
                    totalCost += Math.Max(runCost, shiftedCost);
                }
                else
                {
                    totalCost += OptimalNegativeRunSplit(nums, runIndex, runLength);
                }
            }
            i++;
        }

        return totalCost;
    }

    static void Main(string[] args)
    {
        // var solution = new AdHocSolution();
        var solution = new DynamicSolution();
        // Console.WriteLine(solution.MaximumTotalCost([1, -2, 3, 4]));
        // Console.WriteLine(solution.MaximumTotalCost([1, -1, 1, -1]));
        // Console.WriteLine(solution.MaximumTotalCost([0]));
        Console.WriteLine(solution.MaximumTotalCost([1, -1]));
        // Console.WriteLine(solution.MaximumTotalCost([0, -3, -4]));
        // Console.WriteLine(solution.MaximumTotalCost([-13, -11, -5, -4, -19, 11, -3, 4, 20, -10, 12]));
    }
}

// [-13, -11, -5, -4, -19, 11, -3, 4, 20, -10, 12]

// [-13, -11, -5, -4, -19] => Max(-13 + 11 - 5 + 4 -19, -13 - 11 + 5 - 4 + 19) => -4
// [11, -3] => 14
// [ 4 ] => 4
// [20 , -10 , 12 ] => 42

// -4 + 14 + 4 + 42 = 56

// [-13, -11, -5, -4, -19] => 8
// -13 + 11 - 5 => -7
// -4 + 19 => 15

// [-13, -11, -5, -4, -19] => -13 + 11 - 5 + 4 - 19 = -22
// [-13, -11, -5, -4, -19] => -13 - 11 + 5 - 4 + 19 = -4

// [-13] => -13
// [-13, -11] => -2
// [-13, -11, -5] => -7
// [-13, -11, -5, -4] => -3
// [-13, -11, -5, -4, -19] => -22


// [-13, -11, -5, -4, -19]
// [-13] => -13
// [-11, -5, -4, -19] => -11 + 5 - 4 + 19 = 9
