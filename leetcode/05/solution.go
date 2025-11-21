package main

import (
	"strings"
)

func longestPalindrome(s string) string {
	if len(s) < 1 {
		return ""
	}

	bestStart, bestEnd := 0, 0

	for i := 0; i < len(s); i++ {
		start, end := expandAroundCenter(s, i, i)   // Odd length palindromes
		if (end - start + 1 > bestEnd - bestStart + 1) {
			bestStart = start
			bestEnd = end
		}

		start, end = expandAroundCenter(s, i, i+1) // Even length palindromes
		if (end - start + 1 > bestEnd - bestStart+1) {
			bestStart = start
			bestEnd = end
		}
	}
	return s[bestStart : bestEnd+1]
}

func expandAroundCenter(s string, left, right int) (int, int) {
	for left >= 0 && right < len(s) && s[left] == s[right] {
		left--
		right++
	}
	return left + 1, right - 1
}

func main() {
	// Test cases from problem description
	testCases := []struct {
		input    string
		expected []string
	}{
		{"babad", []string{"bab", "aba"}},
		{"cbbd", []string{"bb"}},
		{"a", []string{"a"}},
		{"ac", []string{"a", "c"}},
		{"racecar", []string{"racecar"}},
		{"abcbab", []string{"abcba"}},
	}

	for _, tc := range testCases {
		result := longestPalindrome(tc.input)
		assert(contains(tc.expected, result), "Input: "+tc.input+" Expected one of: "+strings.Join(tc.expected, ",")+" Got: "+result)
	}
	println("All tests passed!")
}

func contains(s []string, e string) bool {
	for _, a := range s {
		if a == e {
			return true
		}
	}
	return false
}

func assert(condition bool, message string) {
	if !condition {
		panic("Assertion failed: " + message)
	}
}
