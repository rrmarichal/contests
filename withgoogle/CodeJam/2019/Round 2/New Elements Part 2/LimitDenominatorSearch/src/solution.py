import sys
from fractions import Fraction

class TestInfo:
    def __init__(self, N, molecules):
        self.N = N
        self.molecules = molecules
    
class Solution:
    def __init__(self, input):
        self.T = int(input.readline())
        self.tests = []
        for t in range(self.T):
            N = int(input.readline())
            molecules = []
            for j in range(N):
                molecules.append([int(item) for item in input.readline().split()])
            self.tests.append(TestInfo(N, molecules))

    def bounds(self, testInfo):
        lb = Fraction(0)
        ub = Fraction(1e10)
        curr = testInfo.molecules[0]
        for j in range(1, testInfo.N):
            next = testInfo.molecules[j]
            if curr[1] >= next[1] and curr[0] >= next[0]:
                return 1, 0
            dc = curr[0] - next[0]
            dj = next[1] - curr[1]
            if curr[1] < next[1]:
                lb = max(lb, Fraction(dc, dj))
            elif curr[1] > next[1]:
                ub = min(ub, Fraction(dc, dj))
            curr = next
        return lb, ub

    def _solve(self, testInfo):
        lb, ub = self.bounds(testInfo)
        if not lb < ub:
            return None
        if ub - lb > 1:
            return int(lb) + 1
        avg = (lb + ub) / 2
        l, r = 1, int(1e10)
        while l < r:
            mid = (l + r) // 2
            approx = avg.limit_denominator(mid)
            if approx > lb and approx < ub:
                r = mid
            else:
                l = mid + 1
        return avg.limit_denominator(l)

    def solve(self):
        result = []
        for t in range(self.T):
            case = self._solve(self.tests[t])
            if case is None:
                result.append('Case #{}: IMPOSSIBLE'.format(t + 1))
            else:
                result.append('Case #{}: {} {}'.format(t + 1, case.denominator, case.numerator))
        return result

if __name__ == '__main__':
    solution = Solution(sys.stdin)
    result = solution.solve()
    for case in result:
        print(case)
