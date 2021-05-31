package nz.ac.auckland.softeng281.a4;

/**
 * The Linked List Class Has only one head pointer to the start node. Nodes are
 * indexed starting from 0. List goes from 0 to size-1
 *
 * @author Partha Roop
 */
public class EdgesLinkedList {
	// the head of the linked list
	private Edge head;

	public EdgesLinkedList() {
		head = null;
	}


	/**
	 * This method adds a node with specified data as the start node of the list
	 *
	 * @param : an integer, which is the value of the Node
	 * @return void
	 */
	public void prepend(Edge e) {
		Edge n = new Edge(e.getSource(), e.getTarget(), e.getWeight());
		n.setNext(head);
		head = n;

	}

	/**
	 * This method adds a node with specified data as the end node of the list
	 *
	 * @param : an integer, which is the value of the Node
	 * @return void
	 */

	public void append(Edge edge) {
		if (head==null) {
			head = edge;
		} else {
			Edge lastEdge = get(size()-1);
			lastEdge.setNext(edge);
		}
	}

	/**
	 * This method gets the value of a node at a given position
	 *
	 * @param pos: an integer, which is the position
	 * @return the value at the position pos
	 */

	public Edge get(int pos) throws InvalidPositionException {
		if (pos < 0 || pos > size() - 1) {
			throw new InvalidPositionException("Position " + pos + " outside the list boundary");
		}
		int i = 0;
		Edge t = head;
		while(i!=pos) {
			++i;
			t=t.getNext();
		}

		return t;
	}

	/**
	 * This method fetches the value of a node at a given position
	 *
	 * @param pos: an integer, which is the position
	 * @return the value at the position pos
	 */

	public void insert(int pos, Edge edge) throws InvalidPositionException {
		if (pos < 0 || pos > size() - 1) {
			throw new InvalidPositionException("Position " + pos + " outside the list boundary");
		}
		throw new java.lang.UnsupportedOperationException("Not implemented yet.");
	}

	/**
	 * This method removes a node at a given position
	 *
	 * @param pos: an integer, which is the position
	 * @return void
	 */

	public void remove(int pos) throws InvalidPositionException {
		if (pos < 0 || pos > size() - 1) {
			throw new InvalidPositionException("Position " + pos + " outside the list boundary");
		}
		throw new java.lang.UnsupportedOperationException("Not implemented yet.");
	}

	/**
	 * This method returns the size of a list
	 *
	 * @param
	 * @return the size of the list
	 */

	public int size() {
		int i = 0;

		Edge t = head;
		while(t!=null) {
			++i;
			t=t.getNext();
		}
		return i;
	}

	/**
	 * This method is used for printing the data in the list from head till the last
	 * node
	 *
	 * @param
	 * @return void
	 */

	public void print() {
		Edge edge = head;
		while (edge != null) {
			System.out.println(edge);
			edge = edge.getNext();
		}
	}
}
