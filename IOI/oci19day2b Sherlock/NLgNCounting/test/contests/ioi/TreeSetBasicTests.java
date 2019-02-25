package contests.ioi;

import java.util.SortedSet;
import java.util.TreeSet;

import static org.junit.Assert.assertEquals;

import org.junit.Ignore;
import org.junit.Test;

@Ignore
public class TreeSetBasicTests {

    @Test
    public void test0() {
        TreeSet<TreeItem> t = new TreeSet<TreeItem>();
        t.add(new TreeItem(7));
        t.add(new TreeItem(9));
        t.add(new TreeItem(3));
        t.add(new TreeItem(5));
        t.add(new TreeItem(1));
        t.add(new TreeItem(6));
        t.add(new TreeItem(4));
        SortedSet<TreeItem> head = t.headSet(new TreeItem(5));
        assertEquals(3, head.size());
    }

    @Test
    public void emptyHeadTest() {
        TreeSet<TreeItem> t = new TreeSet<TreeItem>();
        t.add(new TreeItem(7));
        t.add(new TreeItem(9));
        t.add(new TreeItem(3));
        t.add(new TreeItem(5));
        t.add(new TreeItem(1));
        t.add(new TreeItem(6));
        t.add(new TreeItem(4));
        SortedSet<TreeItem> head = t.headSet(new TreeItem(1));
        assertEquals(0, head.size());
    }

    @Test
    public void fullHeadTest() {
        TreeSet<TreeItem> t = new TreeSet<TreeItem>();
        t.add(new TreeItem(7));
        t.add(new TreeItem(9));
        t.add(new TreeItem(3));
        t.add(new TreeItem(5));
        t.add(new TreeItem(1));
        t.add(new TreeItem(6));
        t.add(new TreeItem(4));
        SortedSet<TreeItem> head = t.headSet(new TreeItem(10));
        assertEquals(7, head.size());
    }

    @Test
    public void inclusiveHeadTest() {
        TreeSet<TreeItem> t = new TreeSet<TreeItem>();
        t.add(new TreeItem(7));
        t.add(new TreeItem(9));
        t.add(new TreeItem(3));
        t.add(new TreeItem(5));
        t.add(new TreeItem(1));
        t.add(new TreeItem(6));
        t.add(new TreeItem(4));
        SortedSet<TreeItem> head = t.headSet(new TreeItem(4), true);
        assertEquals(3, head.size());
    }

    @Test
    public void traveHeadTest() {
        TreeSet<TreeItem> t = new TreeSet<TreeItem>();
        t.add(new TreeItem(7));
        t.add(new TreeItem(9));
        t.add(new TreeItem(3));
        t.add(new TreeItem(5));
        t.add(new TreeItem(1));
        t.add(new TreeItem(6));
        t.add(new TreeItem(4));
        SortedSet<TreeItem> head = t.headSet(new TreeItem(4));
        assertEquals(2, head.size());
        assertEquals(1, head.first().getValue());
        assertEquals(3, head.last().getValue());
    }

}
