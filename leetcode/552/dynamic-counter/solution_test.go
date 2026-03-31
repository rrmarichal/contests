package main

import (
	"testing"

	"github.com/stretchr/testify/assert"
)

func TestSample(t *testing.T) {
	// "PP", "AP", "LP"
	// "PL", "AL", "LL"
	// "PA", "LA"
	assert.Equal(t, 8, checkRecord(2))
	assert.Equal(t, 3, checkRecord(1))
	assert.Equal(t, 19, checkRecord(3))
	assert.Equal(t, 183236316, checkRecord(10101))
}
