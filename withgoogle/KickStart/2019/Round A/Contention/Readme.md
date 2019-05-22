## Solutions overview
---

Both solutions below are based on this very simple observation: **we can greedily chose the last segment to sell**. At each step <code>K = Q..1</code>, we chose the <code>Kth</code> segment to sell from the remaining set as the one that maximizes the seats sold, being this segment the last to attend.

### AdHocCounting

To count the active segments coverage we use a <code>TreeSet</code> data structure and a windowing technique over the sorted segments endpoints.

The time complexity for this implementation is <code>O(Q^2lgQ)</code> and it's expected to pass the first test set.

### LazySegmentTreeCounting

Use a Min/Max Lazy Segment Tree data structure to insert the intervals. Each node computes the min and max over how many requests are trying to book the seats under it. Every time a node goes to 1 (up or down), we must update the requests overlapping this segment accordingly. Use an Interval Tree data structure to compute the intersections.

### Official solution

Let us try to speed up the slow process of recalculating number of seats we can book at every step For every seat, let us denote the value of a seat as the number of remaining requests trying to book this seat. Everytime the value of a seat drops to 1, we increase the number of seats we can book for the corresponding request containing this seat booking.

Since requests are represented by an interval, we can use an interval tree to support the operations of removing an interval after the initial construction of the tree. Each node of the interval tree stores the set of intervals that cover it, and also the minimum value of any seat in it's range. Whenever a remove operation makes the minimum value become one, we walk down the tree to find the seats that became one, and walk back up the tree to find out which interval is the only interval that now covers that seat. We now set that seat's value to infinity so that we don't process it again. This happens only once per seat, for a total amortised cost of O(NlogN). In total this algorithm is O(Nlog(Q+N)), if you use a constant time set (like a hashset).
