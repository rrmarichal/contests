package main

type MaxHeap struct {
	size int
	heap []ProfitCapital
}

func NewHeap(list []ProfitCapital) *MaxHeap {
	result := MaxHeap{size: 0, heap: []ProfitCapital{}}
	for _, pc := range list {
		result.add(pc)
	}
	return &result
}

func (h *MaxHeap) pop() ProfitCapital {
	max := h.heap[0]

	h.swap(0, h.size-1)
	h.size--
	h.down(0)

	return max
}

func (h *MaxHeap) up(index int) {
	if index == 0 {
		return
	}

	parent := (index - 1) / 2
	if h.heap[index].P > h.heap[parent].P {
		h.swap(index, parent)
		h.up(parent)
	}
}

func (h *MaxHeap) down(index int) {
	lci := 2*index + 1
	if lci >= h.size {
		return
	}
	max := lci
	rci := 2*index + 2
	if rci < h.size && h.heap[rci].P > h.heap[max].P {
		max = rci
	}

	if h.heap[max].P > h.heap[index].P {
		h.swap(index, max)
		h.down(max)
	}
}

func (h *MaxHeap) add(pc ProfitCapital) {
	if len(h.heap) == h.size {
		h.heap = append(h.heap, pc)
	} else {
		h.heap[h.size] = pc
	}
	h.size++

	h.up(h.size - 1)
}

func (h *MaxHeap) swap(x, y int) {
	h.heap[x], h.heap[y] = h.heap[y], h.heap[x]
}
