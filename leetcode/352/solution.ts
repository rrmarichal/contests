import assert from "node:assert"

/**
 * Your SummaryRanges object will be instantiated and called as such:
 * var obj = new SummaryRanges()
 * obj.addNum(value)
 * var param_2 = obj.getIntervals()
 */
class Interval<T> {
  min: number
  max: number

  constructor(value: number) {
    this.min = this.max = value
  }

  add(value: number) {
    if (value < this.min) this.min = value
    if (value > this.max) this.max = value
  }

  size(): number {
    return this.max - this.min
  }

  *[Symbol.iterator]() {
    for (let i = this.min; i <= this.max; i++) {
      yield i
    }
  }
}

class SummaryRanges {
  private readonly visited: Map<number, Interval<number>>
  private readonly intervals: Set<Interval<number>>

  constructor() {
    this.visited = new Map<number, Interval<number>>()
    this.intervals = new Set<Interval<number>>
  }

  addNum(value: number): void {
    if (this.visited.has(value)) return

    const leftjoint = this.visited.get(value - 1)
    const rightjoint = this.visited.get(value + 1)

    if (leftjoint && rightjoint) {
      // Merge smaller set into bigger one
      if (leftjoint.size() > rightjoint.size()) {
        leftjoint.add(rightjoint.max)


        this.visited.set(value, leftjoint)
        for (const right of rightjoint) {
          this.visited.set(right, leftjoint)
        }

        this.visited.set(value, leftjoint)
        this.intervals.delete(rightjoint)
      } else {
        rightjoint.add(leftjoint.min)

        this.visited.set(value, rightjoint)
        for (const left of leftjoint) {
          this.visited.set(left, rightjoint)
        }

        this.visited.set(value, rightjoint)
        this.intervals.delete(leftjoint)
      }

    } else if (leftjoint || rightjoint) {
      if (leftjoint) {
        leftjoint.add(value)
        this.visited.set(value, leftjoint)
      } else if (rightjoint) {
        rightjoint.add(value)
        this.visited.set(value, rightjoint)
      }
    } else {
      const interval = new Interval(value)
      this.visited.set(value, interval)
      this.intervals.add(interval)
    }
  }

  getIntervals(): number[][] {
    const result: number[][] = []
    for (const ds of this.intervals) {
      result.push([ds.min, ds.max])
    }
    result.sort((a, b) => a[0] - b[0])
    return result
  }
}

const t0 = new SummaryRanges()
t0.addNum(1) // arr = [1]
assert.deepEqual(t0.getIntervals(), [[1,1]])
t0.addNum(3) // arr = [1, 3]
assert.deepEqual(t0.getIntervals(), [[1, 1], [3, 3]])
t0.addNum(7) // arr = [1, 3, 7]
assert.deepEqual(t0.getIntervals(), [[1, 1], [3, 3], [7, 7]])
t0.addNum(2) // arr = [1, 2, 3, 7]
assert.deepEqual(t0.getIntervals(), [[1, 3], [7, 7]])
t0.addNum(6) // arr = [1, 2, 3, 6, 7]
assert.deepEqual(t0.getIntervals(), [[1, 3], [6, 7]])

const t1 = new SummaryRanges()
t1.addNum(0)
t1.addNum(4)
t1.addNum(6)
t1.addNum(7)
t1.addNum(8)
assert.deepEqual(t1.getIntervals(), [[0, 0], [4, 4], [6,8]])
t1.addNum(5)
assert.deepEqual(t1.getIntervals(), [[0, 0], [4, 8]])

const t2 = new SummaryRanges()
t2.addNum(6)
t2.addNum(6)
t2.addNum(0)
t2.addNum(4)
t2.addNum(8)
t2.addNum(7)
t2.addNum(6)
t2.addNum(4)
t2.addNum(7)
assert.deepEqual(t2.getIntervals(), [[0, 0], [4, 4], [6,8]])
t2.addNum(5)
assert.deepEqual(t2.getIntervals(), [[0, 0], [4, 8]])
