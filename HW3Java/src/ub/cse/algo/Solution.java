package ub.cse.algo;

import com.sun.org.apache.xpath.internal.operations.Bool;

import java.util.*;

public class Solution {
	private int startNode;
	private HashMap<Integer, ArrayList<Integer>> graph;
	private int[] output;


	class Edge {
		int from;
		int to;

		public Edge(int f, int t)
		{
			this.to = t;
			this.from = f;
		}
	}

	public Solution(int node, HashMap<Integer, ArrayList<Integer>> g) {

		startNode = node;
		graph = g;
		output = new int[g.size()];

		HashSet<Integer> nodesVisited = new HashSet<>();
		HashMap<Integer, List<Edge>> edges = new HashMap<>();
		HashMap<Integer, HashSet<Edge>> edgesVisited = new HashMap<>();
		HashSet<Edge> edgeSet;
		Queue<Integer> todoQueue = new LinkedList<>();

		ArrayList<Integer> eList = new ArrayList<>();
		for (int i = 0; i < g.size(); i++)
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

	public int[] outputDistances() {
		for (int i = 0; i < graph.size(); i++)
		{
			if (i != startNode && output[i] == 0)
				output[i] = -1;
		}

		return output;
	}
}
