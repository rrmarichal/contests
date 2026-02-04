using System.Diagnostics;

public class Solution
{
  /**
   * Memoized palindrome lookup utility.
   */
  private static bool IsPalindrome(string s, bool?[][] palindrome, int index, int length)
  {
    if (palindrome[index] == null)
    {
      palindrome[index] = new bool?[s.Length - index + 1];
    }

    if (length <= 1)
    {
      return (bool)(palindrome[index][length] = true);
    }

    if (s[index] != s[index + length - 1])
    {
      return (bool)(palindrome[index][length] = false);
    }

    if (palindrome[index][length].HasValue)
    {
      return palindrome[index][length].Value;
    }

    return (bool) (palindrome[index][length] = IsPalindrome(s, palindrome, index + 1, length - 2));
  }

  public int MinCut(string s)
  {
    if (s.Length == 1) return 0;

    // Optimal partition of substring s[0..k]
    var partition = new int[s.Length];
    // Track found palindromes
    // palindrome[k, l] is true iff s[k..k+l-1] is a palindrome substring
    var palindrome = new bool?[s.Length][];

    for (var index = 1; index < s.Length; index++)
    {
      // edge case substring [0..index] is palindrome
      if (IsPalindrome(s, palindrome, 0, index + 1))
      {
        partition[index] = 0;
        continue;
      }

      partition[index] = int.MaxValue;
      for (var k = 1; k <= index; k++)
      {
        if (IsPalindrome(s, palindrome, k, index - k + 1))
        {
          partition[index] = Math.Min(partition[index], partition[k - 1] + 1);
        }
      }
    }

    return partition.Last();
  }

  public static void Main()
  {
    Debug.Assert(new Solution().MinCut("aab") == 1);
    Debug.Assert(new Solution().MinCut("a") == 0);
    Debug.Assert(new Solution().MinCut("ab") == 1);
    Debug.Assert(new Solution().MinCut("abaxabaab") == 2);
    Debug.Assert(new Solution().MinCut("abcdefgh") == 7);
  }
}
