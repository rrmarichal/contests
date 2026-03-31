package main

import (
	"math"
)

var M = (uint32)(math.Pow(10, 9) + 7)

const (
	Present = 0
	Late    = 1
	Absent  = 2
)

func checkRecord(n int) int {
	T := make([][3]uint32, n+1)
	TP := make([][2]uint32, n+1)

	if n == 1 {
		return 3
	}
	if n == 2 {
		return 8
	}

	T[1][Present] = 1
	T[1][Late] = 1
	T[1][Absent] = 1
	T[2][Present] = 3
	T[2][Late] = 3
	T[2][Absent] = 2

	TP[1][Present] = 1
	TP[1][Late] = 1
	TP[2][Present] = 2
	TP[2][Late] = 2

	for i := 3; i <= n; i++ {
		T[i][Present] = (T[i-1][Present] + T[i-1][Late] + T[i-1][Absent]) % M
		T[i][Late] = (T[i-1][Present] + T[i-1][Absent] + T[i-2][Present] + T[i-2][Absent]) % M
		T[i][Absent] = (TP[i-1][Present] + TP[i-1][Late]) % M

		TP[i][Present] = (TP[i-1][Present] + TP[i-1][Late]) % M
		TP[i][Late] = (TP[i-1][Present] + TP[i-2][Present]) % M
	}

	return (int)((T[n][Present] + T[n][Late] + T[n][Absent]) % M)
}
