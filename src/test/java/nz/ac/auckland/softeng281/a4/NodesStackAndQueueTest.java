package nz.ac.auckland.softeng281.a4;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

public class NodesStackAndQueueTest {

	NodesStackAndQueue stack;

	@Before
	public void setUp() {
		stack = new NodesStackAndQueue();
	}

	@Test
	public void appendTestA() {
		stack.append(new Node("10"));

		assertEquals(stack.peek(), new Node("10"));
	}

	@Test
	public void appendTestB() {
		stack.append(new Node("10"));
		stack.append(new Node("20"));
		stack.append(new Node("30"));

		assertTrue("The size is correct", stack.getCount() == 3);
		assertEquals(stack.peek(), new Node("10"));
	}


	@Test
	public void isEmptyEmptyStack() {
		assertTrue(stack.isEmpty());
	}


	@Test
	public void isEmptyNotEmpty() {
		stack.append(new Node("4"));
		assertFalse(stack.isEmpty());
	}

	@Test
	public void isEmptyFullThenEmpty() {
		stack.push(new Node("4"));
		stack.pop();
		assertTrue(stack.isEmpty());
	}

	@Test
	public void popTestA() {
		stack.append(new Node("10"));
		stack.append(new Node("20"));
		stack.append(new Node("30"));

		stack.pop();

		assertTrue("The size is correct", stack.getCount() == 2);
	}


	@Test
	public void popTestB() {
		stack.append(new Node("10"));
		stack.append(new Node("20"));
		stack.append(new Node("30"));

		Node returned = stack.pop();

		Node expected = new Node("10");


		assertEquals(returned, expected);
	}

	@Test
	public void popTestCEmpty() {
		try {
			stack.pop();
			fail();
		} catch (EmptyException e){

		}
	}

	@Test
	public void peekTestA() {
		stack.append(new Node("10"));
		stack.append(new Node("20"));
		stack.append(new Node("30"));

		Node returned = stack.peek();

		Node expected = new Node("10");


		assertEquals(returned, expected);
	}

	public void peekTestBEmpty() {
		try {
			stack.peek();
			fail();
		} catch (EmptyException e) {

		}
	}

	public void peekTestCPoppedButNotEmpty() {
		try {
			stack.append(new Node("10"));
			stack.append(new Node("20"));
			stack.append(new Node("30"));

			stack.pop();

			Node returned = stack.peek();

			Node expected = new Node("20");

			assertEquals(returned, expected);

		} catch (EmptyException e) {
			fail();
		}
	}


	@Test
	public void pushTestA() {
		stack.append(new Node("10"));
		stack.append(new Node("20"));
		stack.append(new Node("30"));

		stack.push(new Node("5"));


		Node returned = stack.peek();

		Node expected = new Node("5");

		assertEquals(returned, expected);

	}

	@Test
	public void pushTestBEmpty() {

		stack.push(new Node("5"));

		Node returned = stack.peek();
		Node expected = new Node("5");

		assertEquals(returned, expected);

	}

	@Test
	public void pushTestCPushPop() {

		stack.append(new Node("10"));
		stack.append(new Node("20"));
		stack.append(new Node("30"));
		stack.push(new Node("5"));
		stack.push(new Node("2"));

		stack.pop();

		Node returned = stack.peek();
		Node expected = new Node("5");

		assertEquals(returned, expected);

	}



	@Test
	public void allTestA() {
		stack.append(new Node("20"));
		stack.append(new Node("30"));
		stack.push(new Node("10"));
		stack.push(new Node("5"));

		stack.append(new Node("40"));
		stack.pop();
		stack.pop();



		assertTrue("The size is correct", stack.getCount() == 3);
		assertEquals(stack.peek(), new Node("20"));
	}



}