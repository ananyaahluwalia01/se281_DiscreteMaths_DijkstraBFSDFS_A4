package nz.ac.auckland.softeng281.a4;

import org.junit.After;
import org.junit.Before;
// import org.junit.Ignore;
import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

@RunWith(Enclosed.class)
public class GraphTest {

    public static class GraphUnitTest {
        Graph graph;

        @Before
        public void setUp() throws Exception {
            List<String> edges = Arrays.asList("1,2", "2,3", "2,4", "4,2");
            List<String> weights = Arrays.asList("10", "20", "30", "20");
            graph = new Graph(edges, weights);
        }
        
        @Test
        public void testFindNode() {
            assertTrue(graph.isNodeInGraph(new Node("1")));
            assertTrue(graph.isNodeInGraph(new Node("2")));
            assertTrue(graph.isNodeInGraph(new Node("3")));
            assertTrue(graph.isNodeInGraph(new Node("4")));
            assertFalse(graph.isNodeInGraph(new Node("5")));
        }

        @Test
        public void testShortestPath() {
            List<String> edges = Arrays.asList("1,2", "2,3", "3,4", "4,5");
            List<String> weights = Arrays.asList("10", "20", "30", "20");
            graph = new Graph(edges, weights);
            Path path = new Path(80, new Node("1"), new Node("2"), new Node("3"), new Node("4"), new Node("5"));
            assertEquals(path, graph.computeShortestPath(new Node("1"), new Node("5")));
        }
        
        // CHANGE THIS TEST
        @Test
        public void testIsNodeInGraph() {
            assertTrue(graph.isNodeInGraph(new Node("1")));
        }
        
    }

    public static class GraphSystemTest {

        ByteArrayOutputStream myOut;
        PrintStream origOut;

        @Before
        public void setUp() {
            origOut = System.out;
            myOut = new ByteArrayOutputStream();
            System.setOut(new PrintStream(myOut));
        }

        @After
        public void tearDown() {
            System.setOut(origOut);
            if (myOut.toString().length() > 1) {
                System.out.println(System.lineSeparator() + "the System.out.print was :" + System.lineSeparator()
                        + myOut.toString());
            }
        }

        private void runTest(String fileName, String command) {
            GraphUI.scanner = new Scanner("open " + fileName + System.getProperty("line.separator") + command
                    + System.getProperty("line.separator") + "exit" + System.getProperty("line.separator"));
            GraphControl controller = new GraphControl();
            controller.execute();
        }
        
        @Test
        public void testSearchWeightA() {
            runTest("a.txt", "search 1 3");
            assertTrue(myOut.toString().contains("Given the edge from source 1 target 3 has weight: 5"));
        }

        @Test
        public void testSearchEdgeByWeightA() {
            runTest("a.txt", "search 5");
            assertTrue(myOut.toString().contains("The edge searched having weight 5 is: 1-->3"));
        }
        
        @Test
        public void testShortestPathA() {
            runTest("a.txt", "path 5 1");
            assertTrue(myOut.toString().contains("The shortest path is: 5 -> 4 -> 1 cost: 4"));
        }
        
        // ANANYA'S TESTS DFS, BFS, SHORTEST PATH
        
        @Test
        public void testSearchWeightByEdgeForSelfLoop() {
        	runTest("a.txt", "search 3 3");
        	assertTrue(myOut.toString().contains("Given the edge from source 3 target 3 has weight: 2"));
        }
        
        @Test
        public void testSearchWeightByEdgeDoesNotExist() {
        	runTest("a.txt", "search 3 4");
        	assertTrue(myOut.toString().contains("There is no such edge with source : Node{value='3'} target: Node{value='4'}"));
        }
        
        // should display the first occurrence according to BFS
        @Test
        public void testSearchEdgeByWeightMultipleSameWeight() {
            runTest("a.txt", "search 1");
            assertTrue(myOut.toString().contains("The edge searched having weight 1 is: 0-->0"));
        }
        
        @Test
        public void testSearchEdgeByWeightDoesNotExist() {
            runTest("a.txt", "search 55");
            assertTrue(myOut.toString().contains("There is no such edge with weight: 55"));
        }
        
        // self loops only ignored for shortest path, NOT FOR DFS AND BFS (no edge with weight 0)
        @Test
        public void testSearchEdgeByWeightSelfLoop() {
            runTest("a.txt", "search 0");
            assertTrue(myOut.toString().contains("There is no such edge with weight: 0"));
        }
        
        @Test
        public void testShortestPathB() {
        	runTest("a.txt", "path 4 0");
            assertTrue(myOut.toString().contains("The shortest path is: 4 -> 2 -> 0 cost: 2"));
        }
        
        @Test
        public void testShortestPathC() {
        	runTest("a.txt", "path 3 3");
            assertTrue(myOut.toString().contains("The shortest path is: 3 -> 3 cost: 2"));
        }
        

    }


}