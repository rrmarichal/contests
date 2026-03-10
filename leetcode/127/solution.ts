import assert from "node:assert"

type QueueItem = {
  words: number
  node: number
}

function _dist(x: string, y: string): boolean {
  let d = 0
  for (let i = 0; i < x.length; i++) {
    if (x[i] != y[i]) d++
    if (d > 1) return false
  }

  return true
}

function _buildGraph(words: any) {
  const result: number[][] = Array.from({ length: words.length }, () => [])

  for (let i = 0; i < words.length - 1; i++)
    for (let j = i + 1; j < words.length; j++) {
      if (_dist(words[i], words[j])) {
        result[i].push(j)
        result[j].push(i)
      }
    }
  return result
}

function ladderLength(
  beginWord: string,
  endWord: string,
  wordList: string[],
): number {
  const G = _buildGraph(wordList)
  const Q: QueueItem[] = []
  const visited: boolean[] = []

  for (let i = 0; i < wordList.length; i++) {
    if (_dist(beginWord, wordList[i])) {
      Q.push({ words: 2, node: i })
      visited[i] = true
    }
  }

  let index = 0
  while (index < Q.length) {
    const current = Q[index++]
    if (wordList[current.node] == endWord) return current.words

    for (let i = 0; i < G[current.node].length; i++) {
      const node = G[current.node][i]
      if (!visited[node]) {
        Q.push({ node, words: current.words + 1 })
        visited[node] = true
      }
    }
  }

  return 0
}

assert.equal(
  ladderLength("hit", "cog", ["hot", "dot", "dog", "lot", "log", "cog"]),
  5,
)
assert.equal(ladderLength("hit", "cog", ["hot", "dot", "dog", "lot", "log"]), 0)
