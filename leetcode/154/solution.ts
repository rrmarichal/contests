import assert from "node:assert"

function findMinWithingRange(numbers: number[], low: number, high: number): number {
  if (low == high) return numbers[low]
  if (numbers[low] < numbers[high]) return numbers[low]
  const pivot = Math.trunc((low+high)/2)
  return Math.min(findMinWithingRange(numbers, low, pivot), findMinWithingRange(numbers, pivot+1, high))
}

function findMin(numbers: number[]): number {
  return findMinWithingRange(numbers, 0, numbers.length - 1)
};

assert.equal(findMin([1,3,5]), 1)
assert.equal(findMin([2,2,2,0,1]), 0)
