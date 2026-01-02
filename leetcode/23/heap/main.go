package main

import (
	"container/heap"
	utils "mergeklists/utils"
)

// MinHeap of utils.ListNode pointers - ordered by Val
type MinHeap []*utils.ListNode

func (h MinHeap) Len() int           { return len(h) }
func (h MinHeap) Less(i, j int) bool { return h[i].Val < h[j].Val }
func (h MinHeap) Swap(i, j int)      { h[i], h[j] = h[j], h[i] }
func (h *MinHeap) Push(x any)        { *h = append(*h, x.(*utils.ListNode)) }
func (h *MinHeap) Pop() any {
	old := *h
	n := len(old)
	x := old[n-1]
	*h = old[0 : n-1]
	return x
}

func mergeKLists(lists []*utils.ListNode) *utils.ListNode {
	h := &MinHeap{}
	heap.Init(h)

	// Add the head of each non-nil list to the heap
	for _, node := range lists {
		if node != nil {
			heap.Push(h, node)
		}
	}

	dummy := &utils.ListNode{}
	current := dummy

	for h.Len() > 0 {
		// Pop the smallest node
		smallest := heap.Pop(h).(*utils.ListNode)
		current.Next = smallest
		current = current.Next

		// If this list has more nodes, push the next one
		if smallest.Next != nil {
			heap.Push(h, smallest.Next)
		}
	}

	return dummy.Next
}

func main() {
	var l0 = &utils.ListNode{Val: 1, Next: &utils.ListNode{Val: 4, Next: &utils.ListNode{Val: 5, Next: nil}}}
	var l1 = &utils.ListNode{Val: 1, Next: &utils.ListNode{Val: 3, Next: &utils.ListNode{Val: 4, Next: nil}}}
	var l2 = &utils.ListNode{Val: 2, Next: &utils.ListNode{Val: 6, Next: nil}}
	utils.PrintList(mergeKLists([]*utils.ListNode{l0, l1, l2}))

	var l3 *utils.ListNode = nil
	utils.PrintList(mergeKLists([]*utils.ListNode{l3}))

	var l4 = &utils.ListNode{Val: 1, Next: &utils.ListNode{Val: 3, Next: &utils.ListNode{Val: 4, Next: &utils.ListNode{Val: 6, Next: &utils.ListNode{Val: 8, Next: &utils.ListNode{Val: 9, Next: &utils.ListNode{Val: 12, Next: nil}}}}}}}
	var l5 = &utils.ListNode{Val: 1, Next: &utils.ListNode{Val: 2, Next: &utils.ListNode{Val: 5, Next: &utils.ListNode{Val: 7, Next: &utils.ListNode{Val: 11, Next: &utils.ListNode{Val: 21, Next: &utils.ListNode{Val: 24, Next: nil}}}}}}}
	var l6 = &utils.ListNode{Val: -4, Next: &utils.ListNode{Val: 0, Next: &utils.ListNode{Val: 4, Next: &utils.ListNode{Val: 7, Next: &utils.ListNode{Val: 10, Next: &utils.ListNode{Val: 14, Next: &utils.ListNode{Val: 22, Next: &utils.ListNode{Val: 29, Next: nil}}}}}}}}

	utils.PrintList(mergeKLists([]*utils.ListNode{l4, l5, l6}))
}
