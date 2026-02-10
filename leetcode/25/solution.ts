import assert from "node:assert"

//  Definition for singly-linked list.
class ListNode {
  val: number;
  next: ListNode | null;
  constructor(val?: number, next?: ListNode | null) {
    this.val = val === undefined ? 0 : val;
    this.next = next === undefined ? null : next;
  }
}

function listToArray(head: ListNode | null): number[] {
  let result = []
  while (head != null) {
    result.push(head.val)
    head = head.next
  }

  return result
}

function _reverse(head: ListNode, k: number): [H: ListNode, T: ListNode, N: ListNode | null, C: number] {
  let count = 1
  let T = head, tail = head, current = head.next
  head.next = null
  while (current != null) {
    let tmp = current.next
    current.next = tail
    tail = current
    current = tmp

    count++
    if (count == k) return [tail, T, current, count]
  }

  return [tail, T, current, count]
}

function reverseKGroup(head: ListNode | null, k: number): ListNode | null {
  if (head == null) return null
  if (k == 1) return head

  let R: ListNode | null = null
  let [H, T, N, C] = _reverse(head, k)
  R = H
  while (N != null) {
    let [H0, T0, N0, C0] = _reverse(N, k)
    T.next = H0

    if (N0 == null) {
      if (C0 < k) {
        [H0, T0, N0, C0] = _reverse(H0, k)
        T.next = H0
      }
      return R
    }

    H = H0
    N = N0
    T = T0
    C = C0
  }

  return R
}

assert.deepEqual([2,1,4,3,5], listToArray(reverseKGroup(new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(4, new ListNode(5))))), 2)))
assert.deepEqual([3,2,1,4,5], listToArray(reverseKGroup(new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(4, new ListNode(5))))), 3)))
assert.deepEqual([2,1,4,3,6,5], listToArray(reverseKGroup(new ListNode(1, new ListNode(2, new ListNode(3, new ListNode(4, new ListNode(5, new ListNode(6, null)))))), 2)))
assert.deepEqual([3,2,1], listToArray(reverseKGroup(new ListNode(1, new ListNode(2, new ListNode(3))), 3)))
