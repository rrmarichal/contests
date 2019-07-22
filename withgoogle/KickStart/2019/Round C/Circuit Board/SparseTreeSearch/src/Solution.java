import java.io.*;
import java.util.*;

class InputReader {
    private static final int BUFFER_SIZE = 1<<16;

    private BufferedReader reader;
    private StringTokenizer tokenizer;

    public InputReader(InputStream stream) {
        reader = new BufferedReader(new InputStreamReader(stream), BUFFER_SIZE);
    }

    private boolean ensureTokenizer() {
        if (tokenizer == null || !tokenizer.hasMoreTokens()) {
            String line = nextLine();
            if (line != null) {
                tokenizer = new StringTokenizer(line);
            }
        }
        return tokenizer != null && tokenizer.hasMoreElements();
    }

    public String nextLine() {
        try {
            tokenizer = null;
            return reader.readLine();
        }
        catch (IOException e) {
            return null;
        }
    }

    public String nextToken() {
        if (ensureTokenizer()) {
            return tokenizer.nextToken();
        }
        return null;
    }

    public Integer nextInt() {
        if (ensureTokenizer()) {
            return Integer.parseInt(tokenizer.nextToken());
        }
        return null;
    }

    public Long nextLong() {
        if (ensureTokenizer()) {
            return Long.parseLong(tokenizer.nextToken());
        }
        return null;
    }
}

interface SparseTreeOperation {
	NodeInfo aggregate(NodeInfo nodeInfo, NodeInfo nodeInfo2);
}

class MinOperation implements SparseTreeOperation {
	@Override
	public NodeInfo aggregate(NodeInfo left, NodeInfo right) {
		if (left.value < right.value) {
			return left;
		}
		return right;
	}
}

class MaxOperation implements SparseTreeOperation {
	@Override
	public NodeInfo aggregate(NodeInfo left, NodeInfo right) {
		if (left.value >= right.value) {
			return left;
		}
		return right;
	}
}

class NodeInfo implements Comparable<NodeInfo> {
	int value, index;

	public NodeInfo(int value, int index) {
		this.value = value;
		this.index = index;
	}

	@Override
	public int compareTo(NodeInfo o) {
		return Integer.compare(index, o.index);
	}

	@Override
	public boolean equals(Object other) {
		return other instanceof NodeInfo && compareTo((NodeInfo) other) == 0;
	}
}

class SparseTree {

    private static final boolean CHECKED = true;

	public static SparseTree create(List<Integer> values, SparseTreeOperation operation) {
		if (CHECKED && values == null) {
            throw new IllegalArgumentException("Invalid argument (values is null).");
        }
        if (CHECKED && operation == null) {
            throw new IllegalArgumentException("Invalid argument (operation is null).");
        }
        return new SparseTree(values, operation);
	}

	private int size;
	private List<List<NodeInfo>> tree;
	private SparseTreeOperation operation;
	
	private SparseTree(List<Integer> values, SparseTreeOperation operation) {
        this.operation = operation;
		this.size = values.size();
		_buildTree(values);
	}

	private void _buildTree(List<Integer> list) {
		tree = new ArrayList<List<NodeInfo>>(size);
		for (int j = 0; j < size; j++) {
			tree.add(new ArrayList<NodeInfo>());
			tree.get(j).add(new NodeInfo(list.get(j), j));
		}
		for (int k = 1; (1 << k) <= size; k++) {
			tree.add(new ArrayList<NodeInfo>());
			for (int j = 0; j + (1 << k) <= size; j++) {
				tree.get(j).add(operation.aggregate(
					tree.get(j).get(k - 1),
					tree.get(j + (1 << (k - 1))).get(k - 1)
				));
			}
		}
	}

	private NodeInfo _query(int l, int r) {
		int k = (int) (Math.log(r - l + 1) / Math.log(2));
		NodeInfo x = tree.get(l).get(k);
		NodeInfo y = tree.get(r - (1 << k) + 1).get(k);
		return operation.aggregate(x, y);
	}
	
