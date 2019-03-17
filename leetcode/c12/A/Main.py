class Solution(object):
    def findRadius(self, houses, heaters):
        """
        :type houses: List[int]
        :type heaters: List[int]
        :rtype: int
        """
        houses.sort()
        heaters.sort()
        heaters = [-(1<<31)] + heaters + [1 << 31]
        a = 0
        mx = 0
        for x in houses:
            if (x > heaters[a+1]):
                while (x > heaters[a+1]):
                    a = a+1
            mx = max(mx, min(heaters[a+1]-x, x-heaters[a]))
        return mx

s = Solution()
r = s.findRadius([1,2,3,4],[1,5])
print(r)
