package main

import (
	utils "mergeklists/utils"
)

// Heap is a generic min-heap implementation
type Heap[T any] struct {
	data []T
	less func(a, b T) bool
}

// NewHeap creates a new heap with the given comparison function
func NewHeap[T any](less func(a, b T) bool) *Heap[T] {
	return &Heap[T]{less: less}
}

func (h *Heap[T]) Len() int { return len(h.data) }

func (h *Heap[T]) Push(x T) {
	h.data = append(h.data, x)
	h.up(len(h.data) - 1)
}

func (h *Heap[T]) Pop() T {
	n := len(h.data) - 1
	h.data[0], h.data[n] = h.data[n], h.data[0]
	h.down(0, n)
	x := h.data[n]
	h.data = h.data[:n]
	return x
}

func (h *Heap[T]) up(i int) {
	for {
		parent := (i - 1) / 2
		if i == 0 || !h.less(h.data[i], h.data[parent]) {
			break
		}
		h.data[i], h.data[parent] = h.data[parent], h.data[i]
		i = parent
	}
}

func (h *Heap[T]) down(i, n int) {
	for {
		left := 2*i + 1
		if left >= n {
			break
		}
		// Find the smaller child
		smallest := left
		if right := left + 1; right < n && h.less(h.data[right], h.data[left]) {
			smallest = right
		}
		if !h.less(h.data[smallest], h.data[i]) {
			break
		}
		h.data[i], h.data[smallest] = h.data[smallest], h.data[i]
		i = smallest
	}
}

func mergeKLists(lists []*utils.ListNode) *utils.ListNode {
	h := NewHeap(func(a, b *utils.ListNode) bool {
		return a.Val < b.Val
	})

	// Add the head of each non-nil list to the heap
	for _, node := range lists {
		if node != nil {
			h.Push(node)
		}
	}

	dummy := &utils.ListNode{}
	current := dummy

	for h.Len() > 0 {
		smallest := h.Pop() // No type assertion needed!
		current.Next = smallest
		current = current.Next

		if smallest.Next != nil {
			h.Push(smallest.Next)
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
