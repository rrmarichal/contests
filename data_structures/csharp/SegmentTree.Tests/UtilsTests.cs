using NUnit.Framework;

namespace SegmentTree.Tests {

	public class UtilsTests {

		[Test]
		public void NextPowerOfTwoTest0() {
			int np = Utils.NextPowerOfTwo(0);
			Assert.AreEqual(1, np);
		}

		[Test]
		public void NextPowerOfTwoTest1() {
			int np = Utils.NextPowerOfTwo(1);
			Assert.AreEqual(1, np);
		}

		[Test]
		public void NextPowerOfTwoTest2() {
			int np = Utils.NextPowerOfTwo(2);
			Assert.AreEqual(2, np);
		}

		[Test]
		public void NextPowerOfTwoTest3() {
			int np = Utils.NextPowerOfTwo(3);
			Assert.AreEqual(4, np);
		}

		[Test]
		public void NextPowerOfTwoTest4() {
			int np = Utils.NextPowerOfTwo(100);
			Assert.AreEqual(128, np);
		}

	}

}
