import assert from "node:assert"

function maximalRectangle(matrix: string[][]): number {
  const downAcc: number[][] = Array.from({ length: matrix.length }, () =>
    Array.from({ length: matrix[0].length }),
  )
  for (let r = matrix.length - 1; r >= 0; r--)
    for (let c = matrix[0].length - 1; c >= 0; c--) {
      downAcc[r][c] = matrix[r][c] == "0" ? 0 : 1
      if (matrix[r][c] == "1" && r + 1 < matrix.length) downAcc[r][c] += downAcc[r + 1][c]
    }

  let best = 0
  for (let r = 0; r < matrix.length; r++) {
    for (let c = 0; c < matrix[0].length; c++) {
      let k = 0,
        minDepth = downAcc[r][c]
      while (c + k < matrix[0].length && matrix[r][c + k] == "1") {
        if ((k + 1) * minDepth > best) best = (k + 1) * minDepth
        k++
        minDepth = Math.min(minDepth, downAcc[r][c + k])
      }
    }
  }
  return best
}

assert.equal(
  maximalRectangle([
    ["1", "0", "1", "0", "0"],
    ["1", "0", "1", "1", "1"],
    ["1", "1", "1", "1", "1"],
    ["1", "0", "0", "1", "0"],
  ]),
  6,
)
assert.equal(maximalRectangle([["0"]]), 0)
assert.equal(maximalRectangle([["1"]]), 1)
assert.equal(
  maximalRectangle([
    ["0", "0", "0"],
    ["0", "0", "0"],
    ["1", "1", "1"],
  ]),
  3,
)
assert.equal(
  maximalRectangle([
    ["1", "0", "1", "1", "1"],
    ["0", "1", "0", "1", "0"],
    ["1", "1", "0", "1", "1"],
    ["1", "1", "0", "1", "1"],
    ["0", "1", "1", "1", "1"],
  ]),
  6,
)
