import unittest

from src.solution import Solution

class Solution_Tests(unittest.TestCase):
    """
    Solution test cases
    """
    def test0(self):
        s0 = Solution(open("test_data/0.in"))
        result = s0.solve()
        self.assertEqual(result[0], "Case #1: None")
