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
		// set the value after e to the head edge, and set e as the new head edge.
		e.setNext(head);
		head = e;

	}

	/**
	 * This method adds a node with specified data as the end node of the list
	 *
	 * @param : an integer, which is the value of the Node
	 * @return void
	 */

	public void append(Edge edge) {
		if (head==null) {
			// if the list is empty, set the passed in edge to head
			head = edge;

		} else {
			// otherwise set the edge after the last edge on the current list, to the passed in edge
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

		// create edge to store the edge being checked
		Edge currentEdge = head;
		int i = 0;

		// go through list until you reach the position of interest 
		while(i!=pos) {
			++i;
			currentEdge = currentEdge.getNext();
		}

		// return edge at that position
		return currentEdge;
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
		// set the value at the point of interest to the next position in the list
		edge.setNext(get(pos));
		
		// insert the edge into the position of interest
		if (pos == 0) {
			head = edge;
		} else {
			get(pos-1).setNext(edge);
		}

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
		
		// if the list only has one element, remove that element
		if (size() == 1) {
			head = null;
		} else {
			
			// if removing start element, shift all the elements ahead of the head back one position
			if (pos == 0) {
				head = get(1);
				
				for (int i = pos+2; i < size(); i++) {
					get(i-2).setNext(get(i));	
				}
				
			} else {
				
				// shift all elements after the pos back one position
				for (int i = pos+1; i < size(); i++) {
					get(i-2).setNext(get(i));	
				}
			}
			
			// set the final element's previous index to null
			get(size()-1).setNext(null);
		}
	}

	/**
	 * This method returns the size of a list
	 *
	 * @param
	 * @return the size of the list
	 */

	public int size() {

		// create edge to store the edge being checked
		Edge currentEdge = head;
		int i = 0;

		// go through list until you find the space after last edge of the list (the tail which would be null)
		while(currentEdge!=null) {
			++i;
			currentEdge=currentEdge.getNext();
		}
		// return the pointer of the tail 
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