	public NodeInfo query(int l, int r) {
		if (CHECKED && l > r) {
			throw new IllegalArgumentException("l > r.");
		}
		if (CHECKED && l < 0) {
			throw new IllegalArgumentException("l < 0.");
		}
		if (CHECKED && r >= size) {
			throw new IllegalArgumentException("r >= size.");
		}
		return _query(l, r);
	}
	
}

class TestInfo {
	int R, C, K;
	Integer[][] T;

	public TestInfo(int R, int C, int K, Integer[][] T) {
		this.R = R;
		this.C = C;
		this.K = K;
		this.T = T;
	}
}

public class Solution {

    private int T;
    private TestInfo[] tests;

    protected Solution(InputReader in) {
        T = in.nextInt();
        tests = new TestInfo[T];
        for (int t = 0; t < T; t++) {
			int R = in.nextInt(), C = in.nextInt(), K = in.nextInt();
			Integer[][] T = new Integer[R][C];
			for (int j = 0; j < R; j++)
				for (int k = 0; k < C; k++) {
					T[j][k] = in.nextInt();
				}
            tests[t] = new TestInfo(R, C, K, T);
        }
    }

	private int _search(SparseTree tree, int l, int h) {
		if (l > h) {
			return 0;
		}
		NodeInfo min = tree.query(l, h);
		int max = (h - l + 1) * min.value;
		max = Math.max(max, _search(tree, l, min.index - 1));
		max = Math.max(max, _search(tree, min.index + 1, h));
		return max;
	}

	private int _solve(TestInfo test) {
		SparseTree[] rowsMin = new SparseTree[test.R];
		SparseTree[] rowsMax = new SparseTree[test.R];
		MinOperation minOp = new MinOperation();
		for (int j = 0; j < test.R; j++) {
			List<Integer> row = Arrays.asList(test.T[j]);
			rowsMin[j] = SparseTree.create(row, minOp);
			rowsMax[j] = SparseTree.create(row, new MaxOperation());
		}
		List<Integer> runs = new ArrayList<Integer>(test.R);
		for (int j = 0; j < test.R; j++) {
			int min = test.T[j][0], max = min, k = 1;
			for (; k < test.C; k++) {
				if (test.T[j][k] < min) {
					min = test.T[j][k];
				}
				if (test.T[j][k] > max) {
					max = test.T[j][k];
				}
				if (max - min > test.K) {
					break;
				}
			}
			runs.add(k);
		}
		int max = 0;
		for (int j = 0; j < test.C; j++) {
			SparseTree tree = SparseTree.create(runs, minOp);
			int best = _search(tree, 0, test.R - 1);
			if (best > max) {
				max = best;
			}
			for (int k = 0; k < test.R; k++) {
				int run = runs.get(k) - 1;
				if (run == 0) {
					run++;
				}
				if (j + run < test.C) {
					int mn = rowsMin[k].query(j + 1, j + run).value;
					int mx = rowsMax[k].query(j + 1, j + run).value;
					for (; j + run + 1 < test.C; run++) {
						if (test.T[k][j + run + 1] < mn) {
							mn = test.T[k][j + run + 1];
						}
						if (test.T[k][j + run + 1] > mx) {
							mx = test.T[k][j + run + 1];
						}
						if (mx - mn > test.K) {
							break;
						}
					}
				}
				runs.set(k, run);
			}
		}
		return max;
	}

    public String[] solve() {
        String[] result = new String[T];
        for (int t = 0; t < T; t++) {
            result[t] = String.format("Case #%d: %d", t + 1, _solve(tests[t]));
        }
        return result;
    }

    public static void main(String[] args) {
        InputReader in = new InputReader(System.in);
        PrintWriter out = new PrintWriter(System.out);
        Solution solution = new Solution(in);
        String[] result = solution.solve();
        for (String c: result) {
            out.println(c);
        }
        out.close();
    }

}
