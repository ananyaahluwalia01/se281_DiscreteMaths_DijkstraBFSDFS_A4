package nz.ac.auckland.softeng281.a4;
import java.util.ArrayList;

public class NodesStackAndQueue {
	// declare instance fields 
	private int front;
	private int rear;
	private int count;
	private ArrayList<Node> data;

	public NodesStackAndQueue() {
		// initialise instance fields
		data = new ArrayList <>();
		front = 0;
		rear = 0;
		count = 0;
	}

	// helper method for testing, count tracks the length of the actual stack
	public int getCount() {
		return count;
	}

	// checks if queue is empty
	public boolean isEmpty() {
		if (front == rear) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Push operation refers to inserting an element on the start/top/front of the stack.
	 *
	 * @param node
	 */
	public void push(Node node) {
		data.add(front, node);

		// increment rear and count as size of stack has increased
		++rear;
		++count;

	}

	/**
	 * pop an element from the start/top/front of the stack (removes and returns the top element)
	 *
	 * @return
	 */
	public Node pop() { // equivalent to the dequeue REMOVE COMMENT
		if(data.isEmpty()) {
			throw new EmptyException("Stack is empty.");
		}
		Node out = data.get(front);

		// increment front as the popped element is no longer part of the stack
		++front;

		// decrease count as the size of the stack has decreased,
		--count;
		return out;
	}


	/**
	 * get the element from the start/top/front of the stack without removing it
	 *
	 * @return
	 */
	public Node peek() {

		if(data.isEmpty()) {
			throw new EmptyException("Stack is empty.");
		}
		return data.get(front);
	}

	/**
	 * append an element at the end/bottom/rear of the stack
	 *
	 * @param node
	 */
	public void append(Node node) { 
		data.add(rear, node);

		// increment count and rear as size of stack has increased
		++rear;
		++count;
	}
}
