using NUnit.Framework;
using AdHocTraversal;

namespace Tests
{
    public class AdHocTraversalTests
    {
        private readonly Solution solution;

        public AdHocTraversalTests()
        {
            solution = new Solution();
        }

        [Test]
        public void NoFlippingSequenceTest()
        {
            var root = new TreeNode(1);
            root.left = new TreeNode(2);
            var result = solution.FlipMatchVoyage(root, new[] { 2, 1 });
            Assert.AreEqual(result.Count, 1);
            Assert.AreEqual(result[0], -1);
        }

        [Test]
        public void OneFlipTest()
        {
            var root = new TreeNode(1);
            root.left = new TreeNode(2);
            root.right = new TreeNode(3);
            var result = solution.FlipMatchVoyage(root, new[] { 1, 3, 2 });
            Assert.AreEqual(result.Count, 1);
            Assert.AreEqual(result[0], 1);
        }

        [Test]
        public void NoFlipTest()
        {
            var root = new TreeNode(1);
            root.left = new TreeNode(2);
            root.right = new TreeNode(3);
            var result = solution.FlipMatchVoyage(root, new[] { 1, 2, 3 });
            Assert.AreEqual(result.Count, 0);
        }

        [Test]
        public void TwoFlipsTest()
        {
            var root = new TreeNode(1);
            root.left = new TreeNode(2);
            root.right = new TreeNode(3);
            
            root.left.left = new TreeNode(4);
            root.left.right = new TreeNode(5);

            root.right.left = new TreeNode(7);
            root.right.right = new TreeNode(6);

            var result = solution.FlipMatchVoyage(root, new[] { 1, 3, 6, 7, 2, 4, 5 });
            Assert.AreEqual(result.Count, 2);
            Assert.AreEqual(result[0], 1);
            Assert.AreEqual(result[1], 3);
        }

        [Test]
        public void ThreeFlipsTest()
        {
            var root = new TreeNode(1);
            root.left = new TreeNode(2);
            root.right = new TreeNode(3);
            
            root.left.left = new TreeNode(4);
            root.left.right = new TreeNode(5);

            root.right.left = new TreeNode(7);
            root.right.right = new TreeNode(6);

            var result = solution.FlipMatchVoyage(root, new[] { 1, 3, 6, 7, 2, 5, 4 });
            Assert.AreEqual(result.Count, 3);
            Assert.AreEqual(result[0], 1);
            Assert.AreEqual(result[1], 3);
            Assert.AreEqual(result[2], 2);
        }
    }
}