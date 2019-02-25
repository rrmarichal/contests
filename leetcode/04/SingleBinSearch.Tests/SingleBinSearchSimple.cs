using NUnit.Framework;
using SingleBinSearch;

namespace Tests
{
  [TestFixture]
  public class SingleBinSearchSimple
  {
    private readonly Solution solution;

    public SingleBinSearchSimple()
    {
      solution = new Solution();
    }

    [Test]
    [TestCase(new int[0], new[] { 2, 3, 4 }, ExpectedResult = 3.0)]
    [TestCase(new int[0], new[] { 2, 5 }, ExpectedResult = 3.5)]
    [TestCase(new[] { 2, 5, 11, 21 }, new int[0], ExpectedResult = 8.0)]
    public double OnOneArrayEmptyReturnsOthersMedian(int[] A, int[] B)
    {
      return solution.FindMedianSortedArrays(A, B);
    }

    [Test]
    [TestCase(new[] { 1, 2 }, new[] { 3, 4, 5 }, ExpectedResult = 3)]
    [TestCase(new[] { 1, 3, 5, 7 }, new[] { 2, 4, 6 }, ExpectedResult = 4)]
    [TestCase(new[] { 5 }, new[] { 1, 10 }, ExpectedResult = 5)]
    [TestCase(new[] { 9 }, new[] { 1, 2, 3, 4, 5, 6, 7, 8, 10, 11 }, ExpectedResult = 6)]
    [TestCase(new[] { 5 }, new[] { 1, 3 }, ExpectedResult = 3)]
    [TestCase(new[] { 5 }, new[] { 11, 13 }, ExpectedResult = 11)]
    [TestCase(new[] { 1, 5 }, new[] { 7, 11, 13 }, ExpectedResult = 7)]
    [TestCase(new[] { 1, 2, 3, 4 }, new[] { 7, 11, 13 }, ExpectedResult = 4)]
    [TestCase(new[] { 1, 1, 3, 3 }, new[] { 1, 1, 3, 3, 3 }, ExpectedResult = 3)]
    public double JudgeTestsOddCount(int[] A, int[] B)
    {
      return solution.FindMedianSortedArrays(A, B);
    }

    [Test]
    [TestCase(new[] { 1 }, new[] { 1 }, ExpectedResult = 1)]
    [TestCase(new[] { 1, 2 }, new[] { 3, 4 }, ExpectedResult = 2.5)]
    [TestCase(new[] { 3, 4 }, new[] { 1, 2 }, ExpectedResult = 2.5)]
    [TestCase(new[] { 11, 33 }, new[] { 11, 33 }, ExpectedResult = 22)]
    [TestCase(new[] { 1, 1, 3, 3 }, new[] { 1, 1, 3, 3 }, ExpectedResult = 2)]
    [TestCase(new[] { 3 }, new[] { 1, 2, 4 }, ExpectedResult = 2.5)]
    public double JudgeTestsEvenCount(int[] A, int[] B)
    {
      return solution.FindMedianSortedArrays(A, B);
    }
  }
}
