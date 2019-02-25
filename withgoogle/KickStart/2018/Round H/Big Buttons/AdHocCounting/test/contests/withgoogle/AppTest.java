package contests.withgoogle;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;

import org.junit.Before;
import org.junit.Test;

public class AppTest {

    private App app;

    @Before
    public void init() {
        app = new App();
    }

    @Test
    public void case0PrefixesRemovalTest() {
        String[] prefixes = new String[] {
            "BBB",
            "RB"
        };
        assertEquals(2, app.removePrefixed(prefixes).size());
    }

    @Test
    public void case1PrefixesRemovalTest() {
        String[] prefixes = new String[] {
            "R"
        };
        assertEquals(1, app.removePrefixed(prefixes).size());
    }

    @Test
    public void case2PrefixesRemovalTest() {
        String[] prefixes = new String[] {
            "R",
            "B",
            "RBRB"
        };
        assertEquals(2, app.removePrefixed(prefixes).size());
    }

    @Test
    public void case3PrefixesRemovalTest() {
        String[] prefixes = new String[] {
            "BRBRBBBRBRRRBBB",
            "BRBRBRRRBRRRBRB",
            "BBBRBBBRBRRRBBB",
            "BRBRBRRRBRRRB",
            "BRBRBBBRBBBRB"
        };
        assertEquals(4, app.removePrefixed(prefixes).size());
    }

    @Test
    public void case0Test() {
        int N = 3;
        ArrayList<String> prefixes = new ArrayList<String>();
        prefixes.add("BBB");
        prefixes.add("RB");
        assertEquals(5, app.solve(N, prefixes));
    }

    @Test
    public void case1Test() {
        int N = 5;
        ArrayList<String> prefixes = new ArrayList<String>();
        prefixes.add("R");
        assertEquals(16, app.solve(N, prefixes));
    }

    @Test
    public void case2Test() {
        int N = 4;
        ArrayList<String> prefixes = new ArrayList<String>();
        prefixes.add("R");
        prefixes.add("B");
        assertEquals(0, app.solve(N, prefixes));
    }

    @Test
    public void case3Test() {
        int N = 50;
        ArrayList<String> prefixes = new ArrayList<String>();
        prefixes.add("BRBRBBBRBRRRBBB");
        prefixes.add("BBBRBBBRBRRRBBB");
        prefixes.add("BRBRBRRRBRRRB");
        prefixes.add("BRBRBBBRBBBRB");
        assertEquals(1125556309458944L, app.solve(N, prefixes));
    }

}
