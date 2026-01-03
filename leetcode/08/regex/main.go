package main

import (
	"fmt"
	"regexp"
)

func isMatch(input string, regex string) bool {
	re, err := regexp.Compile("^" + regex + "$")
	if err != nil {
		return false
	}
	return re.MatchString(input)
}

func main() {
	fmt.Println(isMatch("aa", "a"))
	fmt.Println(isMatch("aa", "a*"))
	fmt.Println(isMatch("ab", ".*"))

	/*
		"^abcd .*pq .*rs .*        tu$"
		" abcd  Xpq  Xrs  XtuYpqrs tu
	*/
	fmt.Println(isMatch("abcdXpqXrsXtuYpqrstu", "abcd.*pq.*rs.*tu"))
}
