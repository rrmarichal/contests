import assert from "node:assert"

/**
 * aka, peak finder
 * disconnects on equal ratings
 */
function candy(ratings: number[]): number {
  var candies = 0
  var run = 1
  var prevRun = -1

  // 0 for unknown
  // 1 for ascending
  // 2 for descending
  var ascending = 0

  ratings.push(ratings[ratings.length - 1])

  for (let i = 0; i < ratings.length - 1; i++) {
    if (ratings[i] == ratings[i+1]) {
      if (ascending == 1) {
        if (prevRun == -1) {
          candies += run*(run+1)/2
        } else {
          candies += run*(run+1)/2-1
        }
      } else {
        if (prevRun == -1) {
          candies += run*(run+1)/2
        } else {
          candies += prevRun > run ? run*(run-1)/2 : run*(run+1)/2 - prevRun
        }
      }

      ascending = 0
      prevRun = -1
      run = 1
    } else if (ratings[i] < ratings[i+1]) {
      if (ascending == 0 || ascending == 1) {
        ascending = 1
        run++
      } else {
        // peak counter, depends on previous run length
        if (prevRun == -1) {
          candies += run*(run+1)/2
        } else {
          candies += prevRun > run ? run*(run-1)/2 : run*(run+1)/2 - prevRun
        }

        prevRun = run
        run = 2
        ascending = 1
      }
    } else {
      if (ascending == 0 || ascending == 2) {
        ascending = 2
        run++
      } else {
        // sink counter, subtract one for double counted element
        if (prevRun == -1) {
          candies += run*(run+1)/2
        } else {
          candies += run*(run+1)/2-1
        }

        prevRun = run
        run = ascending = 2
      }
    }
  }

  return candies
}

assert.equal(candy([1, 0, 2]), 5)
assert.equal(candy([1, 2, 2]), 4)
assert.equal(candy([1, 2, 3, 4, 100]), 15)
assert.equal(candy([1, 2, 2, 2, 2, 1]), 8)
assert.equal(candy([1, 3, 2, 2, 1]), 7)
assert.equal(candy([1,2,3,5,4,3,2,1,4,3,2,1,3,2,1,1,2,3,4]), 47)
