using System.Text;

class Program
{
  static string _MergeSort(string s, int l, int h)
  {
    if (l == h) return s[l].ToString();

    var pivot = (l + h)/2;
    var ls = _MergeSort(s, l, pivot);
    var rs = _MergeSort(s, pivot+1, h);

    var inversions = 0;
    var li = 0;
    var ri = 0;
    var result = new StringBuilder();
    while (li < ls.Length || ri < rs.Length)
    {
      if (li == ls.Length)
      {
        result.Append(rs[ri++]);
        continue;
      }
      if (ri == rs.Length)
      {
        var shift = Math.Min(ls[li++] + inversions, '9');
        result.Append((char) shift);
        continue;
      }

      if (ls[li] + inversions <= rs[ri])
      {
        var shift = Math.Min(ls[li++] + inversions, '9');
        result.Append((char) shift);
        li++;
      }
      else
      {
        result.Append(rs[ri++]);
        inversions++;
      }
    }

    return result.ToString();
  }

  static string Solve(string s)
  {
    return _MergeSort(s, 0, s.Length - 1);
  }

  public static void Main(string[] args)
  {
    var N = int.Parse(Console.ReadLine());
    for (var i = 0; i < N; i++)
    {
      Console.WriteLine(Solve(Console.ReadLine()));
    }
  }
}
