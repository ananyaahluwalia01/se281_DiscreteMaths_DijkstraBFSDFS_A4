package nz.ac.auckland.softeng281.a4;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * You cannot add new fields.
 */
public class Graph {

	/**
	 * Each node maps to a list of all the outgoing edges from that node
	 */
	private HashMap<Node, EdgesLinkedList> adjacencyMap;
	/**
	 * root of the graph, to know where to start the DFS or BFS
	 */
	private Node root;

	/**
	 * !!!!!! You cannot change this method !!!!!!!
	 */
	public Graph(List<String> edges, List<String> weights) {
		if (edges.isEmpty() || weights.isEmpty()) {
			throw new IllegalArgumentException("edges and weights are empty");
		}
		adjacencyMap = new HashMap<>();
		int i = 0;
		for (String edge : edges) {
			String[] split = edge.split(",");
			Node source = new Node(split[0]);
			Node target = new Node(split[1]);
			Edge edgeObject = new Edge(source, target, Integer.parseInt(weights.get(i)));
			if (!adjacencyMap.containsKey(source)) {
				adjacencyMap.put(source, new EdgesLinkedList());
			}
			adjacencyMap.get(source).append(edgeObject);
			if (i == 0) {
				root = source;
			}
			i++;
		}
	}


	/**
	 * find a particular node, note that a node might not have outgoing edges but only ingoing edges
	 * so you need to check also the target nodes of the edges
	 *
	 * @param node
	 * @return true if adjacencyMap contains the node, false otherwise.
	 */
	public boolean isNodeInGraph(Node node) {
		if (adjacencyMap.containsKey(node)) {
			return true;
		} else {
			for (Node key : adjacencyMap.keySet()) {
				EdgesLinkedList check = adjacencyMap.get(key);
				for (int i=0; i < (check.size()-1); i++) {
					if (node.equals(check.get(i).getTarget())) {
						return true;
					}
				}
			}
		} 
		return false;
	}


	/**
	 * This method finds an edge with a specific weight, if there are more
	 * than one you need to return the first you encounter.
	 * You must use Breath First Search (BFS) strategy starting from the root.
	 * <p>
	 * You can create a data structure to keep track of the visited nodes
	 * Set<Node> visited = new HashSet<>();
	 * If you don't keep track of the visited nodes the method will run forever!
	 * <p>
	 * <p>
	 * In addition to the data structure visited you can only create new
	 * datastructures of type EdgesLinkedList and NodesStackAndQue
	 *
	 * @param weight
	 * @return the Edge with the specific weight, null if no edge with the specif weight exists in teh graph
	 */
	public Edge searchEdgeByWeight(int weight) {
		if (!(root==null)) {
			Set<Node> visited = new HashSet<>();
			NodesStackAndQueue queue = new NodesStackAndQueue();
			visited.add(root);
			queue.append(root);

			while(!queue.isEmpty()){
				Node parentNode = queue.pop();
				EdgesLinkedList childrenEdges = adjacencyMap.get(parentNode);

				for (int i=0; i < (childrenEdges.size()); i++) {
					int currentChildWeight = childrenEdges.get(i).getWeight();
					if (currentChildWeight == weight) {
						return childrenEdges.get(i);
					}
					parentNode = childrenEdges.get(i).getTarget();
				}

				if (!visited.contains(parentNode)) {
					visited.add(parentNode);
					queue.append(parentNode);
				}
			}

		}
		return null;
	}


	/**
	 * Returns the weight of the Edeg with Node source and Node target if the
	 * given Edge is inside the graph.
	 * If there is no edge with the specified source and target, the method returns -1
	 * You must use Depth First Search (DFS) strategy starting from the root.
	 * <p>
	 * RULES
	 * You can create a data structure to keep track of the visited nodes
	 * Set<Node> visited = new HashSet<>();
	 * If you don't keep track of the visited nodes the method will run forever!
	 * <p>
	 * In addition to the data structure visited you can only create new data structures of type
	 * <p>
	 * NodesStackAndQueue and EdgesLinkedList
	 *
	 * @param source
	 * @param target
	 * @return the weight of the first encountered edge with source and target,
	 * -1 if no edge with the given source and target exists
	 */
	public int searchWeightByEdge(Node source, Node target) {
		if (!(root==null)) {
			Set<Node> visited = new HashSet<>();
			NodesStackAndQueue stack = new NodesStackAndQueue();
			visited.add(root);
			stack.push(root);

			while(!stack.isEmpty()){
				Node parentNode = stack.pop();
				EdgesLinkedList childrenEdges = adjacencyMap.get(parentNode);

				for (int i=0; i < (childrenEdges.size()); i++) {
					Node currentChildSource = childrenEdges.get(i).getSource();
					Node currentChildTarget = childrenEdges.get(i).getTarget();

					if ((currentChildSource.equals(source)) && (currentChildTarget.equals(currentChildTarget))) {
						return childrenEdges.get(i).getWeight();
					}
					parentNode = childrenEdges.get(i).getTarget();
				}

				if (!visited.contains(parentNode)) {
					visited.add(parentNode);
					stack.push(parentNode);
				}
			}

		}
		return -1;
	}


	/**
	 * Given a source Node and a target Node it returns the shortest path
	 * between source and target
	 * <p>
	 * A Path is represented as an ordered sequence of nodes, together with the
	 * total weight of the path. (see Path.java class)
	 *
	 * @param source
	 * @param target
	 * @return the shortest path between source and target
	 */
	public Path computeShortestPath(Node source, Node target) {
		Set<Node> visited = new HashSet<>();
		HashMap<Node, String> nodeAndDistance = new HashMap<>();
		HashMap<Node, Node> nodeAndPrevious = new HashMap<>();
		
		NodesStackAndQueue graphTargetNodes = getTargetFromAdjacencyMap();
		
		for (int i = 0; i < graphTargetNodes.getCount()-1; i++) {
			if (!nodeAndDistance.containsKey(graphTargetNodes.peek())) {
				nodeAndDistance.put(graphTargetNodes.peek(), "inf");
				nodeAndPrevious.put(graphTargetNodes.peek(), null);
				System.out.println(graphTargetNodes.peek());
				graphTargetNodes.pop();
			}
		}
		
		return null;
	}

	protected NodesStackAndQueue getTargetFromAdjacencyMap() {
		
		NodesStackAndQueue returnedList = new NodesStackAndQueue();
		
		for (Node key : adjacencyMap.keySet()) {
			EdgesLinkedList currentLinkedList = adjacencyMap.get(key);
			for (int i=0; i < (currentLinkedList.size()-1); i++) {
				returnedList.append(adjacencyMap.get(key).get(i).getTarget());
			}
		}
		
		return returnedList;
	}

}

