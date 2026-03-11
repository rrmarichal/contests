import assert from "node:assert"

function _wordBreak(
  s: string,
  index: number,
  wordDict: string[],
  prev: number[][],
): void {
  if (index < 0) return
  if (prev[index].length > 0) return

  for (let l = 0; l <= index; l++) {
    const word = s.substring(index - l, index + 1)
    if (wordDict.includes(word)) {
      prev[index].push(index - l - 1)
      _wordBreak(s, index - l - 1, wordDict, prev)
    }
  }
}

function _rebuild(index: number, s: string, prev: number[][]): string[][] {
  if (index == -1) return [[]]

  const result: string[][] = []
  for (const p of prev[index]) {
    const reverse = _rebuild(p, s, prev)
    for (const b of reverse) {
      result.push([...b, s.substring(p + 1, index + 1)])
    }
  }

  return result
}

function wordBreak(s: string, wordDict: string[]): string[] {
  const prev: number[][] = Array.from({ length: s.length }, () => [])
  _wordBreak(s, s.length - 1, wordDict, prev)
  return _rebuild(s.length - 1, s, prev).map((x) => x.join(" "))
}

assert.deepEqual(
  wordBreak("catsanddog", ["cat", "cats", "and", "sand", "dog"]),
  ["cats and dog", "cat sand dog"],
)

assert.deepEqual(
  wordBreak("pineapplepenapple", [
    "apple",
    "pen",
    "applepen",
    "pine",
    "pineapple",
  ]),
  ["pine apple pen apple", "pineapple pen apple", "pine applepen apple"],
)

assert.deepEqual(
  wordBreak("catsandog", ["cats", "dog", "sand", "and", "cat"]),
  [],
)
