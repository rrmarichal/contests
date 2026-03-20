using System.Text;

class Program
{
  static string Solve(string s)
  {
    var lastIndex = new int[10];
    var indices = new List<int>[10];
    for (var i = 0; i < s.Length; i++)
    {
      var key = s[i]-'0';
      lastIndex[key] = i;
      if (indices[key] == null)
      {
        indices[key] = [];
      }
      indices[key].Add(i);
    }

    var result = new StringBuilder();
    for (var c = 0; c <= 9; c++)
    {
      if (indices[c] == null) continue;

      for (var i = indices[c].Count - 1; i >= 0; i--)
      {
        var index = indices[c][i];
        var less = false;
        for (var k = 0; k < c; k++)
        {
          if (lastIndex[k] > index)
          {
            less = true;
            break;
          }
        }

        result.Append(less ? Math.Min(c+1, 9).ToString() : c.ToString());
      }
    }

    return result.ToString();
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
