package nz.ac.auckland.softeng281.a4;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;
import static org.junit.Assert.assertEquals;

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
    
    
}