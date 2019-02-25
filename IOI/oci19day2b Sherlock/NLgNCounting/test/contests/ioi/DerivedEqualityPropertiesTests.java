package contests.ioi;

import static org.junit.Assert.assertEquals;

import org.junit.Before;
import org.junit.Test;
import static contests.ioi.Functions.*;

public class DerivedEqualityPropertiesTests {

    private int[] T, A, B, C, AB, BC;

    @Before
    public void init() {
        // T = new int[] { 7, 9, 3, 5, 1, 6, 4 };
        T = new int[] { 7, 9, 3, 5, 1, 6, 4, 5, 7, 7, 1, 1 };
        int al = 3, bl = 6, cl = T.length - al - bl;
        A = new int[al];
        B = new int[bl];
        C = new int[cl];
        AB = new int[al + bl];
        BC = new int[bl + cl];
        System.arraycopy(T, 0, A, 0, al);
        System.arraycopy(T, al, B, 0, bl);
        System.arraycopy(T, al + bl, C, 0, cl);
        System.arraycopy(T, 0, AB, 0, al + bl);
        System.arraycopy(T, al, BC, 0, bl + cl);
    }

    @Test
    public void copiesTest() {
        assertEquals(A[0], T[0]);
        assertEquals(B[B.length - 1], T[A.length + B.length - 1]);
        assertEquals(C[0], T[A.length + B.length]);
        assertEquals(AB[A.length + B.length - 1], T[A.length + B.length - 1]);
        assertEquals(BC[B.length + C.length - 1], T[T.length - 1]);
    }

    /**
     * f1: e(BC, BC) = e(B, B) + 2*e(B, C) + e(C, C)
     */
    @Test
    public void f1Test() {
        long left = e(BC, BC);
        long right = e(B, B) + 2*e(B, C) + e(C, C);
        assertEquals(left, right);
    }

    /**
     * f2: e(AB, AB) = e(A, A) + 2*e(A, B) + e(B, B)
     */
    @Test
    public void f2Test() {
        long left = e(AB, AB);
        long right = e(A, A) + 2*e(A, B) + e(B, B);
        assertEquals(left, right);
    }

    /**
     * f3(f1 - f2): e(BC, BC) - e(AB, AB) = e(C, C) - e(A, A) + 2*e(B, C) - 2*e(A, B)
     */
    @Test
    public void f3Test() {
        long left = e(BC, BC) - e(AB, AB);
        long right = e(C, C) - e(A, A) + 2*e(B, C) - 2*e(A, B);
        assertEquals(left, right);
    }

    /**
     * f4: e(T, T) = e(ABC, T) = e(B, T) + e(A, T) + e(C, T) = e(A, B) + e(B, B) + e(B, C) + e(A, T) + e(C, T)
     */
    @Test
    public void f4Test() {
        long left = e(T, T);
        long right = e(B, A) + e(B, B) + e(B, C) + e(A, T) + e(C, T);
        assertEquals(left, right);
    }

    /**
     * f5: e(AB, C) - e(A, BC) = e(B, C) - e(A, B)
     */
    @Test
    public void f5Test() {
        long left = e(AB, C) - e(A, BC);
        long right = e(B, C) - e(A, B);
        assertEquals(left, right);
    }

    /**
     * f6(f3 + f4): e(T, T) + e(BC, BC) - e(AB, AB) = e(C, C) - e(A, A) + 3*e(B, C) - e(A, B) + e(B, B) + e(A, T) + e(C, T)
     */
    @Test
    public void f6Test() {
        long left = e(T, T) + e(BC, BC) - e(AB, AB);
        long right = e(C, C) - e(A, A) + 3*e(B, C) - e(A, B) + e(B, B) + e(A, T) + e(C, T);
        assertEquals(left, right);
    }

    /**
     * f9: e(A, B) = e(B, T) - e(B, B) - e(B, C) = e(B, T) - e(B, BC)
     */
    @Test
    public void f9Test() {
        long left = e(A, B);
        long right = e(B, T) - e(B, B) - e(B, C);
        long rright = e(B, T) - e(B, BC);
        assertEquals(left, right);
        assertEquals(left, rright);
    }

    /**
     * f10: e(T, T) = 2*(e(AB, C) + e(A, BC) - e(A, C)) + e(A, A) + e(B, B) + e(C, C)
     */
    @Test
    public void f11Test() {
        long left = e(T, T);
        long right = 2*(e(AB, C) + e(A, BC) - e(A, C)) + e(A, A) + e(B, B) + e(C, C);
        assertEquals(left, right);
    }

    @Test
    public void equalityInverseTest2() {
        long left = e(B, T) + e(A, T);
        long right = e(AB, AB) + e(AB, C);
        assertEquals(left, right);
    }

    /**
     * f(x) = g(x, x) - f(~x)
     */
    @Test
    public void fgRelationshipTest0() {
        int[] X = new int[] { 3, 2, 1 };
        int[] _X = reverse(X);
        long left = f(X);
        long right = g(X, X, false) - f(_X);
        assertEquals(left, right);
    }

    @Test
    public void fgRelationshipTest1() {
        int[] X = new int[] { 4, 6, 2, 2, 3 };
        int[] _X = reverse(X);
        long left = f(X);
        long right = g(X, X, false) - f(_X);
        assertEquals(left, right);
    }

    @Test
    public void fgRelationshipTest2() {
        int[] X = new int[] { 7, 9, 3, 5, 1, 6, 4 };
        int[] _X = reverse(X);
        long left = f(X);
        long right = g(X, X, false) - f(_X);
        assertEquals(left, right);
    }

    @Test
    public void fgRelationshipTest3() {
        int[] X = new int[] { 7, 9, 3, 5, 1, 6, 4, 5, 7, 7, 1, 1 };
        int[] _X = reverse(X);
        long left = f(X);
        long right = g(X, X, false) - f(_X);
        assertEquals(left, right);
    }

    /**
     * f12: g(B, B) = f(AB) - g(A, A) - h(A, B) + f(~AB)
     */
    @Test
    public void f12Test() {
        int[] _AB = reverse(AB);
        long left = g(B, B, false);
        long right = f(AB) - g(A, A, false) - h(A, B) + f(_AB);
        assertEquals(left, right);
    }

    /**
     * g(B, B) = g(B, ~B)
     */
    @Test
    public void greflexiveTest0() {
        int[] _B = reverse(B);
        long left = g(B, B, false);
        long right = g(B, _B, false);
        assertEquals(left, right);
    }

    /**
     * f(x) + f`(x) + e(x) = x*(x-1)/2
     */
    @Test
    public void inversesTest0() {
        long left = f(T) + f_1(T) + e(T);
        long right = T.length * (T.length - 1)/2;
        assertEquals(left, right);
    }

    /**
     * g(X, Y) + g`(X, Y) + e(X, Y) = |X|*|Y|
     */
    @Test
    public void inversesTest1() {
        long left = g(A, B, false) + g_1(A, B) + e(A, B);
        long right = A.length * B.length;
        assertEquals(left, right);
    }

}
