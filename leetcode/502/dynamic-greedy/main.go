package main

import (
	"slices"
)

type ProfitCapital struct {
	P int
	C int
}

func findMaximizedCapital(k int, w int, profits []int, capital []int) int {
	pc := make([]ProfitCapital, len(profits))
	for i := range profits {
		pc[i] = ProfitCapital{P: profits[i], C: capital[i]}
	}

	slices.SortFunc(pc, func(a, b ProfitCapital) int {
		return a.C - b.C
	})

	index := slices.IndexFunc(pc, func(pc ProfitCapital) bool {
		return pc.C > w
	})

	// can't complete no tasks
	if index == 0 {
		return w
	}

	// all tasks can be completed within the initial working capital
	if index == -1 {
		index = len(pc)
	}

	pool := NewHeap(pc[:index])
	for range k {
		if pool.size == 0 {
			break
		}
		w += pool.pop().P

		for index < len(pc) && pc[index].C <= w {
			pool.add(pc[index])
			index++
		}
	}

	return w
}
