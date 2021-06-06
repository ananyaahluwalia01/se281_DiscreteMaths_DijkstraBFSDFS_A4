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

		// if the node is a source node, return true
		if (adjacencyMap.containsKey(node)) {
			return true;
		} else {

			// if the node is a target node, return true (using helper method)
			NodesStackAndQueue graphTargetNodes = getAllTargetNodesFromAdjacencyMap();
			for (int i = 0; i < graphTargetNodes.getCount(); i++) {
				if (node.equals(graphTargetNodes.peek())) {
					return true;
				}
				graphTargetNodes.pop();
			}
		} 

		// otherwise false
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

		// if the graph is not empty
		if (!(root==null)) {

			// create set to store discovered nodes and queue to store node being examined
			Set<Node> visited = new HashSet<>();
			NodesStackAndQueue queue = new NodesStackAndQueue();
			visited.add(root);
			queue.append(root);

			while(!queue.isEmpty()){

				// set the parent node to the node being examined and the children edges to the edges where the parent node is the source
				Node parentNode = queue.pop();
				EdgesLinkedList childrenEdges = adjacencyMap.get(parentNode);

				// for all the children edges of the parent node, check if the weight of interest is the weight of the edge
				for (int i=0; i < (childrenEdges.size()); i++) {
					int currentChildWeight = childrenEdges.get(i).getWeight();
					if (currentChildWeight == weight) {

						// return the edge of the weight of interest
						return childrenEdges.get(i);
					}

					// set the new parent edge to the nth target of the old parent source 
					parentNode = childrenEdges.get(i).getTarget();
				}

				// if the new parentNode has not already been discovered, then add it to the discovered hashset and add it to queue.
				if (!visited.contains(parentNode)) {
					visited.add(parentNode);
					queue.append(parentNode);
				}
			}

		}
		// if no edge found, return null
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

		// if the graph is not empty
		if (!(root==null)) {

			// create set to store discovered nodes and stack to store node being examined
			Set<Node> visited = new HashSet<>();
			NodesStackAndQueue stack = new NodesStackAndQueue();
			visited.add(root);
			stack.push(root);

			while(!stack.isEmpty()){

				// set the parent node to the node being examined and the children edges to the edges where the parent node is the source
				Node parentNode = stack.pop();
				EdgesLinkedList childrenEdges = adjacencyMap.get(parentNode);

				// for all the children edges of the parent node, check if the source and target of interest is the source and target of the edge
				for (int i=0; i < (childrenEdges.size()); i++) {
					Node currentChildSource = childrenEdges.get(i).getSource();
					Node currentChildTarget = childrenEdges.get(i).getTarget();

					if ((currentChildSource.equals(source)) && (currentChildTarget.equals(target))) {
						// return the weight of the edge of interest
						return childrenEdges.get(i).getWeight();
					}

					// set the new parent edge to the nth target of the old parent source 
					parentNode = childrenEdges.get(i).getTarget();
				}

				// if the new parentNode has not already been discovered, then add it to the discovered hashset and add it to queue.
				if (!visited.contains(parentNode)) {
					visited.add(parentNode);
					stack.push(parentNode);
				}
			}

		}
		// if no edge found, return null
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
		
		// a HashSet of vertices to be processed  
		Set<Node> notVisited = new HashSet<>();
		
		// HashMaps for nodes in the graph with their distance and previous nodes (respectively) 
		HashMap<Node, Integer> nodeAndDistance = new HashMap<>();
		HashMap<Node, Node> nodeAndPrevious = new HashMap<>();

		// document all the nodes in a graph, and add a buffer node to the top
		NodesStackAndQueue graphNodes = getAllTargetNodesFromAdjacencyMap();
		for (Node sourceNode : adjacencyMap.keySet()) {
			graphNodes.append(sourceNode);
		}
		graphNodes.push(new Node("buffer"));

		// add all the nodes to the HashMaps, along with initialized values of distances = infinity, previous = null
		for (int i = 0; i < graphNodes.getCount(); i++) {
			graphNodes.pop();
			if (!nodeAndDistance.containsKey(graphNodes.peek())) {
				nodeAndDistance.put(graphNodes.peek(), Integer.MAX_VALUE);
				nodeAndPrevious.put(graphNodes.peek(), null);

				notVisited.add(graphNodes.peek());

			}
		}
		
		// if asking for path from a node to itself (self loop)
		if (source.equals(target)) {
			List<Node> pathList = new ArrayList<> ();
			
			pathList.add(source);
			pathList.add(target);
			
			// regardless of whether graph has a weighted self loop return node -> node with weight 0
			return new Path(0, pathList);
		}
		
		// set the source node's distance to 0
		nodeAndDistance.put(source, 0);

		// Initialize the leadNode: the lead vertex for the current iteration
		Node leadNode = null;

		while(!notVisited.isEmpty()) {

			// set the lead node to the node with the smallest distance
			int smallestDistance = Integer.MAX_VALUE;
			for (Node nodeBeingChecked : notVisited) {
				if (nodeAndDistance.get(nodeBeingChecked) < smallestDistance) {
					smallestDistance = nodeAndDistance.get(nodeBeingChecked);
					leadNode = nodeBeingChecked;
				}
			}

			// mark lead node as visited
			notVisited.remove(leadNode);

			// relax edges where lead node is the source edge if applies (check using helper method)
			if (adjacencyMap.containsKey(leadNode)) {
				for(int currentTargetIndex=0; currentTargetIndex < adjacencyMap.get(leadNode).size(); currentTargetIndex++) {

					int newDistance = checkIfRelaxationNecessary(leadNode, currentTargetIndex, nodeAndDistance, smallestDistance);

					if (newDistance != -1) {
						nodeAndDistance.put(adjacencyMap.get(leadNode).get(currentTargetIndex).getTarget(), newDistance);
						nodeAndPrevious.put(adjacencyMap.get(leadNode).get(currentTargetIndex).getTarget(), leadNode);
					}
				}
			}
		}

		// return the path found using the helper method
		return new Path(nodeAndDistance.get(target), constructPath(source, target, nodeAndPrevious));

	}


	/**
	 * Check if relaxation is necessary, i.e. if the distance found is smaller 
	 * than the node's existing distance.
	 * 
	 * @param leadNode
	 * @param currentTargetIndex
	 * @param nodeAndDistance
	 * @param smallestDistance
	 * @return smallest distance value, if it is smaller than current distance, or -1 if smallest distance is greater than current distance
	 */
	protected int checkIfRelaxationNecessary(Node leadNode, int currentTargetIndex, HashMap<Node, Integer> nodeAndDistance, int smallestDistance) {
		// calculate new distance by adding the lead node's distance to the weight of the edge to the target node.
		int newDistance = smallestDistance + adjacencyMap.get(leadNode).get(currentTargetIndex).getWeight();
		
		// if the calculated distance is less than the distance stored, return new distance
		if (newDistance < nodeAndDistance.get(adjacencyMap.get(leadNode).get(currentTargetIndex).getTarget())) {
			return newDistance;
		}

		return -1;
	}

	/**
	 * Construct the path using previousNodes stored in the hashmap
	 * 
	 * @param source
	 * @param target
	 * @param nodeAndPrevious
	 * @return a list of the nodes making up the shortest path 
	 */
	protected List<Node> constructPath(Node source, Node target, HashMap<Node, Node> nodeAndPrevious) {
		// add the target/final step to the returned list first
		List<Node> returnedListForPath = new ArrayList<> ();
		returnedListForPath.add(target);
		Node nodeStep = target;
		
		// add each previous step obtained from the HashMap to index one, to the start of the list.
		while (!nodeStep.equals(source)) {
			returnedListForPath.add(0, nodeAndPrevious.get(nodeStep));
			nodeStep = nodeAndPrevious.get(nodeStep);

		}
		
		return returnedListForPath;
	}

	/**
	 * Gather all the target nodes from the adjacency map
	 * 
	 * @return a NodesStackAndQueue of all the source nodes in the graph
	 */
	protected NodesStackAndQueue getAllTargetNodesFromAdjacencyMap() {
		
		// create returned list and already added to keep track of nodes
		NodesStackAndQueue returnedList = new NodesStackAndQueue();
		Set<Node> alreadyAdded = new HashSet<>();

		for (Node key : adjacencyMap.keySet()) {
			
			// loop through edgesLinkedList in adjacencyMap and if the target node isn't in the list, add it
			EdgesLinkedList currentLinkedList = adjacencyMap.get(key);
			for (int i=0; i < (currentLinkedList.size()); i++) {
				if (!alreadyAdded.contains(adjacencyMap.get(key).get(i).getTarget())) {
					returnedList.append(adjacencyMap.get(key).get(i).getTarget());
					alreadyAdded.add(adjacencyMap.get(key).get(i).getTarget());
				}

			}
		}
		// return list of nodes
		return returnedList;
	}

}

