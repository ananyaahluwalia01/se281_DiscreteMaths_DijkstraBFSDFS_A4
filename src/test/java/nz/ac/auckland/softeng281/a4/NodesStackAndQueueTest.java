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
    public void isEmptyEmptyStack() {
        assertTrue(stack.isEmpty());
    }

	
    @Test
    public void isEmptyNotEmpty() {
        stack.append(new Node("4"));
        assertFalse(stack.isEmpty());
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
    
    
}