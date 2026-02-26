import assert from "node:assert"

/**
 * Definition for a binary tree node.
 */
class TreeNode {
  val: number
  left: TreeNode | null
  right: TreeNode | null
  constructor(val?: number, left?: TreeNode | null, right?: TreeNode | null) {
    this.val = val === undefined ? 0 : val
    this.left = left === undefined ? null : left
    this.right = right === undefined ? null : right
  }
}

type NodeResult = { max: number; down: number }

function _maxPathSum(current: TreeNode): NodeResult {
  let lr: NodeResult | null = null
  let rr: NodeResult | null = null

  if (current?.left != null) {
    lr = _maxPathSum(current?.left)
  }
  if (current?.right != null) {
    rr = _maxPathSum(current?.right)
  }

  if (lr == null && rr == null) return { max: current.val, down: current.val }
  if (lr != null && rr != null) {
    return {
      max: Math.max(lr.max, rr.max, lr.down + rr.down + current.val, lr.down + current.val, rr.down + current.val, current.val),
      down: Math.max(current.val + Math.max(lr.down, rr.down), current.val),
    }
  }
  if (lr != null)
    return {
      max: Math.max(lr.max, lr.down + current.val, current.val),
      down: Math.max(current.val + lr.down, current.val),
    }
  if (rr != null)
    return {
      max: Math.max(rr.max, rr.down + current.val, current.val),
      down: Math.max(current.val + rr.down, current.val),
    }

  throw new Error("should not get here")
}

function maxPathSum(root: TreeNode | null): number {
  return _maxPathSum(root!).max
}

assert.equal(maxPathSum(new TreeNode(2, new TreeNode(-1))), 2)
assert.equal(maxPathSum(new TreeNode(1, new TreeNode(2), new TreeNode(3))), 6)
assert.equal(
  maxPathSum(
    new TreeNode(
      -10,
      new TreeNode(9),
      new TreeNode(20, new TreeNode(15), new TreeNode(7)),
    ),
  ),
  42,
)
