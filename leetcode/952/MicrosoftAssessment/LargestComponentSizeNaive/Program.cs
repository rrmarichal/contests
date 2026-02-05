using System.Diagnostics;

public class Solution
{
  // Naive approach, solves for ~60% of test cases
  public int LargestComponentSize(int[] list)
  {
    var count = new int[list.Length];
    for (var i = 0; i < list.Length; i++)
    {
      if (count[i] == 0)
      {
        var visited = new HashSet<int>();
        count[i] = ComponentSize(i, list, visited);

        foreach (var node in visited)
        {
          if (node != i)
          {
            count[node] = count[i];
          }
        }
      }
    }

    return count.Max();
  }

  private static int ComponentSize(int node, int[] list, ISet<int> visited)
  {
    visited.Add(node);
    var count = 1;
    for (var next = 0; next < list.Length; next++)
    {
      if (node != next && GCD(list[node], list[next]) > 1 && !visited.Contains(next))
      {
        count += ComponentSize(next, list, visited);
      }
    }
    return count;
  }

  private static int GCD(int x, int y)
  {
    if (x == 0)
    {
      return y;
    }

    return GCD(y%x, x);
  }

  public static void Main()
  {
    Debug.Assert(new Solution().LargestComponentSize([4,6,15,35]) == 4);
    Debug.Assert(new Solution().LargestComponentSize([20,50,9,63]) == 2);
    Debug.Assert(new Solution().LargestComponentSize([2,3,6,7,4,12,21,39]) == 8);
    Debug.Assert(new Solution().LargestComponentSize([3,3,3]) == 3);
    Debug.Assert(new Solution().LargestComponentSize([1,2,3]) == 1);
    Debug.Assert(new Solution().LargestComponentSize([4,2,3]) == 2);
  }
}