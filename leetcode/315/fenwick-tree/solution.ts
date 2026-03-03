import assert from "node:assert"

class FenwickTree {
  private tree: number[]

  constructor(size: number) {
    this.tree = Array.from({ length: size }, () => 0)
  }

  tag(index: number) {
    while (index < this.tree.length) {
      this.tree[index]++
      index += index & -index
    }
  }

  query(index: number): number {
    let result = 0
    while (index > 0) {
      result += this.tree[index]
      index -= index & -index
    }
    return result
  }
}

function countSmaller(nums: number[]): number[] {
  const result = Array.from<number>({ length: nums.length })
  const min = Math.min(...nums)
  const max = Math.max(...nums)
  const shift = -(min-1)
  const size = max - min + 2
  const tree = new FenwickTree(size)

  for (let i = nums.length - 1; i >= 0; i--) {
    result[i] = tree.query(shift + nums[i] - 1)
    tree.tag(shift + nums[i])
  }

  return result
}

assert.deepEqual(countSmaller([4, 3, 2, 1]), [3, 2, 1, 0])
assert.deepEqual(countSmaller([3, 2, 1]), [2, 1, 0])
assert.deepEqual(countSmaller([5, 2, 6, 1]), [2, 1, 1, 0])
assert.deepEqual(countSmaller([-1]), [0])
assert.deepEqual(countSmaller([-1, -1]), [0, 0])
