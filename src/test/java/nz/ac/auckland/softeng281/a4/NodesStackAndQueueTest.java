package nz.ac.auckland.softeng281.a4;

import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

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
    
}