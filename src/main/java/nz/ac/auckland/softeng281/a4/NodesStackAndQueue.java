package nz.ac.auckland.softeng281.a4;
import java.util.ArrayList;

public class NodesStackAndQueue {
	private int front;
	private int rear;
	private int count;
	private ArrayList<Node> data;

	public NodesStackAndQueue() {
		data = new ArrayList <>();
		front = 0;
		rear = 0;
		count = 0;
	}

	public int getCount() {
		return count;
	}

	public boolean isEmpty() {
		if (front == rear) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Push operation refers to inserting an element on the Top of the stack.
	 *
	 * @param node
	 */
	public void push(Node node) {
		data.add(front, node);
		++rear;
		++count;
		
	}

	/**
	 * pop an element from the top of the stack (removes and returns the top element)
	 *
	 * @return
	 */
	public Node pop() { // equivalent to the dequeue REMOVE COMMENT
		if(data.isEmpty()) {
			throw new EmptyException("Data is empty.");
		}
		Node out = data.get(front);
		++front;
		--count;
		return out;
	}


	/**
	 * get the element from the top of the stack without removing it
	 *
	 * @return
	 */
	public Node peek() {
		
		if(data.isEmpty()) {
			throw new EmptyException("Data is empty.");
		}
		return data.get(front);
	}

	/**
	 * append an element at the end of the stack
	 *
	 * @param node
	 */
	public void append(Node node) { // equivalent to the enqueue REMOVE COMMENT
		data.add(rear, node);
		++rear;
		++count;
	}
}
