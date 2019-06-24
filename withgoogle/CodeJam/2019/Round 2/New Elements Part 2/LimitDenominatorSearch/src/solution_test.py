import unittest
import sys

from src.solution import Solution

class Solution_Tests(unittest.TestCase):
    """
    Solution test cases
    """
    def test0(self):
        s0 = Solution(open("test_data/0.in"))
        result = s0.solve()
        self.assertEqual(result[0], "Case #1: 2 1")
        self.assertEqual(result[1], "Case #2: IMPOSSIBLE")
        self.assertEqual(result[2], "Case #3: 1 1")

    def test1(self):
        s0 = Solution(open("test_data/1.in"))
        result = s0.solve()
        self.assertEqual(result[0], "Case #1: 1 25")
        self.assertEqual(result[1], "Case #2: 2 1")

    def test2(self):
        s0 = Solution(open("test_data/2.in"))
        result = s0.solve()
        self.assertEqual(result[0], "Case #1: 8 29")
        self.assertEqual(result[1], "Case #2: 2 9")
        self.assertEqual(result[2], "Case #3: 83 100")
