import assert from "node:assert"

function minPatches(nums: number[], n: number): number {
  let sum = 0,
    target = 1,
    index = 0,
    patches = 0

  while (sum < n) {
    if (index < nums.length) {
      if (nums[index] <= target) {
        sum += nums[index++]
        target = sum + 1
      } else {
        patches++
        sum += target
        target = sum + 1
      }
    } else {
      patches++
      target = sum + 1
      sum += sum + 1
    }
  }

  return patches
}

assert.equal(minPatches([1, 3], 6), 1)
assert.equal(minPatches([1, 5, 10], 20), 2)
assert.equal(minPatches([1, 5, 10], 1000), 8)
assert.equal(minPatches([10], 22), 4)
assert.equal(minPatches([1,2,2], 5), 0)
