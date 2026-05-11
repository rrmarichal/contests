namespace Greedy_Scanner;

record Sweeper(int KeyIndex, int RingIndex, int Value);

public class Solution
{
  public int FindRotateSteps(string ring, string key)
  {
    var Q = new Queue<Sweeper>();
    var best = new int[key.Length, ring.Length];

    for (var i = 0; i < ring.Length; i++)
    {
      if (ring[i] == key[0])
      {
        Q.Enqueue(new Sweeper(0, i, 1 + Math.Min(i, ring.Length - i)));
        best[0, i] = Q.Last().Value;
      }
    }

    while (Q.Count > 0)
    {
      var current = Q.Dequeue();
      for (var next = 0; next < ring.Length; next++)
      {
        if (current.KeyIndex + 1 < key.Length && ring[next] == key[current.KeyIndex + 1])
        {
          var clockwise = Math.Abs(next - current.RingIndex);
          var counterClockwise = ring.Length - clockwise;
          var sweep = new Sweeper(current.KeyIndex + 1, next, 1 + current.Value + Math.Min(clockwise, counterClockwise));

          if (best[sweep.KeyIndex, sweep.RingIndex] == 0 || best[sweep.KeyIndex, sweep.RingIndex] > sweep.Value)
          {
            Q.Enqueue(sweep);
            best[sweep.KeyIndex, sweep.RingIndex] = sweep.Value;
          }
        }
      }
    }

    var result = int.MaxValue;
    for (var i = 0; i < ring.Length; i++)
    {
      if (best[key.Length - 1, i] > 0 && best[key.Length - 1, i] < result)
        result = best[key.Length - 1, i];
    }

    return result;
  }
}
