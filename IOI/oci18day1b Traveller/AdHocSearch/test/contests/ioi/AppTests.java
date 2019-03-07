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
    public void case0Test() {
        int N = 3, S = 1;
        Edge[] edges = new Edge[] {
            new Edge(0, 1),
            new Edge(1, 2)
        };
        assertEquals(2, app.count(N, S, edges));
    }

    @Test
    public void case2Test() {
        int N = 8, S = 7;
        Edge[] edges = new Edge[] {
            new Edge(0, 1),
            new Edge(1, 2),
            new Edge(2, 3),
            new Edge(3, 4),
            new Edge(4, 1),
            new Edge(3, 5),
            new Edge(6, 5),
            new Edge(7, 5),
            new Edge(7, 6)
        };
        assertEquals(4, app.count(N, S, edges));
    }



}
