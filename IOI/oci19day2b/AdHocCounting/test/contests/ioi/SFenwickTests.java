package contests.ioi;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class SFenwickTests {

    @Test
    public void createTest() {
        SFenwick sf = new SFenwick(7);
        sf.init(7);
        assertEquals(1, sf.sum(0));
        assertEquals(2, sf.sum(1));
        assertEquals(3, sf.sum(2));
        assertEquals(4, sf.sum(3));
        assertEquals(5, sf.sum(4));
        assertEquals(6, sf.sum(5));
        assertEquals(7, sf.sum(6));
    }

    @Test
    public void update0Test() {
        SFenwick sf = new SFenwick(7);
        sf.init(7);
        assertEquals(7, sf.sum(6));
        sf.update(0);
        assertEquals(6, sf.sum(6));
    }

    @Test
    public void update1Test() {
        SFenwick sf = new SFenwick(7);
        sf.init(7);
        assertEquals(7, sf.sum(6));
        sf.update(1);
        assertEquals(6, sf.sum(6));
    }

    @Test
    public void update2Test() {
        SFenwick sf = new SFenwick(7);
        sf.init(7);
        assertEquals(7, sf.sum(6));
        sf.update(2);
        assertEquals(6, sf.sum(6));
    }

    @Test
    public void update3Test() {
        SFenwick sf = new SFenwick(7);
        sf.init(7);
        assertEquals(7, sf.sum(6));
        sf.update(3);
        assertEquals(6, sf.sum(6));
    }

    @Test
    public void update4Test() {
        SFenwick sf = new SFenwick(7);
        sf.init(7);
        assertEquals(7, sf.sum(6));
        sf.update(4);
        assertEquals(6, sf.sum(6));
    }

    @Test
    public void update5Test() {
        SFenwick sf = new SFenwick(7);
        sf.init(7);
        assertEquals(7, sf.sum(6));
        sf.update(5);
        assertEquals(6, sf.sum(6));
    }

    @Test
    public void update6Test() {
        SFenwick sf = new SFenwick(7);
        sf.init(7);
        assertEquals(7, sf.sum(6));
        sf.update(6);
        assertEquals(6, sf.sum(6));
    }

    @Test
    public void updateAllTest() {
        SFenwick sf = new SFenwick(7);
        sf.init(7);
        assertEquals(7, sf.sum(6));
        sf.update(0);
        sf.update(1);
        sf.update(2);
        sf.update(3);
        sf.update(4);
        sf.update(5);
        sf.update(6);
        assertEquals(0, sf.sum(6));
    }

}
