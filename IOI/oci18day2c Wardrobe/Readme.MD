## Solutions overview
---

- ### BruteForceSearch

This was used to test randomly generated tests cases. You can view the optimal distribution on each case.

- ### DPExpansion

Second step towards the solution is to come up with a Dynamic Programming approach to the calculation. Define <code>T<sub>j</sub></code> to be the best distribution of the elements from <code>1</code> to <code>j</code>. Let <code>A</code> be the list of elements (T-shirt stacks) in the input, then

<pre><code>T<sub>j</sub> = max(GCD(A[j-k+1..j]) + T<sub>j-k</sub>)</code></pre>

for all valid <code>k</code>. That is to consider all possible segments of stacks ending up at <code>j</code> having length <code>k</code>.

The running time for this solution is <code>O(MD)</code> since at each element we must expand <code>2D</code> times.

- ### LgNExpansion

This solution is a follow up to the previous one after making one observation. Consider a list <code>L</code> of <code>K</code> elements. Let <code>G<sub>k</sub></code> be equal to <code>GCD(L[1..k])</code> for <code>k</code> in <code>[1..K]</code>. Then the set <code>S = {G<sub>1</sub>, ..., G<sub>K</sub>}</code> contains at most <code>lgK</code> elements.

In words, after expanding the GCD function <code>K</code> times, we can get at most <code>lgK</code> different values. To see this, every time the GCD changes, it does it by a value that is at most half the previos value.

Using this observation and using the DP approach above, to calculate <code>T<sub>j</sub></code> we only consider <code>lg(D)</code> values of <code>k</code> in the recursive definition of <code>T</code>.

At each step <code>j</code> we expand the list of GCDs for segments <code>A[j-1..j], A[j-2..j], ..., A[1..j]</code> keeping a linked list that "compresses" those unique values and stores the ranges they cover.

To calculate <code>T<sub>j</sub></code> we use the <code>update_table</code> method. For each different value of a GCD we need to get the maximum value of <code>T</code> in that range. To do that we use a <code>Segment Tree</code> that we update at every step with the calculated <code>T<sub>j</sub></code> in the <code>update_tree</code> method.

The running time for this solution is <code>O(Mlg<sup>2</sup>D)</code> and is expected to receive full score.
