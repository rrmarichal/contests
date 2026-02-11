import assert from "node:assert"

function largestRectangleArea(heights: number[]): number {
  const stack = []
  let maxArea = 0

  for (let i = 0; i <= heights.length; i++) {
    const height = heights[i] || 0

    while (stack.length && height < heights[stack[stack.length - 1]]) {
      const index = stack.pop()
      const left = stack.length ? stack[stack.length - 1] + 1 : 0
      maxArea = Math.max(maxArea, (i - left) * heights[index!])
    }

    stack.push(i)
  }

  return maxArea
}

assert.equal(largestRectangleArea([2, 1, 5, 6, 2, 3]), 10)
