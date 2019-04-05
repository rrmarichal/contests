package linkedlist;

import static org.junit.Assert.*;

import org.junit.Test;

public class LinkedListTests {

    @Test
    public void testCreateEmpty() {
        LinkedList<Integer> list = LinkedList.<Integer>create();
        assertNotNull(list);
    }

    @Test
    public void testCreateWithItems() {
        LinkedList<Integer> list = LinkedList.<Integer>create(new Integer[] { 1, 2, 3 });
        assertNotNull(list);
    }

    @Test
    public void testAdd0() {
        LinkedList<Integer> list = LinkedList.<Integer>create();
        list.add(1);
        assertEquals(1, list.size());
        assertEquals(1, (int) list.first().item);
    }

    @Test
    public void testAdd1() {
        LinkedList<Integer> list = LinkedList.<Integer>create(new Integer[] { 1, 2, 3 });
        list.add(4);
        assertEquals(4, list.size());
        assertEquals(1, (int) list.first().item);
        assertEquals(4, (int) list.last().item);
    }

    @Test
    public void testRemove0() {
        LinkedList<Integer> list = LinkedList.<Integer>create();
        list.add(1);
        LinkedList.Node<Integer> second = list.add(2);
        LinkedList.Node<Integer> third = list.add(3);
        list.add(4);
        assertEquals(4, list.size());

        list.remove(second);
        assertEquals(3, list.size());

        list.remove(third);
        assertEquals(2, list.size());
        assertEquals(4, (int) list.first().next.item);
        assertEquals(1, (int) list.last().prev.item);

        list.remove(list.first());
        assertEquals(1, list.size());

        list.remove(list.first());
        assertEquals(0, list.size());
    }

}
