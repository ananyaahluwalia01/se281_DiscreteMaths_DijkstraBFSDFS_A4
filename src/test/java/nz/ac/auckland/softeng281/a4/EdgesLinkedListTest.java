package nz.ac.auckland.softeng281.a4;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class EdgesLinkedListTest {

    EdgesLinkedList list;

    @Before
    public void setUp() {
        list = new EdgesLinkedList();
    }

    @Test
    public void testPrependEmptyList() {
        list.prepend(new Edge(new Node("1"), new Node("2"), 1));
        assertEquals(new Edge(new Node("1"), new Node("2"), 1), list.get(0));
    }

}