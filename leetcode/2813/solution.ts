import assert from "node:assert"

// When greedily choosing the "k" highest profit items in the collection,
// we produce an initial solution S0 with K0 categories.
// Higher-profit solutions will have K > K0 categories.

// - If K0 is equal to k, we got the optimal -- S0 contains all categories
// - Adding a new category to an existing (possibly optimal) solution:
// - Remove item from category C and add item from category D

function findMaximumElegance(items: number[][], k: number): number {
  const solutionCategories: Set<number> = new Set()
  const solutionBuckets: Map<number, number> = new Map()

  items.sort((a, b) => b[0] - a[0])
  const optimal: number[][] = []
  let targetProfit = 0
  for (let i = 0; i < k; i++) {
    optimal.push(items[i])
    targetProfit += items[i][0]
    solutionCategories.add(items[i][1])

    if (!solutionBuckets.has(items[i][1])) {
      solutionBuckets.set(items[i][1], 0)
    }
    solutionBuckets.set(items[i][1], solutionBuckets.get(items[i][1])! + 1)
  }
  let best = targetProfit + solutionCategories.size * solutionCategories.size
  let backwardIndex = k - 1
  let forwardIndex = k
  while (true) {
    while (
      forwardIndex < items.length &&
      solutionCategories.has(items[forwardIndex][1])
    ) {
      forwardIndex++
    }
    if (forwardIndex == items.length) break
    while (
      backwardIndex >= 0 &&
      solutionBuckets.get(items[backwardIndex][1]) == 1
    ) {
      backwardIndex--
    }
    if (backwardIndex < 0) break

    // swap category at forwardIndex with item from category at backwardIndex
    targetProfit += items[forwardIndex][0] - items[backwardIndex][0]
    solutionBuckets.set(
      items[backwardIndex][1],
      solutionBuckets.get(items[backwardIndex][1])! - 1,
    )
    solutionCategories.add(items[forwardIndex][1])

    if (
      targetProfit + solutionCategories.size * solutionCategories.size >
      best
    ) {
      best = targetProfit + solutionCategories.size * solutionCategories.size
    }
    backwardIndex--
  }

  return best
}

assert.equal(
  findMaximumElegance(
    [
      [4, 2],
      [4, 3],
      [4, 5],
      [1, 5],
      [4, 7],
      [10, 8],
      [9, 8],
      [6, 8],
    ],
    4,
  ),
  38,
)

assert.equal(
  findMaximumElegance(
    [
      [3, 2],
      [5, 1],
      [10, 1],
    ],
    2,
  ),
  17,
)

assert.equal(
  findMaximumElegance(
    [
      [3, 1],
      [3, 1],
      [2, 2],
      [5, 3],
    ],
    3,
  ),
  19,
)

assert.equal(
  findMaximumElegance(
    [
      // 1
      [6, 1],
      [3, 1],
      [1, 1],
      // 2
      [4, 2],
      [3, 2],
      // 3
      [5, 3],
      [4, 3],
      [3, 3],
    ],
    5,
  ),
  31,
)

assert.equal(
  findMaximumElegance(
    [
      [1, 1],
      [2, 1],
      [3, 1],
    ],
    3,
  ),
  7,
)

assert.equal(
  findMaximumElegance(
    [
      [3, 1],
      [3, 1],
      [2, 2],
      [5, 3],
    ],
    2,
  ),
  12,
)
