package contests.ioi;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class SHeapTests {

    @Test
    public void case0Test() {
        int[] A = new int[] { 7, 9, 3, 5, 1, 6, 4 };
        SHeap sheap = new SHeap(A);
        sheap.init(0, 3);
        assertEquals(4, sheap.size());

        assertEquals(3, sheap.extractMin().item);
        assertEquals(3, sheap.size());

        assertEquals(5, sheap.extractMin().item);
        assertEquals(2, sheap.size());

        assertEquals(7, sheap.extractMin().item);
        assertEquals(1, sheap.size());

        assertEquals(9, sheap.extractMin().item);
        assertEquals(0, sheap.size());
    }

    @Test
    public void case1Test() {
        int[] A = new int[] { 7, 9, 3, 5, 1, 6, 4 };
        SHeap sheap = new SHeap(A);
        sheap.init(2, 4);
        assertEquals(3, sheap.size());

        assertEquals(1, sheap.extractMin().item);
        assertEquals(2, sheap.size());

        assertEquals(3, sheap.extractMin().item);
        assertEquals(1, sheap.size());

        assertEquals(5, sheap.extractMin().item);
        assertEquals(0, sheap.size());
    }

    @Test
    public void case2Test() {
        int[] A = new int[] { 7, 9, 3, 5, 1, 6, 4 };
        SHeap sheap = new SHeap(A);
        sheap.init(0, 1);
        assertEquals(2, sheap.size());

        assertEquals(7, sheap.extractMin().item);
        assertEquals(1, sheap.size());

        assertEquals(9, sheap.extractMin().item);
        assertEquals(0, sheap.size());
    }

    @Test
    public void case3Test() {
        int[] A = new int[] { 7, 9, 3, 5, 1, 6, 4 };
        SHeap sheap = new SHeap(A);
        sheap.init(0, 6);
        assertEquals(7, sheap.size());

        assertEquals(1, sheap.extractMin().item);
        assertEquals(6, sheap.size());

        assertEquals(3, sheap.extractMin().item);
        assertEquals(5, sheap.size());

        assertEquals(4, sheap.extractMin().item);
        assertEquals(4, sheap.size());

        assertEquals(5, sheap.extractMin().item);
        assertEquals(3, sheap.size());

        assertEquals(6, sheap.extractMin().item);
        assertEquals(2, sheap.size());

        assertEquals(7, sheap.extractMin().item);
        assertEquals(1, sheap.size());

        assertEquals(9, sheap.extractMin().item);
        assertEquals(0, sheap.size());
    }

    @Test
    public void case4Test() {
        int[] A = new int[] { 7, 9, 3, 5, 1, 6, 4 };
        SHeap sheap = new SHeap(A);
        sheap.init(6, 6);
        assertEquals(1, sheap.size());

        assertEquals(4, sheap.extractMin().item);
        assertEquals(0, sheap.size());
    }

    @Test
    public void case5Test() {
        int[] A = new int[] { 1, 1, 1, 2, 2 };
        SHeap sheap = new SHeap(A);
        sheap.init(0, 4);
        assertEquals(5, sheap.size());

        HeapItem min = sheap.extractMin();
        assertEquals(1, min.item);
        assertEquals(0, min.index);
        assertEquals(4, sheap.size());

        min = sheap.extractMin();
        assertEquals(1, min.item);
        assertEquals(1, min.index);
        assertEquals(3, sheap.size());

        min = sheap.extractMin();
        assertEquals(1, min.item);
        assertEquals(2, min.index);
        assertEquals(2, sheap.size());

        min = sheap.extractMin();
        assertEquals(2, min.item);
        assertEquals(3, min.index);
        assertEquals(1, sheap.size());

        min = sheap.extractMin();
        assertEquals(2, min.item);
        assertEquals(4, min.index);
        assertEquals(0, sheap.size());
    }

    @Test
    public void case6Test() {
        int[] A = new int[] { 2, 1, 1 };
        SHeap sheap = new SHeap(A);
        sheap.init(0, 2);
        assertEquals(3, sheap.size());

        HeapItem min = sheap.extractMin();
        assertEquals(1, min.item);
        assertEquals(1, min.index);
        assertEquals(2, sheap.size());

        min = sheap.extractMin();
        assertEquals(1, min.item);
        assertEquals(2, min.index);
        assertEquals(1, sheap.size());

        min = sheap.extractMin();
        assertEquals(2, min.item);
        assertEquals(0, min.index);
        assertEquals(0, sheap.size());
    }

}
