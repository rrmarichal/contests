import assert from "node:assert"

type Partition = {
  low: number
  high: number
}

function _partitions(s: string, low: number, high: number): Partition[] {
  const result: Partition[] = []
  let index = low+1

  while (index <= high) {
    let end = index
    while (end <= high && s[end] != s[low]) end++

    result.push({ low: index, high: end - 1 })
    index = end + 1
  }

  return result
}

function _selection(
  s: string,
  low: number,
  high: number,
  T: number[][],
): number {
  if (T[low][high] != 0) return T[low][high]
  if (low == high) return (T[low][high] = 1)

  const partitions = _partitions(s, low, high)

  // base(edge) case, print first (and possibly last) characters, then solve for inner sequence
  let best =
    1 + _selection(s, low + 1, partitions[partitions.length - 1].high, T)
  for (let k = 0; k < partitions.length - 1; k++) {
    // Parenthesization problem.
    // - Print from [low+1..partition[k]] -> does not include lead character
    // - Print from partition[k] to high -> does include lead character
    const pcost =
      _selection(s, low + 1, partitions[k].high, T) +
      _selection(s, partitions[k].high + 1, high, T)
    if (pcost < best) best = pcost
  }

  // lead character printout accounted in sub-problems optimization
  // no need to add 1 to optimal computation
  return (T[low][high] = best)
}

function _removeRuns(s: string): string {
  let index = 0
  let clean = ""
  while (index < s.length) {
    let r = 1
    while (index + r < s.length && s[index] == s[index + r]) r++

    clean += s[index]
    index += r
  }

  return clean
}

function strangePrinter(s: string): number {
  const clean = _removeRuns(s)
  const T: number[][] = Array.from<number, number[]>(
    { length: clean.length },
    () => Array.from<number, number>({ length: clean.length }, () => 0),
  )
  return _selection(clean, 0, clean.length - 1, T)
}

assert.equal(_removeRuns("aaabbb"), "ab")
assert.equal(_removeRuns("aba"), "aba")
assert.equal(_removeRuns("abcc"), "abc")

assert.equal(strangePrinter("aaabbb"), 2)
assert.equal(strangePrinter("aba"), 2)
assert.equal(strangePrinter("abcc"), 3)
assert.equal(strangePrinter("bgtgb"), 3)
assert.equal(strangePrinter("tbgtgb"), 4)
assert.equal(strangePrinter("ababaddcbcaabdbdddcccddbbaa"), 10)
assert.equal(strangePrinter("bababaddcbcaabdbdddcccddbbaab"), 11)
assert.equal(strangePrinter("dbababaddcbcaabdbdddcccddbbaabdd"), 12)
assert.equal(strangePrinter("bdbababaddcbcaabdbdddcccddbbaabddb"), 13)
assert.equal(strangePrinter("ccbdbababaddcbcaabdbdddcccddbbaabddb"), 14)
assert.equal(strangePrinter("dddccbdbababaddcbcaabdbdddcccddbbaabddb"), 15)
assert.equal(
  strangePrinter("caaabdbbcbccdbcbcdcccabdcdadbccaaaddaaccbadddabca"),
  20,
)
assert.equal(
  strangePrinter(
    "abcdefghijabcdefghijabcdefghijabcdefghijabcdefghijabcdefghijababcdefbcdefghijabcdefghijabcdefghijaaa",
  ),
  87,
)
assert.equal(
  strangePrinter(
    "zuvckrvtmihlhnbbgycnxthqtskcjgakbypnrkhduqqcdsfksjzscjivbtzmbzxezosrabwurnywhdizmktqtcnuxmjyoidpwxg",
  ),
  74,
)
assert.equal(
  strangePrinter(
    "abcdabcdbcabcdabcdbcabcdabcdbcahijabcdefghijabcdefghijabcdvckrvtmihlhnbbgycnxthvckrvtmihlhnbbgycnxth",
  ),
  75,
)
assert.equal(strangePrinter("abbbaba"), 3)
assert.equal(strangePrinter("aabbbabaa"), 3)
assert.equal(strangePrinter("dcdbacadbcdabcdcdbdbabcbdacacbdcdabdb"), 21)
