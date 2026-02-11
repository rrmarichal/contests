import assert from "node:assert"

const FACTORIALS: number[] = [1, 1, 2, 6, 24, 120, 720, 5040, 40320, 362880]

function minmax(k: number, length: number): number {
  for (let i = length; i >= 0; i--) {
    if (FACTORIALS[i] <= k) return i
  }
  return -1
}

function rank(r: number, visited: boolean[]): number {
  let count = 0
  for (let i = 1; i <= visited.length; i++) {
    if (!visited[i]) count++
    if (count == r) return i
  }
  return 0
}

function getPermutation(n: number, k: number): string {
  const visited: boolean[] = Array.from({ length: 10 }, () => false)
  let result = ""
  while (result.length < n) {
    if (k <= FACTORIALS[n - result.length - 1]) {
      const value = rank(1, visited)
      visited[value] = true
      result += value.toString()
    } else {
      const mm = minmax(k, n - result.length - 1)
      const index = Math.floor((k - 1) / FACTORIALS[mm]) + 1
      const value = rank(index, visited)
      visited[value] = true
      result += value.toString()
      if (k%FACTORIALS[mm] > 0)
        k = (k%FACTORIALS[mm])
      else
        k = FACTORIALS[mm]
    }
  }
  return result
}

assert.equal(getPermutation(3, 6), "321")
assert.equal(getPermutation(4, 6), "1432")
assert.equal(getPermutation(3, 1), "123")
assert.equal(getPermutation(3, 2), "132")
assert.equal(getPermutation(3, 3), "213")
assert.equal(getPermutation(4, 9), "2314")
assert.equal(getPermutation(4, 3), "1324")

