package main

import "fmt"

func maxArea(height []int) int {
	var left int = 0
	var right int = len(height) - 1
	var best int = 0

	for left < right {
		best = max(best, (right-left)*min(height[left], height[right]))
		if height[left] < height[right] {
			left++
		} else {
			right--
		}
	}

	return best
}

func main() {
	fmt.Println(maxArea([]int{1, 8, 6, 2, 5, 4, 8, 3, 7}))
	fmt.Println(maxArea([]int{1, 1}))
}
