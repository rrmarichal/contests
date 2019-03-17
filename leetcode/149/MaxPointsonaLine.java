import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

public class MaxPointsonaLine {

	public static void main(String[] args) {
		Solution solution = new Solution();
		System.out.println(solution.maxPoints(new Point[] { 
            new Point(84, 250),
            new Point(0, 0),
            new Point(1, 0),
            new Point(0, -70),
            new Point(0, -70),
            new Point(1, -1),
            new Point(21, 10),
            new Point(42, 90),
            new Point(-42, 230)
		}));
	}

	static class Solution {
		
		public int maxPoints(Point[] points) {
			Arrays.sort(points, new Comparator<Point>() {
				@Override
				public int compare(Point o1, Point o2) {
					if (o1.x == o2.x)
						return o1.y - o2.y;
					return o1.x - o2.x;
				}
	        });
	        List<PointInfo> pi = new ArrayList<PointInfo>();
	        int j = 0;
	        while (j < points.length) {
	        	int k = j + 1;
	        	while (k < points.length && equal(points[j], points[k]))
	        		++k;
	        	pi.add(new PointInfo(points[j], k - j));
	        	j = k;
	        }
	        List<PointInfo> source = new ArrayList<PointInfo>(pi); 
			int best = Math.min(2, points.length);
			for (PointInfo pivot: source) {
				PointComparator pc = new PointComparator(pivot);
				try {
					Collections.sort(pi, pc);
					int max = maxOnLine(pivot, pi);
					if (max > best)
						best = max;
				}
				catch (Exception e) {}
			}
			return best;
		}
		
		private int maxOnLine(PointInfo pivot, List<PointInfo> points) {
			int j = 0, result = 0;
			while (j < points.size()) {
				if (points.get(j) != pivot) {
					int k = j + 1, online = pivot.count + points.get(j).count;
					while (k < points.size() && points.get(k) != pivot && cross(pivot.p, points.get(j).p, points.get(k).p) == 0) {
						online += points.get(k).count;
						++k;
					}
					if (online > result)
						result = online;
					j = k;
				} else j++;
			}
			return result;
		}

	    static boolean equal(Point point, Point point2) {
	    	return point.x == point2.x && point.y == point2.y;
		}

		static int cross(Point pivot, Point p1, Point p2) {
			return (p1.x - pivot.x) * (p2.y - pivot.y) - (p2.x - pivot.x) * (p1.y - pivot.y);
		}
		
		static class PointInfo {
	    	public PointInfo(Point point, int i) {
				p = point;
				count = i;
			}
			Point p;
	    	int count;
	    	
	    	@Override
	    	public String toString() {
	    		return String.format("[%d, %d](%d)", p.x, p.y, count);
	    	}
	    }
		
		static class PointComparator implements Comparator<PointInfo> {
			
			private PointInfo pivot;

			public PointComparator(PointInfo pivot) {
				this.pivot = pivot;
			}

			@Override
			public int compare(PointInfo o1, PointInfo o2) {
				int cp = cross(pivot.p, o1.p, o2.p);
				//System.out.print(String.format("%s %s %s: %d\n", pivot, o1, o2, cp));
				return (int) Math.signum(cp);
			}
			
		}
		
	}
	
	static class Solution3 {

		public int maxPoints(Point[] points) {
	        Arrays.sort(points, new Comparator<Point>() {
				@Override
				public int compare(Point o1, Point o2) {
					if (o1.x == o2.x)
						return o1.y - o2.y;
					return o1.x - o2.x;
				}
	        });
	        List<PointInfo> pi = new ArrayList();
	        int j = 0;
	        while (j < points.length) {
	        	int k = j + 1;
	        	while (k < points.length && equal(points[j], points[k]))
	        		++k;
	        	pi.add(new PointInfo(points[j], k - j));
	        	j = k;
	        }
	        Collections.sort(pi, new Comparator<PointInfo>() {
				@Override
				public int compare(PointInfo o1, PointInfo o2) {
					return o2.count - o1.count;
				}
	        });
	        int best = Math.min(points.length, Math.max(2, pi.size() > 0 ? pi.get(0).count : 0));
	        for (j = 0; j < pi.size() - 1; j++)
	            for (int k = j + 1; k < pi.size(); k++) {
	                int online = pi.get(j).count + pi.get(k).count;
	                for (int p = 0; p < pi.size(); p++)
	                    if (p != j && p != k && sameLine(pi.get(j).p, pi.get(k).p, pi.get(p).p))
	                        online += pi.get(p).count;
	                if (online > best)
	                    best = online;
	            }
	        return best;
	    }
	    
	    private boolean equal(Point point, Point point2) {
	    	 return point.x == point2.x && point.y == point2.y;
		}

		static class PointInfo {
	    	public PointInfo(Point point, int i) {
				p = point;
				count = i;
			}
			Point p;
	    	int count;
	    }
	    
	    boolean sameLine(Point p0, Point p1, Point p2) {
	        return (p1.x - p0.x) * (p2.y - p0.y) - (p2.x - p0.x) * (p1.y - p0.y) == 0;
	    }
	    
	}
	
	static class Point {
        int x;
        int y;
        Point() { x = 0; y = 0; }
        Point(int a, int b) { x = a; y = b; }
    }
    
}
