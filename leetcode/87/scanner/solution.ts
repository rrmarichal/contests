import assert from "node:assert"

function charEq(s1: string, s2: string): boolean {
  return [...s1].sort().join() == [...s2].sort().join()
}

function _isScramble(
  s1: string,
  s2: string,
  M: Map<string, Map<string, boolean>>,
): boolean {
  if (s1 == s2) return true
  if (!charEq(s1, s2)) return false

  if (!M.has(s1)) {
    M.set(s1, new Map())
  }
  if (M.get(s1)?.has(s2)) {
    return M.get(s1)!.get(s2)!
  }

  let s1LeftScan = "",
    s1RightScan = s1,
    s2LeftScan = "",
    s2RightScan = s2,
    reverseS2LeftScan = "",
    reverseS2RightScan = s2

  for (let i = 0; i < s1.length - 1; i++) {
    s1LeftScan += s1[i]
    s1RightScan = s1RightScan.substring(1, s1.length)

    s2LeftScan += s2[i]
    s2RightScan = s2RightScan.substring(1, s2.length)

    reverseS2LeftScan += s2[s2.length - i - 1]
    reverseS2RightScan = reverseS2RightScan.substring(
      0,
      reverseS2RightScan.length - 1,
    )

    if (charEq(s1LeftScan, s2LeftScan) && charEq(s1RightScan, s2RightScan)) {
      if (
        _isScramble(s1LeftScan, s2LeftScan, M) &&
        _isScramble(s1RightScan, s2RightScan, M)
      ) {
        M.get(s1)?.set(s2, true)
        return true
      }
    }

    if (
      charEq(s1LeftScan, reverseS2LeftScan) &&
      charEq(s1RightScan, reverseS2RightScan)
    ) {
      if (
        _isScramble(s1LeftScan, reverseS2LeftScan, M) &&
        _isScramble(s1RightScan, reverseS2RightScan, M)
      ) {
        M.get(s1)?.set(s2, true)
        return true
      }
    }
  }

  M.get(s1)?.set(s2, false)
  return false
}

function isScramble(s1: string, s2: string): boolean {
  return _isScramble(s1, s2, new Map())
}

assert.equal(isScramble("great", "rgeat"), true)
assert.equal(isScramble("abcde", "caebd"), false)
assert.equal(isScramble("a", "a"), true)
assert.equal(isScramble("abb", "bba"), true)
assert.equal(isScramble("abc", "cba"), true)
assert.equal(isScramble("abcdd", "dbdac"), false)
assert.equal(isScramble("ccabcbabcbabbbbcbb", "bbbbabccccbbbabcba"), false)
assert.equal(isScramble("abcdbdacbdac", "bdacabcdbdac"), true)
assert.equal(
  isScramble("eebaacbcbcadaaedceaaacadccd", "eadcaacabaddaceacbceaabeccd"),
  false,
)
