import sys
from fractions import Fraction

class TestInfo:
    def __init__(self):
        pass
    
class Solution:
    def __init__(self, input):
        """
        Input
        """
        self.T = int(input.readline())
        self.tests = []
        for j in range(self.T):
            self.tests.append(None)

    def _solve(self, testInfo):
        """
        Solve for testInfo instance
        """
        return None

    def solve(self):
        result = []
        for t in range(self.T):
            case = self._solve(self.tests[t])
            result.append('Case #{}: {}'.format(t + 1, case))
        return result

if __name__ == '__main__':
    solution = Solution(sys.stdin)
    result = solution.solve()
    for case in result:
        print(case)
