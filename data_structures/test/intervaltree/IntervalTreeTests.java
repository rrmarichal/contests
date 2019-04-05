package intervaltree;

import static org.junit.Assert.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Random;

import org.junit.Ignore;
import org.junit.Test;

public class IntervalTreeTests {

    @Test
    public void testValidCreate() {
        IntervalTree.Interval[] intervals = new IntervalTree.Interval[] {
            new IntervalTree.Interval(0, 0, 10),
            new IntervalTree.Interval(1, 4, 12)
        };
        IntervalTree tree = IntervalTree.create(intervals);
        assertNotNull(tree);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCreateNullIntervalsList() {
        IntervalTree.create(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCreateNullIntervalArgument() {
        IntervalTree.create(new IntervalTree.Interval[] {
            new IntervalTree.Interval(0, 0, 10),
            null
        });
    }

    @Test(expected = IllegalArgumentException.class)
    public void testCreateDuplicateIDs() {
        IntervalTree.create(new IntervalTree.Interval[] {
            new IntervalTree.Interval(0, 0, 10),
            new IntervalTree.Interval(0, 1, 4)
        });
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddNullInterval() {
        IntervalTree.Interval[] intervals = new IntervalTree.Interval[] {
            new IntervalTree.Interval(0, 0, 10)
        };
        IntervalTree tree = IntervalTree.create(intervals);
        tree.add(null);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddInvalidRangeInterval() {
        IntervalTree.Interval[] intervals = new IntervalTree.Interval[] {
            new IntervalTree.Interval(0, 0, 10)
        };
        IntervalTree tree = IntervalTree.create(intervals);
        tree.add(new IntervalTree.Interval(1, 2, 1));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testAddDuplicateIDInterval() {
        IntervalTree.Interval[] intervals = new IntervalTree.Interval[] {
            new IntervalTree.Interval(0, 0, 10)
        };
        IntervalTree tree = IntervalTree.create(intervals);
        tree.add(new IntervalTree.Interval(0, 2, 4));
    }

    @Test
    public void testValidAdd() {
        IntervalTree.Interval[] intervals = new IntervalTree.Interval[] {
            new IntervalTree.Interval(0, 0, 10)
        };
        IntervalTree tree = IntervalTree.create(intervals);
        tree.add(new IntervalTree.Interval(1, 2, 14));
        assertEquals(2, tree.size());
    }

    @Test
    public void testMinOnEmptyTree() {
        IntervalTree.Interval[] intervals = new IntervalTree.Interval[] {};
        IntervalTree tree = IntervalTree.create(intervals);
        assertNull(tree.min());
    }

    @Test
    public void testMin() {
        IntervalTree.Interval[] intervals = new IntervalTree.Interval[] {
            new IntervalTree.Interval(0, 0, 10),
            new IntervalTree.Interval(1, -1, 20),
            new IntervalTree.Interval(2, 4, 6),
            new IntervalTree.Interval(3, 19, 20)
        };
        IntervalTree tree = IntervalTree.create(intervals);
        IntervalTree.Interval min = tree.min();
        assertEquals(1, min.id);
    }

    @Test
    public void testMaxOnEmptyTree() {
        IntervalTree.Interval[] intervals = new IntervalTree.Interval[] {};
        IntervalTree tree = IntervalTree.create(intervals);
        assertNull(tree.max());
    }

    @Test
    public void testMax() {
        IntervalTree.Interval[] intervals = new IntervalTree.Interval[] {
            new IntervalTree.Interval(0, 0, 10),
            new IntervalTree.Interval(1, -1, 20),
            new IntervalTree.Interval(2, 4, 6),
            new IntervalTree.Interval(3, 19, 20)
        };
        IntervalTree tree = IntervalTree.create(intervals);
        IntervalTree.Interval max = tree.max();
        assertEquals(3, max.id);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testRemoveNonExistingInterval() {
        IntervalTree.Interval[] intervals = new IntervalTree.Interval[] {
            new IntervalTree.Interval(0, 0, 10)
        };
        IntervalTree tree = IntervalTree.create(intervals);
        tree.remove(1);
    }

    @Test
    public void testRemove0() {
        IntervalTree.Interval[] intervals = new IntervalTree.Interval[] {
            new IntervalTree.Interval(0, 0, 10),
            new IntervalTree.Interval(1, 5, 17),
            new IntervalTree.Interval(2, 4, 12)
        };
        IntervalTree tree = IntervalTree.create(intervals);
        tree.remove(0);
        assertEquals(2, tree.size());
        assertEquals(2, tree.min().id);
        tree.remove(1);
        assertEquals(1, tree.size());
        assertEquals(2, tree.min().id);
        tree.remove(2);
        assertEquals(0, tree.size());
    }

    @Test
    public void testRemove1() {
        IntervalTree.Interval[] intervals = new IntervalTree.Interval[] {
            new IntervalTree.Interval(0, 0, 10),
            new IntervalTree.Interval(1, -1, 20),
            new IntervalTree.Interval(2, 4, 6),
            new IntervalTree.Interval(3, 19, 20)
        };
        IntervalTree tree = IntervalTree.create(intervals);
        assertEquals(1, tree.min().id);
        assertEquals(3, tree.max().id);

        tree.remove(1);
        assertEquals(0, tree.min().id);
        assertEquals(3, tree.max().id);

        tree.remove(3);
        assertEquals(0, tree.min().id);
        assertEquals(2, tree.max().id);
    }

    private IntervalTree.Interval findMin(ArrayList<IntervalTree.Interval> intervals) {
        IntervalTree.Interval min = null;
        for (int j = 0; j < intervals.size(); j++) {
            if (intervals.get(j) != null) {
                if (min == null) {
                    min = intervals.get(j);
                } else if (min.compareTo(intervals.get(j)) > 0) {
                    min = intervals.get(j);
                }
            }
        }
        return min;
    }

    private IntervalTree.Interval findMax(ArrayList<IntervalTree.Interval> intervals) {
        IntervalTree.Interval max = null;
        for (int j = 0; j < intervals.size(); j++) {
            if (intervals.get(j) != null) {
                if (max == null) {
                    max = intervals.get(j);
                } else if (max.compareTo(intervals.get(j)) < 0) {
                    max = intervals.get(j);
                }
            }
        }
        return max;
    }

    @Test
    public void randomAddRemoveTest() {
        final int INTERVALS_COUNT = 100, OPERATIONS = 1000;
        Random random = new Random();
        IntervalTree.Interval[] intervalsArray = new IntervalTree.Interval[INTERVALS_COUNT];
        ArrayList<IntervalTree.Interval> intervalsList = new ArrayList<IntervalTree.Interval>();
        for (int j = 0; j < INTERVALS_COUNT; j++) {
            int x = random.nextInt();
            int y = random.nextInt();
            intervalsArray[j] = new IntervalTree.Interval(j, Math.min(x, y), Math.max(x, y));
            intervalsList.add(intervalsArray[j]);
        }
        IntervalTree tree = IntervalTree.create(intervalsArray);
        int activeCount = INTERVALS_COUNT;
        for (int j = 0; j < OPERATIONS; j++) {
            // Add
            if (random.nextBoolean()) {
                int x = random.nextInt();
                int y = random.nextInt();
                intervalsList.add(new IntervalTree.Interval(intervalsList.size(), Math.min(x, y), Math.max(x, y)));
                tree.add(intervalsList.get(intervalsList.size() - 1));
                activeCount++;
            }
            // Remove
            else {
                int r = random.nextInt(activeCount), k = -1;
                while (r >= 0) {
                    if (intervalsList.get(++k) != null) {
                        r--;
                    }
                }
                tree.remove(intervalsList.get(k).id);
                intervalsList.set(k, null);
                activeCount--;
            }
            IntervalTree.Interval tmin = tree.min(), tmax = tree.max();
            IntervalTree.Interval bmin = findMin(intervalsList), bmax = findMax(intervalsList);

            assertEquals(bmin, tmin);
            assertEquals(bmax, tmax);
        }
    }

    @Test
    public void testIntercept0() {
        IntervalTree.Interval[] intervals = new IntervalTree.Interval[] { new IntervalTree.Interval(0, 0, 10),
                new IntervalTree.Interval(1, -1, 20), new IntervalTree.Interval(2, 4, 6),
                new IntervalTree.Interval(3, 19, 20) };
        IntervalTree tree = IntervalTree.create(intervals);
        Collection<IntervalTree.Interval> intersection = tree.intersection(5, 18);
        assertEquals(3, intersection.size());
    }

    @Test
    public void testIntercept1() {
        IntervalTree.Interval[] intervals = new IntervalTree.Interval[] { new IntervalTree.Interval(0, 0, 10),
                new IntervalTree.Interval(1, -1, 20), new IntervalTree.Interval(2, 4, 6),
                new IntervalTree.Interval(3, 19, 20) };
        IntervalTree tree = IntervalTree.create(intervals);
        Collection<IntervalTree.Interval> intersection = tree.intersection(Integer.MIN_VALUE, Integer.MAX_VALUE);
        assertEquals(tree.size(), intersection.size());
    }

    @Test
    public void testIntercept2() {
        IntervalTree.Interval[] intervals = new IntervalTree.Interval[] { new IntervalTree.Interval(0, 0, 10),
                new IntervalTree.Interval(1, -1, 20), new IntervalTree.Interval(2, 4, 6),
                new IntervalTree.Interval(3, 19, 20) };
        IntervalTree tree = IntervalTree.create(intervals);
        Collection<IntervalTree.Interval> intersection = tree.intersection(Integer.MIN_VALUE, -20);
        assertEquals(0, intersection.size());
    }

    @Test
    public void testIntercept3() {
        IntervalTree.Interval[] intervals = new IntervalTree.Interval[] { new IntervalTree.Interval(0, 0, 10),
                new IntervalTree.Interval(1, 4, 12), };
        IntervalTree tree = IntervalTree.create(intervals);
        Collection<IntervalTree.Interval> intersection = tree.intersection(-13, -5);
        assertEquals(0, intersection.size());
    }

    @Test
    public void testIntercept4() {
        IntervalTree.Interval[] intervals = new IntervalTree.Interval[] {
            new IntervalTree.Interval(0, 0, 10),
            new IntervalTree.Interval(1, 5, 17),
            new IntervalTree.Interval(2, 4, 12)
        };
        IntervalTree tree = IntervalTree.create(intervals);
        Collection<IntervalTree.Interval> intersection = tree.intersection(-1, 25);
        assertEquals(3, intersection.size());
    }

    @Test
    public void testIntercept5() {
        IntervalTree.Interval[] intervals = new IntervalTree.Interval[] {
            new IntervalTree.Interval(0, 0, 10),
            new IntervalTree.Interval(1, 5, 17),
            new IntervalTree.Interval(2, 4, 12)
        };
        IntervalTree tree = IntervalTree.create(intervals);
        Collection<IntervalTree.Interval> intersection = tree.intersection(6, 7);
        assertEquals(3, intersection.size());
    }

    @Test
    public void testIntercept6() {
        IntervalTree.Interval[] intervals = new IntervalTree.Interval[] {
            new IntervalTree.Interval(0, 1, 4),
            new IntervalTree.Interval(1, 1, 3),
            new IntervalTree.Interval(2, 6, 6),
            new IntervalTree.Interval(3, 1, 1),
            new IntervalTree.Interval(4, 2, 7)
        };
        IntervalTree tree = IntervalTree.create(intervals);
        Collection<IntervalTree.Interval> intersection = tree.intersection(4, 6);
        assertEquals(3, intersection.size());
    }

    private boolean _intercept(IntervalTree.Interval interval, int left, int right) {
        return !(left > interval.right || right < interval.left);
    }

    private Collection<IntervalTree.Interval> intersection(IntervalTree.Interval[] intervalsArray, int left, int right) {
        Collection<IntervalTree.Interval> result = new ArrayList<IntervalTree.Interval>();
        for (int j = 0; j < intervalsArray.length; j++) {
            if (_intercept(intervalsArray[j], left, right)) {
                result.add(intervalsArray[j]);
            }
        }
        return result;
    }

    @Test
    public void testRandomIntersections() {
        final int INTERVALS_COUNT = 1000, OPERATIONS = 1000;
        Random random = new Random();
        IntervalTree.Interval[] intervalsArray = new IntervalTree.Interval[INTERVALS_COUNT];
        for (int j = 0; j < INTERVALS_COUNT; j++) {
            int x = random.nextInt();
            int y = random.nextInt();
            intervalsArray[j] = new IntervalTree.Interval(j, Math.min(x, y), Math.max(x, y));
        }
        IntervalTree tree = IntervalTree.create(intervalsArray);
        for (int j = 0; j < OPERATIONS; j++) {
            int x = random.nextInt();
            int y = random.nextInt();

            Collection<IntervalTree.Interval> tintersection = tree.intersection(Math.min(x, y), Math.max(x, y));
            Collection<IntervalTree.Interval> bintersection = intersection(intervalsArray, Math.min(x, y), Math.max(x, y));

            assertEquals(bintersection.size(), tintersection.size());
        }
    }

    private Collection<IntervalTree.Interval> intersection(ArrayList<IntervalTree.Interval> intervalsList, int left, int right) {
        Collection<IntervalTree.Interval> result = new ArrayList<IntervalTree.Interval>();
        for (int j = 0; j < intervalsList.size(); j++) {
            if (intervalsList.get(j) != null && _intercept(intervalsList.get(j), left, right)) {
                result.add(intervalsList.get(j));
            }
        }
        return result;
    }

    @Test
    @Ignore("Performance test")
    public void randomAddRemoveIntersectionTest() {
        final int INTERVALS_COUNT = 30000, OPERATIONS = 30000;
        Random random = new Random();
        IntervalTree.Interval[] intervalsArray = new IntervalTree.Interval[INTERVALS_COUNT];
        ArrayList<IntervalTree.Interval> intervalsList = new ArrayList<IntervalTree.Interval>();
        for (int j = 0; j < INTERVALS_COUNT; j++) {
            int x = random.nextInt();
            int y = random.nextInt();
            intervalsArray[j] = new IntervalTree.Interval(j, Math.min(x, y), Math.max(x, y));
            intervalsList.add(intervalsArray[j]);
        }
        IntervalTree tree = IntervalTree.create(intervalsArray);
        int activeCount = INTERVALS_COUNT;
        long intersectionsTotal = 0;
        for (int j = 0; j < OPERATIONS; j++) {
            // Add
            if (random.nextBoolean()) {
                int x = random.nextInt();
                int y = random.nextInt();
                intervalsList.add(new IntervalTree.Interval(intervalsList.size(), Math.min(x, y), Math.max(x, y)));
                tree.add(intervalsList.get(intervalsList.size() - 1));
                activeCount++;
            }
            // Remove
            else {
                int r = random.nextInt(activeCount), k = -1;
                while (r >= 0) {
                    if (intervalsList.get(++k) != null) {
                        r--;
                    }
                }
                tree.remove(intervalsList.get(k).id);
                intervalsList.set(k, null);
                activeCount--;
            }
            // Test intersection after add/removal.
            int x = random.nextInt();
            int y = random.nextInt();

            Collection<IntervalTree.Interval> tintersection = tree.intersection(Math.min(x, y), Math.max(x, y));
            Collection<IntervalTree.Interval> bintersection = intersection(intervalsList, Math.min(x, y), Math.max(x, y));
            intersectionsTotal += tintersection.size();

            assertEquals(bintersection.size(), tintersection.size());
        }
        System.out.println(String.format("Average intersection size: %d", intersectionsTotal / OPERATIONS));
    }

}
