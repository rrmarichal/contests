import assert from "node:assert"

function splitArray(nums: number[], k: number): number {
  const T: number[][] = Array.from({ length: nums.length }, () =>
    Array.from({ length: k + 1 }, () => 0),
  )
  T[0][1] = nums[0]
  for (let i = 1; i < nums.length; i++) {
    T[i][1] = T[i - 1][1] + nums[i]
    for (let j = 2; j <= Math.min(i + 1, k); j++) {
      T[i][j] = 1 << 30
      let sum = nums[i]
      for (let l = i - 1; l >= j-2; l--) {
        T[i][j] = Math.min(T[i][j], Math.max(T[l][j - 1], sum))
        sum += nums[l]
        if (sum > T[i][j]) break
      }
    }
  }

  return T[nums.length - 1][k]
}

assert.equal(splitArray([7, 2, 5, 10, 8], 2), 18)
assert.equal(splitArray([1, 2, 3, 4, 5], 2), 9)
assert.equal(splitArray([1000000], 1), 1000000)
assert.equal(splitArray([0, 1000000], 2), 1000000)
assert.equal(splitArray([0, 8, 1, 4], 4), 8)
