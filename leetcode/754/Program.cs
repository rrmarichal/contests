namespace _754;

class Program
{
    public int ReachNumber(int target)
    {
        if (target < 0)
        {
            target = -target;
        }

        var n = (int)((-1 + Math.Sqrt(1 + 8 * (long)target)) / 2);
        if (n * (n + 1) / 2 == target)
        {
            return n;
        }

        n++;
        while ((n * (n + 1) / 2 - target) % 2 != 0)
        {
            n++;
        }

        return n;
    }

    static void Main(string[] args)
    {
        var target = int.Parse(args[0]);
        var program = new Program();
        Console.WriteLine(program.ReachNumber(target));
    }
}
