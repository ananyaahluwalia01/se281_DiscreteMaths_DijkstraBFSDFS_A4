package nz.ac.auckland.softeng281.a4;

import org.junit.Before;

import org.junit.Test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

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
		try {
			list.prepend(new Edge(new Node("5"), new Node("6"), 2));
			assertEquals(new Edge(new Node("5"), new Node("6"), 2), list.get(0));
		} catch (InvalidPositionException e) {
			fail();
		}
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

	@Test
	public void testAppendEmpty() {
		try {
			list.append(new Edge(new Node("1"), new Node("2"), 1));
		} catch (InvalidPositionException e) {
			fail();
		}
	}

	@Test
	public void testGetEmpty() {
		try {
			list.get(0);
		} catch (InvalidPositionException e) {

		}
	}

	@Test
	public void testPrependC() {
		list.prepend(new Edge(new Node("1"), new Node("2"), 1));
		list.prepend(new Edge(new Node("3"), new Node("4"), 1));
		list.prepend(new Edge(new Node("5"), new Node("6"), 2));
		assertEquals(new Edge(new Node("5"), new Node("6"), 2), list.get(0));
		assertEquals(new Edge(new Node("3"), new Node("4"), 1), list.get(1));
		assertEquals(new Edge(new Node("1"), new Node("2"), 1), list.get(2));
	}

	@Test
	public void testInsert() {
		list.prepend(new Edge(new Node("1"), new Node("2"), 1));
		list.append(new Edge(new Node("3"), new Node("4"), 1));
		list.append(new Edge(new Node("5"), new Node("6"), 2));
		list.insert(1, new Edge(new Node("7"), new Node("8"), 5));
		assertEquals(new Edge(new Node("7"), new Node("8"), 5), list.get(1));
	}

	@Test
	public void testInsertB() {
		list.prepend(new Edge(new Node("1"), new Node("2"), 1));
		list.append(new Edge(new Node("3"), new Node("4"), 1));
		list.append(new Edge(new Node("5"), new Node("6"), 2));
		list.insert(2, new Edge(new Node("7"), new Node("8"), 5));
		assertEquals(new Edge(new Node("1"), new Node("2"), 1), list.get(0));
		assertEquals(new Edge(new Node("7"), new Node("8"), 5), list.get(2));
		assertEquals(new Edge(new Node("3"), new Node("4"), 1), list.get(1));
		assertEquals(new Edge(new Node("5"), new Node("6"), 2), list.get(3));
	}

	@Test
	public void testRemove() {
		list.append(new Edge(new Node("1"), new Node("2"), 1));
		list.append(new Edge(new Node("3"), new Node("4"), 1));
		list.append(new Edge(new Node("5"), new Node("6"), 1));

		list.remove(0);
		assertEquals(list.size(), 2);
		assertEquals(new Edge(new Node("3"), new Node("4"), 1), list.get(0));
	}

	@Test
	public void testRemoveB() {
		list.append(new Edge(new Node("1"), new Node("2"), 1));
		list.append(new Edge(new Node("3"), new Node("4"), 1));
		list.append(new Edge(new Node("5"), new Node("6"), 1));

		list.remove(0);
		assertEquals(list.size(), 2);
		assertEquals(new Edge(new Node("3"), new Node("4"), 1), list.get(0));
		assertEquals(new Edge(new Node("5"), new Node("6"), 1), list.get(1));

		try {
			list.get(2);
		} catch (InvalidPositionException e) {

		}

	}

	@Test
	public void testRemoveC() {
		list.append(new Edge(new Node("1"), new Node("2"), 1));
		list.append(new Edge(new Node("3"), new Node("4"), 1));
		list.append(new Edge(new Node("5"), new Node("6"), 1));
		list.append(new Edge(new Node("7"), new Node("8"), 1));
		list.append(new Edge(new Node("9"), new Node("10"), 1));
		list.append(new Edge(new Node("11"), new Node("12"), 1));

		list.remove(4);

		assertEquals(list.size(), 5);
		assertEquals(new Edge(new Node("1"), new Node("2"), 1), list.get(0));
		assertEquals(new Edge(new Node("3"), new Node("4"), 1), list.get(1));
		assertEquals(new Edge(new Node("5"), new Node("6"), 1), list.get(2));
		assertEquals(new Edge(new Node("7"), new Node("8"), 1), list.get(3));
		assertEquals(new Edge(new Node("11"), new Node("12"), 1), list.get(4));

		try {
			list.get(5);
		} catch (InvalidPositionException e) {

		}
	}

	@Test
	public void testRemoveEmpty() {
		try {
			list.remove(0);
		} catch(InvalidPositionException e) {

		}
	}
	
	@Test
	public void testRemoveOneItemList() {
		list.append(new Edge(new Node("1"), new Node("2"), 1));
		list.remove(0);
		
		assertEquals(list.size(), 0);
	}



}