## Chinese remainder theorem (CRT) (see Theorem 31.27 from **Introduction to algorithms, 3rd edition**).

Let <code>n = n<sub>1</sub>  n<sub>2</sub> $\dots$ n<sub>K</sub> </code> where the <code>n<sub>i</sub></code> are pairwise relatively prime. Let <code>V = (a<sub>1</sub>, a<sub>2</sub>, $\dots$, a<sub>K</sub>)</code>, where <code>a $\in$ Z<sub>n</sub></code>, <code>a<sub>i</sub> $\in$ Z<sub>n<sub>i</sub></sub></code>, and <code>a<sub>i</sub> = a mod n<sub>i</sub></code> for <code>i = 1,2, $\dots$, K</code>. Then the mapping from <code>a $\leftrightarrow$ V</code> is a one-to-one correspondence (bijection) between <code>Z<sub>n</sub></code> and the Cartesian product <code>Z<sub>n<sub>1</sub></sub> $\times$ Z<sub>n<sub>2</sub></sub> $\times$ $\dots$ $\times$ Z<sub>n<sub>K</sub></sub></code>. Operations performed on the elements of <code>Z<sub>n</sub></code> can be equivalently performed on the corresponding K-tuples by performing the operations independently in each coordinate position in the appropriate system. That is, if

<code>a $\leftrightarrow$ (a<sub>1</sub>, a<sub>2</sub>, $\dots$, a<sub>K</sub>)</code>,

<code>b $\leftrightarrow$ (b<sub>1</sub>, b<sub>2</sub>, $\dots$, b<sub>K</sub>)</code>,

then

<code>(a + b) (mod n) $\leftrightarrow$ ((a<sub>1</sub> + b<sub>1</sub>) (mod n<sub>1</sub>), $\dots$, (a<sub>K</sub> + b<sub>K</sub>) (mod n<sub>K</sub>))</code>

<code>(a - b) (mod n) $\leftrightarrow$ ((a<sub>1</sub> - b<sub>1</sub>) (mod n<sub>1</sub>), $\dots$, (a<sub>K</sub> - b<sub>K</sub>) (mod n<sub>K</sub>))</code>

<code>(ab) (mod n) $\leftrightarrow$ ((a<sub>1</sub>b<sub>1</sub>) (mod n<sub>1</sub>), $\dots$, (a<sub>K</sub>b<sub>K</sub>) (mod n<sub>K</sub>))</code>

### Problem

Find <code>x</code> such that <code>x $\equiv$ a<sub>i</sub> (mod n<sub>i</sub>)</code>.

Let <code>n = n<sub>1</sub>  n<sub>2</sub> $\dots$ n<sub>K</sub></code>, <code>m<sub>i</sub> = n/n<sub>i</sub></code> and <code>c<sub>i</sub> = m<sub>i</sub>(m<sub>i</sub><sup>-1</sup> mod n<sub>i</sub>)</code> then <code>x = (a<sub>1</sub>c<sub>1</sub> + a<sub>2</sub>c<sub>2</sub> + $\dots$ + a<sub>K</sub>c<sub>K</sub>) </code> is a solution to the system of linear modular equations modulo <code>n</code>.

### Input

Input contains <code>T</code>, the number of tests. For each test, the first line contains <code>K</code> (see above), then two lines, with <code>K</code> integers each, the values of <code>a<sub>i</sub></code> and <code>n<sub>i</sub></code>.

### Output

Output is the value <code>a</code> such that <code>a $\equiv$ a<sub>i</sub> (mod n<sub>i</sub>) </code> for <code>i = 1,2,$\dots$,K</code>.
