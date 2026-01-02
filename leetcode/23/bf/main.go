package main

import . "mergeklists/utils"

func mergeKLists(lists []*ListNode) *ListNode {
	if len(lists) == 0 {
		return nil
	}

	var head *ListNode = nil
	var tail *ListNode = nil

	for true {
		var min = 10001
		var index = -1

		for i := 0; i < len(lists); i++ {
			if lists[i] != nil && lists[i].Val < min {
				min = lists[i].Val
				index = i
			}
		}

		if index == -1 {
			break
		}

		if head == nil {
			head = &ListNode{Val: min, Next: nil}
			tail = head
		} else {
			tail.Next = &ListNode{Val: min, Next: nil}
			tail = tail.Next
		}

		lists[index] = lists[index].Next
	}

	return head
}

func main() {
	var l0 = &ListNode{Val: 1, Next: &ListNode{Val: 4, Next: &ListNode{Val: 5, Next: nil}}}
	var l1 = &ListNode{Val: 1, Next: &ListNode{Val: 3, Next: &ListNode{Val: 4, Next: nil}}}
	var l2 = &ListNode{Val: 2, Next: &ListNode{Val: 6, Next: nil}}
	PrintList(mergeKLists([]*ListNode{l0, l1, l2}))

	var l3 *ListNode = nil
	PrintList(mergeKLists([]*ListNode{l3}))

	var l4 = &ListNode{Val: 1, Next: &ListNode{Val: 3, Next: &ListNode{Val: 4, Next: &ListNode{Val: 6, Next: &ListNode{Val: 8, Next: &ListNode{Val: 9, Next: &ListNode{Val: 12, Next: nil}}}}}}}
	var l5 = &ListNode{Val: 1, Next: &ListNode{Val: 2, Next: &ListNode{Val: 5, Next: &ListNode{Val: 7, Next: &ListNode{Val: 11, Next: &ListNode{Val: 21, Next: &ListNode{Val: 24, Next: nil}}}}}}}
	var l6 = &ListNode{Val: -4, Next: &ListNode{Val: 0, Next: &ListNode{Val: 4, Next: &ListNode{Val: 7, Next: &ListNode{Val: 10, Next: &ListNode{Val: 14, Next: &ListNode{Val: 22, Next: &ListNode{Val: 29, Next: nil}}}}}}}}

	PrintList(mergeKLists([]*ListNode{l4, l5, l6}))
}
