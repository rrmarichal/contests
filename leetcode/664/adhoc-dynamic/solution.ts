import assert from "node:assert"

type Partition = {
  low: number
  high: number
}

function* _partitions(
  s: string,
  low: number,
  high: number,
  c: string,
): IterableIterator<Partition> {
  let index = low
  while (index <= high) {
    while (index <= high && s[index] == c) index++
    if (index > high) break

    let end = index
    while (end <= high && s[end] != c) end++

    yield { low: index, high: end - 1 }

    index = end
  }
}

function _selection(
  s: string,
  low: number,
  high: number,
  T: number[][],
): number {
  // if (low >= s.length || high >= s.length) return s.length + 1
  if (T[low][high] != 0) return T[low][high]

  const uniques = new Set(s.substring(low, high + 1))
  if (uniques.size == 1) {
    return (T[low][high] = 1)
  }

  // const unique = new Set([s[low], s[high]])
  const unique = new Set([s[low]])
  let best = s.length + 1
  for (const c of unique) {
    // Print character "c" over the tape from low to high
    // then paint partitions generated after taking "c-segments" out

    let cbest = 1
    const partitions = _partitions(s, low, high, c)
    for (const partition of partitions) {
      cbest += _selection(s, partition.low, partition.high, T)
    }

    if (cbest < best) best = cbest
  }

  for (let i = low; i < high; i++) {
    const icut = _selection(s, low, i, T) + _selection(s, i + 1, high, T)
    if (icut < best) best = icut
  }

  if (s[low] == s[high]) {
    let rl = 1,
      rr = 1
    while (s[low] == s[low + rl]) rl++
    while (s[high] == s[high - rr]) rr++

    const pbest = Math.min(1 + _selection(s, low + rl, high - rr, T))
    if (pbest < best) best = pbest
  }

  return (T[low][high] = best)
}

function strangePrinter(s: string): number {
  const T: number[][] = Array.from<number, number[]>({ length: s.length }, () =>
    Array.from<number, number>({ length: s.length }, () => 0),
  )
  const R = _selection(s, 0, s.length - 1, T)
  // console.log(T)
  return R
}

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
assert.equal(strangePrinter("caaabdbbcbccdbcbcdcccabdcdadbccaaaddaaccbadddabca"), 20)
assert.equal(strangePrinter("abcdefghijabcdefghijabcdefghijabcdefghijabcdefghijabcdefghijababcdefbcdefghijabcdefghijabcdefghijaaa"), 87)
assert.equal(strangePrinter("abbbaba"), 3)
assert.equal(strangePrinter("aabbbabaa"), 3)

// assert.equal(strangePrinter("dcdbacadbcdabcdcdbdbabcbdacacbdcdabdb"), 21)
