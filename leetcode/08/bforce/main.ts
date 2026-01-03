import * as assert from "node:assert";

function isMatch(s: string, p: string): boolean {
  if (s == "" && p == "") {
    return true
  }

  if (p == "") {
    return false
  }

  const pLast = p.charAt(p.length - 1)
  if (s == "") {
    if (pLast == "*") {
      return isMatch(s, p.substring(0, p.length - 2))
    }

    return false
  }

  const sLast = s.charAt(s.length - 1)
  if (pLast == sLast || pLast == ".") {
    return isMatch(s.substring(0, s.length - 1), p.substring(0, p.length - 1))
  }

  if (pLast == "*") {
    const pSecondToLast = p.charAt(p.length - 2)
    if (pSecondToLast == "." || pSecondToLast == sLast) {
      if (isMatch(s.substring(0, s.length - 1), p)) {
        return true
      }
    }

    return isMatch(s, p.substring(0, p.length - 2))
  }

  return false
}

assert(!isMatch("aa", "a"), '"aa", "a"')
assert(isMatch("aa", "a*"), '"aa", "a*"')
assert(isMatch("ab", ".*"), '"ab", ".*"')
/*
  "^abcd .*pq .*rs .*        tu$"
  " abcd  Xpq  Xrs  XtuYpqrs tu
*/
assert(isMatch("abcdXpqXrsXtuYpqrstu", "abcd.*pq.*rs.*tu"), '"abcdXpqXrsXtuYpqrstu", "abcd.*pq.*rs.*tu"')
assert(isMatch("aab", "c*a*b"), '"aab", "c*a*b"')
assert(isMatch("a", "ab*"), '"a", "ab*"')
assert(isMatch("bbbba", ".*a*a"), '"bbbba", ".*a*a"')
assert(!isMatch("a", ".*..a*"), '"a", ".*..a*"')
