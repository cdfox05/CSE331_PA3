package ub.cse.algo;

import java.util.*;

public class Solution {
	private int startNode;
	private HashMap<Integer, ArrayList<Integer>> graph;
	private int[] output;


	class Edge { //Edge to make traversal and checking easier
		int from;
		int to;

		public Edge(int f, int t)
		{
			this.to = t;
			this.from = f;
		}
	}

	//Used Textbook and Slides/331 Notes

	public Solution(int node, HashMap<Integer, ArrayList<Integer>> g) {

		startNode = node;
		graph = g;
		output = new int[g.size()];

		HashSet<Integer> nodesVisited = new HashSet<>(); //hashset for immediate contains
		HashMap<Integer, List<Edge>> edges = new HashMap<>();
		HashMap<Integer, HashSet<Edge>> edgesVisited = new HashMap<>();
		HashSet<Edge> edgeSet;
		Queue<Integer> todoQueue = new LinkedList<>(); //queue for nodes

		ArrayList<Integer> eList;
		for (int i = 0; i < g.size(); i++) //sets all vals in output to 0 and sets up all edges
		{
			output[i] = 0;
			eList = g.get(i);
			for (int j = 0; j < eList.size(); j++) {
				if (!edges.containsKey(i)) {
					edges.put(i, new ArrayList<>());
				}
				edges.get(i).add(new Edge(i,eList.get(j)));
			}
		}

		nodesVisited.add(node); //adds first node as visited

		todoQueue.add(node);

		while (!todoQueue.isEmpty())
		{
			node = todoQueue.poll();

			if (!edgesVisited.containsKey(node))
				edgesVisited.put(node, new HashSet<>());

			edgeSet = edgesVisited.get(node);

			for (Edge e: edges.get(node))
			{
				if (!edgeSet.contains(e))
				{
					int w = e.to;
					if (!nodesVisited.contains(w))
					{
						nodesVisited.add(w);
						edgeSet.add(e);
						output[w] = output[e.from] + 1;

						todoQueue.add(w);

					}
				}
			}
		}


	}

	public int[] outputDistances() { //checks for any value that could not be visited from start node
		for (int i = 0; i < graph.size(); i++)
		{
			if (i != startNode && output[i] == 0)
				output[i] = -1;
		}

		return output;
	}
}
