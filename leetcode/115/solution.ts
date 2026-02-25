import assert from "node:assert"

// T[i,j] counts how many distinct subsequences of s[1..i] equal t[1..j]
// T[i,j] = {
// - if s[i] == t[j] -> T[i-1][j-1]+T[i-1][j]
// - if s[i] != t[j] -> T[i-1][j]
// - plus edge cases
// }
function numDistinct(s: string, t: string): number {
  const T: number[][] = Array.from({ length: s.length }, () =>
    Array.from({ length: t.length }, () => 0),
  )

  for (let i = 0; i < s.length; i++)
    for (let j = 0; j < Math.min(i + 1, t.length); j++) {
      if (s[i] == t[j]) {
        if (i == 0) {
          T[i][j] = 1
        } else if (j == 0) {
          T[i][j] = T[i - 1][j] + 1
        } else {
          T[i][j] = T[i - 1][j - 1] + T[i - 1][j]
        }
      } else {
        if (i > 0) T[i][j] = T[i - 1][j]
      }
    }

  return T[s.length - 1][t.length - 1]
}

assert.equal(numDistinct("rabbbit", "rabbit"), 3)
assert.equal(numDistinct("babgbag", "bag"), 5)
assert.equal(numDistinct("bagag", "bag"), 3)
assert.equal(numDistinct("dd", "dd"), 1)
assert.equal(numDistinct("ddd", "dd"), 3)
