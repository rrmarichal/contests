import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class MinimumHeightTrees {

	public static void main(String[] args) {
		Solution s = new Solution();
		int n = 3000;
		int[][] edges = new int[n][2];
		for (int j = 0; j < n-1; j++) {
			edges[j][0] = j;
			edges[j][1] = j+1;
		}
		for (int node : s.findMinHeightTrees(n, edges))
			System.out.println(node);
	}

	static class Solution {
		MaxPathInfo[] maxPathsInfo;
		boolean[] visited;
		int[] maxPath;
		HashMap<Integer, List<Integer>> tree;
		
	    public List<Integer> findMinHeightTrees(int n, int[][] edges) {
	    	if (n <= 1) return Arrays.asList(0);
	    	
	    	maxPathsInfo = new MaxPathInfo[n];
	    	visited = new boolean[n];
	    	buildTreeRepresentation(edges);
	    	findMaxPathToChildren(n / 2);
	    	
	    	maxPath = new int[n];
	    	visited = new boolean[n];
	    	findMaxPaths(n / 2, 0);
	    	
	    	int min = Integer.MAX_VALUE;
	    	for (int j = 0; j < n; j++)
	    		if (maxPath[j] < min)
	    			min = maxPath[j];
	    	List<Integer> result = new ArrayList<Integer>();
	    	for (int j = 0; j < n; j++)
	    		if (maxPath[j] == min)
	    			result.add(j);
	    	return result;
	    }

		private void findMaxPaths(int current, int max) {
			visited[current] = true;
			maxPath[current] = Math.max(max, maxPathsInfo[current].max.value);
			for (int child: tree.get(current)) {
				if (!visited[child]) {
					if (maxPathsInfo[current].max.node == child)
						findMaxPaths(child, Math.max(max + 1, 1 + maxPathsInfo[current].secondMax.value));
					else
						findMaxPaths(child, Math.max(max + 1, 1 + maxPathsInfo[current].max.value));
				}
			}
		}

		private MaxPathInfo findMaxPathToChildren(int current) {
			visited[current] = true;
			NodeInfo max = new NodeInfo(-1, 0), secondMax = new NodeInfo(-1, 0);
			for (int child : tree.get(current)) {
				if (!visited[child]) {
					MaxPathInfo childMax = findMaxPathToChildren(child);
					if (1 + childMax.max.value > max.value) {
						secondMax.value = max.value; 
						secondMax.node = max.node;
						max.value = 1 + childMax.max.value;
						max.node = child;
					}
					else
					if (1 + childMax.max.value > secondMax.value) {
						secondMax.value = 1 + childMax.max.value;
						secondMax.node = child;
					}
				}
			}
			maxPathsInfo[current] = new MaxPathInfo(max, secondMax);
			return maxPathsInfo[current];
		}

		private void buildTreeRepresentation(int[][] edges) {
			tree = new HashMap<Integer, List<Integer>>();
			for (int[] edge : edges) {
				if (!tree.containsKey(edge[0])) {
					tree.put(edge[0], new ArrayList<Integer>());
				}
				tree.get(edge[0]).add(edge[1]);
				if (!tree.containsKey(edge[1])) {
					tree.put(edge[1], new ArrayList<Integer>());
				}
				tree.get(edge[1]).add(edge[0]);
			}
		}
		
		static class MaxPathInfo {
			NodeInfo max, secondMax;
			
			public MaxPathInfo(NodeInfo max, NodeInfo secondMax) {
				this.max = max;
				this.secondMax = secondMax;
			}
		}
		
		static class NodeInfo {
			int node, value;
			
			public NodeInfo(int node, int value) {
				this.node = node;
				this.value = value;
			}
		}
    }
    
}
