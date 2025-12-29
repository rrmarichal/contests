public class Solution
{
  private int _Reverse(int absoluteValue, string max)
  {
      var reverse = new string(absoluteValue.ToString().Reverse().ToArray()).PadLeft(max.Length, '0');
      return reverse.CompareTo(max) > 0 ? 0 : int.Parse(reverse);
  }

  public int Reverse(int x)
  {
    if (x == int.MinValue)
    {
      return 0;
    }

    if (x < 0)
    {
      return -_Reverse(Math.Abs(x), "2147483648");
    }
    else
    {
      return _Reverse(x, "2147483647");
    }
  }

  public static void Main()
  {
    var s = new Solution();
    Console.WriteLine(s.Reverse(-123));
    Console.WriteLine(s.Reverse(123));
    Console.WriteLine(s.Reverse(120));
    Console.WriteLine(s.Reverse(2147483647));
    Console.WriteLine(s.Reverse(2147483641));
    Console.WriteLine(s.Reverse(-2147483647));
    Console.WriteLine(s.Reverse(-2147483641));
    Console.WriteLine(s.Reverse(-2147483648));
  }
}
