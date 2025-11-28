// https://www.hackerrank.com/challenges/minimum-loss/problem?isFullScreen=false

class MergeSortResult
{

    /*
     * Complete the 'minimumLoss' function below.
     *
     * The function is expected to return an INTEGER.
     * The function accepts LONG_INTEGER_ARRAY price as parameter.
     */

    public static int minimumLoss(List<long> price)
    {
        return (int)mergeBuySell(price, 0, price.Count - 1);
    }

    private static long mergeBuySell(List<long> price, int left, int right)
    {
        if (left == right)
        {
            return (long)1e16;
        }

        if (left + 1 == right)
        {
            if (price[left] > price[right])
            {
                return price[left] - price[right];
            }

            (price[left], price[right]) = (price[right], price[left]);
            return (long)1e16;
        }

        var pivot = (left + right)/2;
        var bleft = mergeBuySell(price, left, pivot);
        var bright = mergeBuySell(price, pivot + 1, right);

        // At this point left and right sub-arrays are sorted in descending order
        // For each element i in the left sub-array, perform a binary search in the right sub-array
        // looking for the maximum element that's less than i.
        var merger = (long)1e16;
        for (var i = left; i <= pivot; i++)
        {
            var best = search(price, price[i], pivot + 1, right);

            if (best < merger)
            {
                merger = best;
            }
        }
        merge(price, left, pivot, right);

        return Math.Min(Math.Min(bleft, bright), merger);
    }

    private static void merge(List<long> price, int left, int pivot, int right)
    {
        var merged = new List<long>(right - left + 1);
        var x = left;
        var y = pivot + 1;
        while (merged.Count < right - left + 1)
        {
            if (y > right)
            {
                merged.Add(price[x++]);
            }
            else if (x > pivot)
            {
                merged.Add(price[y++]);
            }
            else if (price[x] > price[y])
            {
                merged.Add(price[x++]);
            }
            else
            {
                merged.Add(price[y++]);
            }
        }

        for (var i = left; i <= right; i++)
        {
            price[i] = merged[i - left];
        }
    }

    private static long search(List<long> price, long item, int left, int right)
    {
        while (left < right)
        {
            var pivot = (left + right)/2;
            if (price[pivot] < item)
            {
                right = pivot;
            }
            else
            {
                left = pivot + 1;
            }
        }

        if (item > price[left])
        {
            return item - price[left];
        }

        return (long)1e16;
    }

}

/*
 * This solution does not work. Non-existence of a buy-sell pair at indices (i, i+target) does not
 * mean a solution at [1..i-1] does not exist
 */
class BinarySearchResult
{

    /*
     * Complete the 'minimumLoss' function below.
     *
     * The function is expected to return an INTEGER.
     * The function accepts LONG_INTEGER_ARRAY price as parameter.
     */

    public static int minimumLoss(List<long> price)
    {
        var min = 1L;
        var max = (long)1e16;
        var lastOccurrenceMap = new Dictionary<long, int>();

        for (var i = 0; i < price.Count; i++)
        {
            if (price[i] < min)
            {
                min = price[i];
            }

            if (price[i] > max)
            {
                max = price[i];
            }

            if (!lastOccurrenceMap.ContainsKey(price[i]))
            {
                lastOccurrenceMap.Add(price[i], i);
            }
            else if (lastOccurrenceMap[price[i]] < i)
            {
                lastOccurrenceMap[price[i]] = i;
            }
        }

        var left = min;
        var right = max;
        var target = 0L;
        while (left < right)
        {
            target = left + (right - left) / 2;
            if (minimumLossExist(price, lastOccurrenceMap, target))
            {
                right = target;
            }
            else
            {
                left = target + 1;
            }
        }

        return (int)target;
    }

    private static bool minimumLossExist(List<long> price, Dictionary<long, int> lastOccurrenceMap, long target)
    {
        for (var i = 0; i < price.Count - 1; i++)
        {
            if (lastOccurrenceMap.ContainsKey(price[i] - target) && lastOccurrenceMap[price[i] - target] > i)
            {
                return true;
            }
        }

        return false;
    }

}

class Solution
{
    public static void Main(string[] args)
    {
        TextWriter textWriter = new StreamWriter(Console.OpenStandardOutput());

        int n = Convert.ToInt32(Console.ReadLine().Trim());

        List<long> price = Console.ReadLine().TrimEnd().Split(' ').ToList().Select(priceTemp => Convert.ToInt64(priceTemp)).ToList();

        int result = MergeSortResult.minimumLoss(price);

        textWriter.WriteLine(result);

        textWriter.Flush();
        textWriter.Close();
    }
}
