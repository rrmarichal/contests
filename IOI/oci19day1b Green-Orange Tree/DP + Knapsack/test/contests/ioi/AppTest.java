package contests.ioi;

import static org.junit.Assert.*;

import org.junit.*;

public class AppTest {

    private App app;

    @Before
    public void setup() {
        app = new App();
    }

    @Test
    public void case0Test() {
        TreeEdge[] edges = new TreeEdge[] {
            new TreeEdge(0, 1, 0),
            new TreeEdge(1, 3, 1),
            new TreeEdge(3, 4, 1),
            new TreeEdge(3, 6, 0),
            new TreeEdge(2, 1, 1),
            new TreeEdge(5, 3, 0)
        };
        assertEquals(3, app.solve(7, 4, edges));
    }

    @Test
    public void case1Test() {
        TreeEdge[] edges = new TreeEdge[] {
            new TreeEdge(0, 1, 0),
            new TreeEdge(1, 3, 1),
            new TreeEdge(3, 4, 1),
            new TreeEdge(3, 6, 0),
            new TreeEdge(2, 1, 1),
            new TreeEdge(5, 3, 0)
        };
        assertEquals(2, app.solve(7, 3, edges));
    }

    @Test
    public void case3Test() {
        TreeEdge[] edges = new TreeEdge[] {
            new TreeEdge(0, 1, 0),
            new TreeEdge(1, 3, 1),
            new TreeEdge(3, 4, 1),
            new TreeEdge(3, 6, 0),
            new TreeEdge(2, 1, 1),
            new TreeEdge(5, 3, 1)
        };
        assertEquals(4, app.solve(7, 5, edges));
    }

    @Test
    public void case4Test() {
        TreeEdge[] edges = new TreeEdge[] {
            new TreeEdge(0, 1, 0),
            new TreeEdge(1, 3, 0),
            new TreeEdge(3, 4, 0),
            new TreeEdge(3, 6, 0),
            new TreeEdge(2, 1, 0),
            new TreeEdge(5, 3, 0),
            new TreeEdge(0, 7, 1),
            new TreeEdge(7, 8, 0),
            new TreeEdge(8, 9, 1)
        };
        assertEquals(2, app.solve(10, 4, edges));
    }

    @Test
    public void case5Test() {
        TreeEdge[] edges = new TreeEdge[] {
            new TreeEdge(0, 1, 0),
            new TreeEdge(1, 3, 0),
            new TreeEdge(3, 4, 0),
            new TreeEdge(3, 6, 0),
            new TreeEdge(2, 1, 0),
            new TreeEdge(5, 3, 0),
            new TreeEdge(0, 7, 1),
            new TreeEdge(7, 8, 0),
            new TreeEdge(8, 9, 1)
        };
        assertEquals(1, app.solve(10, 3, edges));
    }

    @Test
    public void case6Test() {
        TreeEdge[] edges = new TreeEdge[] {
            new TreeEdge(0, 1, 0),
            new TreeEdge(1, 3, 0),
            new TreeEdge(3, 4, 0),
            new TreeEdge(3, 6, 0),
            new TreeEdge(2, 1, 0),
            new TreeEdge(5, 3, 0),
            new TreeEdge(0, 7, 0),
            new TreeEdge(7, 8, 1),
            new TreeEdge(8, 9, 0)
        };
        assertEquals(1, app.solve(10, 3, edges));
    }

    @Test
    public void case7Test() {
        TreeEdge[] edges = new TreeEdge[] {
            new TreeEdge(0, 1, 0),
            new TreeEdge(1, 3, 0),
            new TreeEdge(3, 4, 0),
            new TreeEdge(3, 6, 0),
            new TreeEdge(2, 1, 0),
            new TreeEdge(5, 3, 0),
            new TreeEdge(0, 7, 0),
            new TreeEdge(7, 8, 0),
            new TreeEdge(8, 9, 1)
        };
        assertEquals(1, app.solve(10, 2, edges));
    }
    
    @Test
    public void case8Test() {
        TreeEdge[] edges = new TreeEdge[] {
            new TreeEdge(0, 1, 0),
            new TreeEdge(0, 2, 0),
            new TreeEdge(0, 3, 0),
            new TreeEdge(0, 4, 1)
        };
        assertEquals(1, app.solve(5, 5, edges));
    }

