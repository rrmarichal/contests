## Solutions overview
---

### AdHocCountingWithTreeSet
Search for the best last segment to sell. We iterate over all segments to find which is the segment that, being the last in the list of sales, provides the highest number of seats sold. Doing this <code>Q</code> times we can find the optimal order of the segments (in reverse). At each step update the max/min value accordingly.

To count the active segments coverage we use a <code>TreeSet</code> data structure and a windowing technique over the sorted segments endpoints.

The time complexity for this implementation is <code>O(Q^2lgQ)</code> and it's expected to pass the first test set.
