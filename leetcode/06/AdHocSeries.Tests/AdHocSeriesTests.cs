using NUnit.Framework;
using AdHocSeries;

namespace Tests
{
    public class AdHocSeriesTests
    {
        private readonly Solution solution;

        public AdHocSeriesTests()
        {
            this.solution = new Solution();
        }

        [Test]
        [TestCase("A", 1, ExpectedResult = "A")]
        [TestCase("ABC", 2, ExpectedResult = "ACB")]
        [TestCase("PAYPALISHIRING", 3, ExpectedResult = "PAHNAPLSIIGYIR")]
        [TestCase("PAYPALISHIRING", 4, ExpectedResult = "PINALSIGYAHRPI")]
        public string Test1(string S, int R)
        {
            return solution.Convert(S, R);
        }
    }
}
