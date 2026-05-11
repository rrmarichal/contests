namespace Greedy_Scanner.Tests;

public class UnitTest1
{
    [Fact]
    public void Test1()
    {
        var result = new Solution().FindRotateSteps("godding", "gd");
        Assert.Equal(4, result);
    }

    [Fact]
    public void Test2()
    {
        var result = new Solution().FindRotateSteps("godding", "godding");
        Assert.Equal(13, result);
    }

    [Fact]
    public void Test3()
    {
        var result = new Solution().FindRotateSteps("caotmcaataijjxi", "oatjiioicitatajtijciocjcaaxaaatmctxamacaamjjx");
        Assert.Equal(137, result);
    }

    [Fact]
    public void Test4()
    {
        var result = new Solution().FindRotateSteps("eh", "h");
        Assert.Equal(2, result);
    }
}
