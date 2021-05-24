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

    public boolean isEmpty() {
    	System.out.println(rear + " HAH " + front);
    	if (front == rear) {
        	System.out.println(rear + " empty " + front);
        	return true;
        } else {
        	System.out.println(rear + " not empty " + front);
        	return false;
        }
    }

    /**
     * Push operation refers to inserting an element on the Top of the stack.
     *
     * @param node
     */
    public void push(Node node) {
        throw new java.lang.UnsupportedOperationException("Not implemented yet.");
    }

    /**
     * pop an element from the top of the stack (removes and returns the tope element)
     *
     * @return
     */
    public Node pop() { // equivalent to the dequeue REMOVE COMMENT
    	throw new java.lang.UnsupportedOperationException("Not implemented yet.");
    }
    
    /**
     * get the element from the top of the stack without removing it
     *
     * @return
     */
    public Node peek() {
        throw new java.lang.UnsupportedOperationException("Not implemented yet.");
    }

    /**
     * append an element at the end of the stack
     *
     * @param node
     */
    public void append(Node node) { // equivalent to the enqueue REMOVE COMMENT
    	data.set(rear, node);
    	++rear;
    	++count;
    }
}
