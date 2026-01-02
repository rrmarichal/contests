package utils

import "fmt"

type ListNode struct {
	Val  int
	Next *ListNode
}

func PrintList(list *ListNode) {
	if list == nil {
		fmt.Print("<nil>")
	}

	for list != nil {
		fmt.Print(list.Val, " ")
		list = list.Next
	}
	fmt.Println()
}
