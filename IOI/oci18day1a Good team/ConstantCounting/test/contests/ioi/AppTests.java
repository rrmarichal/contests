package contests.ioi;

import static org.junit.Assert.*;

import org.junit.*;

public class AppTests {

    private App app;

    @Before
    public void setup() {
        app = new App();
    }

    @Test
    public void squaresSumTest0() {
        assertEquals(1, app.squaresSum(1));
    }

    @Test
    public void squaresSumTest1() {
        assertEquals(5, app.squaresSum(2));
    }

    @Test
    public void squaresSumTest2() {
        assertEquals(14, app.squaresSum(3));
    }

    @Test
    public void squaresSumTest3() {
        assertEquals(30, app.squaresSum(4));
    }

    @Test
    public void squaresSumTest4() {
        assertEquals(140, app.squaresSum(7));
    }

    @Test
    public void cubesSumTest0() {
        assertEquals(1, app.cubesSum(1));
    }

    @Test
    public void cubesSumTest1() {
        assertEquals(9, app.cubesSum(2));
    }

    @Test
    public void cubesSumTest2() {
        assertEquals(36, app.cubesSum(3));
    }

    @Test
    public void cubesSumTest3() {
        assertEquals(100, app.cubesSum(4));
    }

    @Test
    public void cubesSumTest4() {
        assertEquals(784, app.cubesSum(7));
    }

    @Test
    public void case0Test() {
        assertEquals(bruteForce(1), app.count(1));
    }

    @Test
    public void case1Test() {
        assertEquals(bruteForce(4), app.count(4));
    }

    @Test
    public void case1aTest() {
        assertEquals(bruteForce(5), app.count(5));
    }

    @Test
    public void case1bTest() {
        assertEquals(bruteForce(8), app.count(8));
    }

    @Test
    public void case1cTest() {
        assertEquals(bruteForce(9), app.count(9));
    }

    @Test
    public void case1dTest() {
        assertEquals(bruteForce(42), app.count(42));
    }

    @Test
    public void case1eTest() {
        assertEquals(bruteForce(64), app.count(64));
    }

    @Test
    public void case1fTest() {
        assertEquals(bruteForce(27), app.count(27));
    }

    @Test
    public void case2Test() {
        assertEquals(bruteForce(100), app.count(100));
    }

    @Test
    public void case2aTest() {
        assertEquals(bruteForce((int) 1e4), app.count((int) 1e4));
    }

    @Test
    public void case3Test() {
        assertEquals(741664100, app.count((int) 1e6));
    }

    @Test
    public void case4Test() {
        assertEquals(21831850819005L, app.count((long) 1e9));
    }

    @Test
    public void case5Test() {
        assertEquals(674166666641510000L, app.count((long) 1e12));
    }

    private long bruteForce(int N) {
        long _2sum = 0, _3sum = 0;
        for (int j = 1; j <= N; j++)
            _2sum += Math.ceil(Math.sqrt(j));
        for (int j = 1; j <= N; j++) {
            _3sum += Math.floor(Math.cbrt(j));
        }
        return _2sum + _3sum;
    }

}
