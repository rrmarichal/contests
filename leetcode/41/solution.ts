import assert from "node:assert"

function firstMissingPositive(list: number[]): number {
  for (let i = 0; i < list.length; i++) {
    if (list[i] < 0 || list[i] > list.length) list[i] = 0;
  }

  let index = -1
  while (++index < list.length) {
    if (list[index] == -index-1) {
      continue
    }

    if (list[index] == index+1) {
      list[index] = -index-1
      continue
    }

    let value = list[index]
    while (value > 0) {
      let tmp = value
      value = list[tmp - 1]
      list[tmp - 1] = -tmp
    }
  }

  let i = 0
  while (i < list.length && list[i] == -i - 1) {
    i++
  }

  return i + 1
}

assert.equal(firstMissingPositive([1, 2, 0]), 3)
assert.equal(firstMissingPositive([3, 4, -1, 1]), 2)
assert.equal(firstMissingPositive([7, 8, 9, 11, 12]), 1)
assert.equal(firstMissingPositive([2, 1]), 3)
assert.equal(firstMissingPositive([2, 3, 4, 1]), 5)
assert.equal(firstMissingPositive([2, 3, 4, 1]), 5)
assert.equal(firstMissingPositive([-1, -2]), 1)