    @Test
    public void case9Test() {
        TreeEdge[] edges = new TreeEdge[] {
            new TreeEdge(0, 1, 0),
            new TreeEdge(0, 2, 1),
            new TreeEdge(0, 3, 0),
            new TreeEdge(0, 4, 1)
        };
        assertEquals(2, app.solve(5, 4, edges));
    }

    @Test
    public void case10Test() {
        TreeEdge[] edges = new TreeEdge[] {
            new TreeEdge(0, 1, 1),
            new TreeEdge(0, 2, 1),
            new TreeEdge(0, 3, 1),
            new TreeEdge(0, 4, 1)
        };
        assertEquals(2, app.solve(5, 3, edges));
    }

    @Test
    public void case11Test() {
        TreeEdge[] edges = new TreeEdge[] {
            new TreeEdge(0, 1, 1),
            new TreeEdge(0, 2, 1),
            new TreeEdge(0, 3, 1),
            new TreeEdge(0, 4, 1)
        };
        assertEquals(2, app.solve(5, 3, edges));
    }

    @Test
    public void case12Test() {
        TreeEdge[] edges = new TreeEdge[] {
            new TreeEdge(0, 1, 0),
            new TreeEdge(0, 2, 0),
            new TreeEdge(0, 3, 0),
            new TreeEdge(0, 4, 0),
            new TreeEdge(0, 5, 0),
            new TreeEdge(2, 6, 1),
            new TreeEdge(2, 7, 0),
            new TreeEdge(5, 8, 0),
            new TreeEdge(5, 8, 0),
            new TreeEdge(1, 9, 0),
            new TreeEdge(9, 10, 1),
        };
        assertEquals(1, app.solve(11, 5, edges));
    }

    @Test
    public void case13Test() {
        TreeEdge[] edges = new TreeEdge[] {
            new TreeEdge(0, 1, 0),
            new TreeEdge(0, 2, 0),
            new TreeEdge(0, 3, 0),
            new TreeEdge(0, 4, 0),
            new TreeEdge(0, 5, 0),
            new TreeEdge(2, 6, 1),
            new TreeEdge(2, 7, 0),
            new TreeEdge(5, 8, 0),
            new TreeEdge(5, 8, 0),
            new TreeEdge(1, 9, 0),
            new TreeEdge(9, 10, 1),
        };
        assertEquals(2, app.solve(11, 6, edges));
    }

    @Test
    public void case14Test() {
        TreeEdge[] edges = new TreeEdge[] {
            new TreeEdge(0, 1, 0),
            new TreeEdge(0, 2, 0),
            new TreeEdge(0, 3, 1),
            new TreeEdge(0, 4, 0),
            new TreeEdge(0, 5, 0),
            new TreeEdge(2, 6, 1),
            new TreeEdge(2, 7, 0),
            new TreeEdge(5, 8, 0),
            new TreeEdge(5, 8, 0),
            new TreeEdge(1, 9, 0),
            new TreeEdge(9, 10, 1),
        };
        assertEquals(3, app.solve(11, 11, edges));
    }

    @Test
    public void case15Test() {
        TreeEdge[] edges = new TreeEdge[] {
            new TreeEdge(5, 8, 0),
            new TreeEdge(0, 2, 0),
            new TreeEdge(0, 3, 1),
            new TreeEdge(5, 8, 0),
            new TreeEdge(1, 9, 0),
            new TreeEdge(2, 6, 1),
            new TreeEdge(2, 7, 0),
            new TreeEdge(9, 10, 1),
            new TreeEdge(0, 4, 0),
            new TreeEdge(0, 5, 0),
            new TreeEdge(0, 1, 0),
        };
        assertEquals(3, app.solve(11, 11, edges));
    }

    @Test
    public void case16Test() {
        TreeEdge[] edges = new TreeEdge[] {
            new TreeEdge(4, 3, 1),
            new TreeEdge(1, 0, 0),
            new TreeEdge(2, 0, 0),
            new TreeEdge(3, 0, 0),
        };
        assertEquals(1, app.solve(5, 5, edges));
    }

    @Test
    public void case17Test() {
        TreeEdge[] edges = new TreeEdge[] {
            // new TreeEdge(3, 0, 0),
            new TreeEdge(2, 1, 1),
            new TreeEdge(1, 0, 0),
        };
        assertEquals(1, app.solve(3, 3, edges));
    }

}
