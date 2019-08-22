# Solutions overview

All solutions are based on the following observation: the optimal placement of the warehouse lies between <code>l=K/2</code> stalls to the left and <code>r=K-l</code> stalls to the right.

## DP search

For every possible placement <code>k</code> of the warehouse between spots <code>l</code> and <code>N-r-1</code> we find the optimal placement of <code>l</code> stalls to the left of <code>k</code> and <code>r</code> stalls to the right of <code>k</code>.

To do this, at each step we compute <code>L[i,j]</code> as the best placement for <code>i</code> stalls on the spots <code>[0..j]</code>.

In the same way we compute <code>R[i,j]</code> as the best placement for <code>i</code> stalls on the spots <code>[k+1..j]</code>.

Overall complexity is <code>O(N^3)</code> and it's expected to pass the first test set.

## Interval selection search

Let <code>[0..L]</code> and <code>[R..N]</code> be the spots intervals where stalls are placed, then the warehouse optimal position is always <code>min-index(C[L+1..R-1])</code>.

Instead of fixing the warehouse position, here we fix both <code>L</code> and <code>R</code> where left and right stalls will be placed. Using shift and optimal warehouse calculation we search the space in linear time for an overall complexity of <code>O(N^3lgN)</code> and it's expected to pass the first test set.

## Incremental search

Using the idea above, let <code>k</code> be the optimal location for the warehouse, then <code>C[k]</code> is the minimum over the interval <code>[k-LeftShift..k+RightShift]</code> and <code>LeftShift</code> and <code>RightShift</code> are maximal.

For each posible warehouse location <code>k</code> find the best sub-intervals for the stalls to the left and right of <code>k</code>. This information is computed for all posible left and right values in an incremental iteration using a Priority Queue with maximum capacity.

Overall complexity is <code>O(NlgN)</code> and it's expected to get full score.
