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
	
	@Test
	public void testSize() {
		list.prepend(new Edge(new Node("1"), new Node("2"), 1));
		list.prepend(new Edge(new Node("3"), new Node("4"), 1));
		list.prepend(new Edge(new Node("5"), new Node("6"), 1));
		assertEquals(3, list.size());
	}
	
	@Test
	public void testSizeEmpty() {
		assertEquals(0, list.size());
	}
	
	@Test
	public void testGet() {
		list.prepend(new Edge(new Node("5"), new Node("6"), 2));
		assertEquals(new Edge(new Node("5"), new Node("6"), 2), list.get(0));

	}
	
	
	@Test
	public void testPrependB() {
		list.prepend(new Edge(new Node("1"), new Node("2"), 1));
		list.prepend(new Edge(new Node("3"), new Node("4"), 1));
		list.prepend(new Edge(new Node("5"), new Node("6"), 2));
		assertEquals(new Edge(new Node("5"), new Node("6"), 2), list.get(0));

	}

	@Test
	public void testAppendA() {
		list.prepend(new Edge(new Node("1"), new Node("2"), 1));
		list.append(new Edge(new Node("3"), new Node("4"), 1));
		list.append(new Edge(new Node("5"), new Node("6"), 2));
		assertEquals(new Edge(new Node("5"), new Node("6"), 2), list.get(2));
	}

}