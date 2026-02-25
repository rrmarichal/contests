import assert from "node:assert"

function _isScramble(s1: string, s2: string, M: Map<string, Map<string, boolean>>): boolean {
  if (s1.length != s2.length) return false
  if (s1 == s2) return true
  if (!M.has(s1)) {
    M.set(s1, new Map())
  }
  if (M.get(s1)?.has(s2)) {
    return M.get(s1)!.get(s2)!
  }

  let result = false
  for (let i = 1; i < s1.length; i++) {
    const s1l = s1.substring(0, i)
    const s1r = s1.substring(i, s1.length + 1)

    const s2l1 = s2.substring(0, i)
    const s2r1 = s2.substring(i, s1.length + 1)

    const s2l2 = s2.substring(0, s1.length - i)
    const s2r2 = s2.substring(s1.length - i, s1.length + 1)

    if (
      s1r + s1l == s2 ||
      (_isScramble(s1l, s2l1, M) && _isScramble(s1r, s2r1, M)) ||
      (_isScramble(s1l, s2r1, M) && _isScramble(s1r, s2l1, M)) ||
      (_isScramble(s1l, s2r2, M) && _isScramble(s1r, s2l2, M))
    ) {
      result = true
      break
    }
  }

  M.get(s1)?.set(s2, result)
  return result
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
