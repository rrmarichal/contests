package contests.ioi;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class PropertiesTests {

    @Test
    public void setsReverseTest0() {
        int[] X = new int[] { 1, 3, 5 };
        int[] Y = new int[] { 2, 10 };
        assertEquals(X.length * Y.length, Functions.g(X, Y, false) + Functions.g(Y, X, true));
    }

    @Test
    public void setsReverseTest1() {
        int[] X = new int[] { 1, 4, 9 };
        int[] Y = new int[] { 3, 8 };
        assertEquals(X.length * Y.length, Functions.g(X, Y, false) + Functions.g(Y, X, true));
    }

    @Test
    public void setsReverseTest2() {
        int[] X = new int[] { 7, 9, 3, 5, 1, 6, 4 };
        int[] Y = new int[] { 7, 9, 3, 5, 1, 6, 4 };
        assertEquals(X.length * Y.length, Functions.g(X, Y, false) + Functions.g(Y, X, true));
    }

    @Test
    public void setsReverseTest3() {
        int[] X = new int[] { 7, 9, 3, 5, 1, 6, 4 };
        int[] Y = new int[] { 10, 20, 9, 9 };
        assertEquals(X.length * Y.length, Functions.g(X, Y, false) + Functions.g(Y, X, true));
    }

    @Test
    public void mirrorSetTest0() {
        int[] X = new int[] {  1, 3, 8 };
        assertEquals(3, Functions.g(X, X, false));
        assertEquals(X.length * X.length - Functions.e(X, X), Functions.h(X, X));
        assertEquals(2 * Functions.g(X, X, false), Functions.h(X, X));
    }

    @Test
    public void mirrorSetTest1() {
        int[] X = new int[] {  1, 3, 8, 2, 5 };
        assertEquals(10, Functions.g(X, X, false));
        assertEquals(X.length * X.length - Functions.e(X, X), Functions.h(X, X));
        assertEquals(2 * Functions.g(X, X, false), Functions.h(X, X));
    }

    @Test
    public void mirrorSetTest2() {
        int[] X = new int[] {  1, 1, 1 };
        assertEquals(0, Functions.g(X, X, false));
        assertEquals(X.length * X.length - Functions.e(X, X), Functions.h(X, X));
        assertEquals(2 * Functions.g(X, X, false), Functions.h(X, X));
    }

    @Test
    public void mirrorSetTest3() {
        int[] X = new int[] {  1, 1, 2 };
        assertEquals(2, Functions.g(X, X, false));
        assertEquals(X.length * X.length - Functions.e(X, X), Functions.h(X, X));
        assertEquals(2 * Functions.g(X, X, false), Functions.h(X, X));
    }

    @Test
    public void mirrorSetTest4() {
        int[] X = new int[] {  1, 1, 2, 1, 2 };
        assertEquals(6, Functions.g(X, X, false));
        assertEquals(X.length * X.length - Functions.e(X, X), Functions.h(X, X));
        assertEquals(2 * Functions.g(X, X, false), Functions.h(X, X));
    }

    @Test
    public void mirrorSetTest5() {
        int[] X = new int[] { 3, 1, 1, 2, 1, 3, 2 };
        assertEquals(16, Functions.g(X, X, false));
        assertEquals(X.length * X.length - Functions.e(X, X), Functions.h(X, X));
        assertEquals(2 * Functions.g(X, X, false), Functions.h(X, X));
    }

    @Test
    public void mirrorSetTest6() {
        int[] X = new int[] { 1, 1, 1, 2, 2, 3, 3 };
        assertEquals(16, Functions.g(X, X, false));
        assertEquals(X.length * X.length - Functions.e(X, X), Functions.h(X, X));
        assertEquals(2 * Functions.g(X, X, false), Functions.h(X, X));
    }

    @Test
    public void equalityAllDifferentTest() {
        int[] X = new int[] { 1, 2, 3, 4, 5 };
        assertEquals(X.length, Functions.e(X, X));
    }

    @Test
    public void equalityAllEqualTest() {
        int[] X = new int[] { 1, 1, 1, 1, 1 };
        assertEquals(X.length * X.length, Functions.e(X, X));
    }

    @Test
    public void equalityInverseTest0() {
        int[] X = new int[] { 1, 8, 2 };
        int[] Y = new int[] { 3, 19 };
        assertEquals(5, Functions.d(X, Y));
    }

    @Test
    public void equalityInverseTest1() {
        int[] X = new int[] { 1, 2, 2 };
        int[] Y = new int[] { 3, 19 };
        assertEquals(7, Functions.d(X, Y));
    }

}
