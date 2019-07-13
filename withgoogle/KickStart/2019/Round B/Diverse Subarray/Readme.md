## Solutions overview

Define a subarray as two endpoints <code>[l, r]</code>, the left and the right endpoints respectively.

### AdHoc Search

For every posible left endpoint <code>l</code>, compute diversity of subarrays <code>[l, l], [l, l+1], ..., [l, r]</code>. On doing that we can implement an incremental counter that uses information of the previous subarray when computing the next.

The solution complexity is <code>O(n^2)</code> and it's expected to pass the first test set.

### Sliding Window Search

This solution is based on observing the relationship of diversities between subarrays <code>[l, r]</code> and subarrays <code>[l+1, r]</code>, for a fixed <code>l</code> and all posible values of <code>r</code>. Let <code>A<sub>l</sub></code> be the array element at position <code>l</code>, when computing diversities on subarrays <code>[l+1, r]</code>, changes occurr at:

- <code>[l, x]</code>, where <code>x</code> is the index of the <code>S-th</code> ocurrence of <code>A<sub>l</sub></code> starting from <code>l</code>.
- <code>[x+1, y]</code>, where <code>y</code> is the index of the <code>S+1-th</code> ocurrence of <code>A<sub>l</sub></code> starting from <code>l</code>.

Diversity of subarrays of the the first interval decrease by 1, the ones in the second increase by <code>S</code>.

To implement interval updates we use a Segment Tree data structure with lazy evaluation.

The solution complexity is <code>O(nlgn)</code> and it's expected to get full score.