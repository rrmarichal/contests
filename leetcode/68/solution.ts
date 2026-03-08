import assert from "node:assert"

function _center(words: string[], maxWidth: number): string {
  if (words.length == 1) return _ljustify(words, maxWidth)

  const wl = words.reduce((p, c) => p + c.length, 0)
  const tsp = maxWidth - wl
  const sbw = Math.floor(tsp / (words.length - 1))
  let rem = tsp % (words.length - 1)
  let result = ""
  for (const word of words) {
    result += word
    if (result.length == maxWidth) break

    for (let i = 0; i < sbw; i++) result += " "
    if (rem > 0) {
      result += " "
      rem--
    }
  }
  return result
}

function _ljustify(words: string[], maxWidth: number): string {
  let result = words.join(" ")
  while (result.length < maxWidth) result += " "
  return result
}

function fullJustify(words: string[], maxWidth: number): string[] {
  const result: string[] = []
  let i = 0
  while (i < words.length) {
    let length = words[i].length
    let curr: string[] = [words[i]]
    let c = 1
    while (
      i + c < words.length &&
      length + 1 + words[i + c].length <= maxWidth
    ) {
      curr.push(words[i + c])
      length += 1 + words[i + c].length
      c++
    }

    if (i + c < words.length) {
      result.push(_center(curr, maxWidth))
    } else {
      result.push(_ljustify(curr, maxWidth))
    }

    i = i + c
  }
  return result
}

assert.deepEqual(
  fullJustify(
    ["This", "is", "an", "example", "of", "text", "justification."],
    16,
  ),
  ["This    is    an", "example  of text", "justification.  "],
)

assert.deepEqual(
  fullJustify(["What", "must", "be", "acknowledgment", "shall", "be"], 16),
  ["What   must   be", "acknowledgment  ", "shall be        "],
)

assert.deepEqual(
  fullJustify(
    [
      "Science",
      "is",
      "what",
      "we",
      "understand",
      "well",
      "enough",
      "to",
      "explain",
      "to",
      "a",
      "computer.",
      "Art",
      "is",
      "everything",
      "else",
      "we",
      "do",
    ],
    20,
  ),
  [
    "Science  is  what we",
    "understand      well",
    "enough to explain to",
    "a  computer.  Art is",
    "everything  else  we",
    "do                  ",
  ],
)
