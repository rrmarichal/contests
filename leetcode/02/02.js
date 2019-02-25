/**
 * Definition for singly-linked list.
 * function ListNode(val) {
 *     this.val = val;
 *     this.next = null;
 * }
 */
/**
 * @param {ListNode} l1
 * @param {ListNode} l2
 * @return {ListNode}
 */

function ListNode(val) {
    this.val = val;
    this.next = null;
}

const addTwoNumbers = (l1, l2) => {
    let result = current = new ListNode(0)
    let rem = 0
    while (l1 || l2) {
        const ds = rem + (l1 && l1.val || 0) + (l2 && l2.val || 0)
        current.val = ds % 10
        rem = Math.floor(ds / 10)
        console.log(current.val, rem)
        l1 = l1.next
        l2 = l2.next
        if (l1 || l2) {
            current = current.next = new ListNode(0)
        }
    }
    return result
}

const l1 = new ListNode(2)
l1.next = new ListNode(4)
l1.next.next = new ListNode(3)

const l2 = new ListNode(5)
l2.next = new ListNode(6)
l2.next.next = new ListNode(4)

addTwoNumbers(l1, l2)
