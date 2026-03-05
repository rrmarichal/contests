import assert from "node:assert"

function _removeInvalidParentheses(s: string, current: string, index: number, delta: number, removed: number, best: string[][]) {
  if (index == s.length) {
    if (delta == 0) {
      best[removed].push(current)
    }
    return
  }
  if (s[index] != "(" && s[index] != ")") _removeInvalidParentheses(s, current + s[index], index+1, delta, removed, best); else
  if (s[index] == "(") {
    // try not removing open parenthesis at index
    _removeInvalidParentheses(s, current+"(", index+1, delta+1, removed, best);
    // try removing open parenthesis at index
    _removeInvalidParentheses(s, current, index+1, delta, removed+1, best);
  } else {
    // try NOT removing closing parenthesis at index
    if (delta > 0) _removeInvalidParentheses(s, current+")", index+1, delta-1, removed, best);
    // try removing closing parenthesis at index
    _removeInvalidParentheses(s, current, index+1, delta, removed+1, best);
  }
}

function removeInvalidParentheses(s: string): string[] {
  const best: string[][] = Array.from({ length: s.length + 1 }, () => [])
  _removeInvalidParentheses(s, "", 0, 0, 0, best)
  for (let i = 0; i < best.length; i++)
    if (best[i].length > 0) return [...new Set(best[i])].sort()

  throw new Error()
}

assert.deepEqual(removeInvalidParentheses("()())()"), ["(())()", "()()()"])
assert.deepEqual(removeInvalidParentheses("(a)())()"), ["(a())()", "(a)()()"])
assert.deepEqual(removeInvalidParentheses(")("), [""])
