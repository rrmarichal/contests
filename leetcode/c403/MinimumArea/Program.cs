namespace MinimumArea;

class Solution
{
    public int MinimumArea(int[][] grid)
    {
        var upperMost = grid.Length - 1;
        var leftMost = grid[0].Length - 1;
        var rightMost = 0;
        var bottomMost = 0;
        for (var i = 0; i < grid.Length; i++)
        {
            for (var j = 0; j < grid[0].Length; j++)
            {
                if (grid[i][j] == 1)
                {
                    if (upperMost > i)
                    {
                        upperMost = i;
                    }
                    if (leftMost > j)
                    {
                        leftMost = j;
                    }
                    if (bottomMost < i)
                    {
                        bottomMost = i;
                    }
                    if (rightMost < j)
                    {
                        rightMost = j;
                    }
                }
            }
        }

        return (bottomMost - upperMost + 1) * (rightMost - leftMost + 1);
    }

    static void Main(string[] args)
    {
        var solution = new Solution();
        // Console.WriteLine(solution.MinimumArea([[0, 1, 0], [1, 0, 1]]));
        Console.WriteLine(solution.MinimumArea([[1, 0], [0, 0]]));
    }
}
