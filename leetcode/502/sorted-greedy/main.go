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
		if a.P != b.P {
			return b.P - a.P
		}
		return a.C - b.C
	})

	taken := make([]bool, len(profits))
	for range k {
		for i, p := range pc {
			if taken[i] {
				continue
			}

			if p.C <= w {
				w += p.P
				taken[i] = true
				break
			}
		}
	}

	return w
}
