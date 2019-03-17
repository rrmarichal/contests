import java.util.*;

public class Solution {
    
    static class PointComparator implements Comparator<int[]> {

        public int compare(int[] b0, int[] b1) {
            if (b0[1] < b1[1])
                return -1;
            if (b0[1] > b1[1])
                return 1;
            return b0[0] - b1[0];
        }

    }

	public static void main(String[] args) {
		Solution s = new Solution();
		System.out.println(s.findMinArrowShots(new int[][] { {10,16}, {2,8}, {1,6}, {7,12} } ));
		System.out.println(s.findMinArrowShots(new int[][] { {0, 10}, {11, 15}, {-1, 0} } ));
	}

    public int findMinArrowShots(int[][] points) {
        Arrays.sort(points, new PointComparator());
        int pivot = 0, result = 0;
        while (pivot < points.length) {
        	int j = pivot + 1;
	        while (j < points.length && points[j][0] <= points[pivot][1])
	        	j++;
	        pivot = j;
	        result++;
        }
        return result;
    }

}
