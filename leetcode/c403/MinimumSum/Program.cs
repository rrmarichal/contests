namespace MinimumSum;

class Solution
{
    private int MinimumArea(int[][] grid, int row0, int row1, int column0, int column1)
    {
        var upperMost = row1;
        var leftMost = column1;
        var rightMost = column0;
        var bottomMost = row0;
        for (var i = row0; i <= row1; i++)
        {
            for (var j = column0; j <= column1; j++)
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

    private int MinimumSum2Rectangles(int[][] grid, int row0, int row1, int column0, int column1)
    {
        var minSum = 1000;

        // cut horizontal line
        if (row0 < row1)
        {
            for (var cut = row0; cut < row1; cut++)
            {
                var rectAbove = MinimumArea(grid, row0, cut, column0, column1);
                var rectBelow = MinimumArea(grid, cut + 1, row1, column0, column1);

                if (rectAbove + rectBelow < minSum && rectAbove > 0 && rectBelow > 0)
                {
                    minSum = rectAbove + rectBelow;

                    // Console.WriteLine("MinimumSum2Rectangles horizontal line {0} {1} {2} {3}", minSum, cut, rectAbove, rectBelow);
                }
            }
        }

        if (column0 < column1)
        {
            // cut vertical line
            for (var cut = column0; cut < column1; cut++)
            {
                var rectLeft = MinimumArea(grid, row0, row1, column0, cut);
                var rectRight = MinimumArea(grid, row0, row1, cut + 1, column1);

                if (rectLeft + rectRight < minSum && rectLeft > 0 && rectRight > 0)
                {
                    minSum = rectLeft + rectRight;

                    // Console.WriteLine("MinimumSum2Rectangles vertical line {0} {1}", minSum, cut);
                }
            }
        }

        return minSum;
    }

    public int MinimumSum(int[][] grid)
    {
        var minSum = int.MaxValue;

        // 1st cut , horizontal line
        for (var cut = 0; cut < grid.Length - 1; cut++)
        {
            // Case #1 , one rectangle above the line and two below
            var rectAbove = MinimumArea(grid, 0, cut, 0, grid[0].Length - 1);
            var rectBelow = MinimumSum2Rectangles(grid, cut + 1, grid.Length - 1, 0, grid[0].Length - 1);
            if (rectAbove + rectBelow < minSum && rectAbove > 0 && rectBelow > 0)
            {
                minSum = rectAbove + rectBelow;

                // Console.WriteLine("MinimumSum horizontal case 1 {0} {1} {2} {3}", minSum, cut, rectAbove, rectBelow);
            }

            // Case #2 , two rectangles above the line and one below
            rectAbove = MinimumSum2Rectangles(grid, 0, cut, 0, grid[0].Length - 1);
            rectBelow = MinimumArea(grid, cut + 1, grid.Length - 1, 0, grid[0].Length - 1);
            if (rectAbove + rectBelow < minSum && rectAbove > 0 && rectBelow > 0)
            {
                minSum = rectAbove + rectBelow;

                // Console.WriteLine("MinimumSum horizontal case 2 {0} {1}", minSum, cut);
            }
        }

        // 1st cut, vertical line
        for (var cut = 0; cut < grid[0].Length - 1; cut++)
        {
            // Case #1 , one rectangle to the left and two rectangles to the right
            var rectLeft = MinimumArea(grid, 0, grid.Length - 1, 0, cut);
            var rectRight = MinimumSum2Rectangles(grid, 0, grid.Length - 1, cut + 1, grid[0].Length - 1);
            if (rectLeft + rectRight < minSum && rectLeft > 0 && rectRight > 0)
            {
                minSum = rectLeft + rectRight;

                // Console.WriteLine("MinimumSum vertical case 1 {0} {1}", minSum, cut);
            }

            // Case #2 , two rectangles to the left and one rectangle to the right
            rectLeft = MinimumSum2Rectangles(grid, 0, grid.Length - 1, 0, cut);
            rectRight = MinimumArea(grid, 0, grid.Length - 1, cut + 1, grid[0].Length - 1);
            if (rectLeft + rectRight < minSum && rectLeft > 0 && rectRight > 0)
            {
                minSum = rectLeft + rectRight;

                // Console.WriteLine("MinimumSum vertical case 2 {0} {1}", minSum, cut);
            }
        }

        return minSum;
    }

    static void Main(string[] args)
    {
        var solution = new Solution();
        // Console.WriteLine(solution.MinimumSum([[1, 0, 1], [1, 1, 1]]));
        // Console.WriteLine(solution.MinimumSum([[1, 0, 1, 0], [0, 1, 0, 1]]));
        // Console.WriteLine(solution.MinimumSum([[1, 1], [0, 1]]));
        // Console.WriteLine(solution.MinimumSum([[1, 1], [1, 1]]));

        // Console.WriteLine(solution.MinimumSum([[1, 1, 1], [1, 1, 1], [1, 1, 1]]));
        // Console.WriteLine(solution.MinimumSum([[1, 1, 1, 1, 1], [1, 1, 1, 1, 1], [1, 1, 1, 1, 1], [1, 1, 1, 1, 1], [1, 1, 1, 1, 1]]));
        // Console.WriteLine(solution.MinimumSum([[0, 0, 0, 0, 1], [0, 0, 0, 0, 0], [0, 0, 1, 0, 0], [0, 0, 0, 0, 0], [1, 0, 0, 0, 0]]));
        // Console.WriteLine(solution.MinimumSum([[0, 0, 1], [1, 1, 0]]));
        Console.WriteLine(solution.MinimumSum([[0, 0, 0], [0, 0, 0], [0, 0, 1], [1, 1, 0]]));
    }
}
