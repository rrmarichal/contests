import assert from "node:assert"

type QueueItem = {
  words: number
  node: number
  prev: number[]
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

function _rebuild(node: number, Q: QueueItem[], begin: string, words: string[], visited: number[]): string[][] {
  if (node == -1) {
    return [[begin]]
  }

  const result: string[][] = []
  for (const prev of Q[visited[node]].prev) {
    const prefixes = _rebuild(prev, Q, begin, words, visited)
    for (const p of prefixes) {
      result.push([...p, words[node]])
    }
  }

  return result
}

function findLadders(
  beginWord: string,
  endWord: string,
  wordList: string[],
): string[][] {
  const G = _buildGraph(wordList)
  const Q: QueueItem[] = []
  const visited: number[] = new Array(wordList.length).fill(-1)
  const result: string[][] = []

  for (let i = 0; i < wordList.length; i++) {
    if (_dist(beginWord, wordList[i])) {
      Q.push({ node: i, words: 2, prev: [-1] })
      visited[i] = Q.length - 1
    }
  }

  let index = 0
  while (index < Q.length) {
    const current = Q[index++]
    if (wordList[current.node] == endWord) {
      return _rebuild(current.node, Q, beginWord, wordList, visited)
    }

    for (let i = 0; i < G[current.node].length; i++) {
      const node = G[current.node][i]
      if (visited[node] == -1) {
        Q.push({ node, words: current.words + 1, prev: [current.node] })
        visited[node] = Q.length - 1
      } else if (Q[visited[node]].words == current.words + 1) {
        Q[visited[node]].prev.push(current.node)
      }
    }
  }

  return []
}

assert.deepEqual(
  findLadders("hit", "cog", ["hot", "dot", "dog", "lot", "log", "cog"]),
  [
    ["hit", "hot", "dot", "dog", "cog"],
    ["hit", "hot", "lot", "log", "cog"],
  ],
)
assert.deepEqual(
  findLadders("hit", "cog", ["hot", "dot", "dog", "lot", "log"]),
  [],
)
