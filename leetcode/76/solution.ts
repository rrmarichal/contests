import assert from "node:assert"

type Map = { [key: string]: number }

function covers(targetMap: Map, windowMap: Map): boolean {
  for (const key of Object.keys(targetMap)) {
    if (!windowMap[key] || windowMap[key] < targetMap[key]) return false
  }

  return true
}

function minWindow(s: string, t: string): string {
  let start = 0,
    end = 0
  let best = s.length + 1
  let result = ""
  const targetMap: Map = {}
  const windowMap: Map = {}
  for (const c of t) targetMap[c] = (targetMap[c] || 0) + 1
  for (const c of s) windowMap[c] = 0

  while (start <= end) {
    if (covers(targetMap, windowMap)) {
      if (end - start < best) {
        best = end - start
        result = s.substring(start, end)
      }
      windowMap[s[start++]]--
    } else if (end < s.length) {
      windowMap[s[end++]]++
    } else break
  }

  return result
}

assert.equal(minWindow("ADOBECODEBANC", "ABC"), "BANC")
assert.equal(minWindow("a", "a"), "a")
assert.equal(minWindow("a", "aa"), "")
assert.equal(minWindow("a", "b"), "")
assert.equal(minWindow("b", "a"), "")
assert.equal(minWindow("a", "ab"), "")
