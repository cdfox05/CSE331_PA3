package ub.cse.algo;

import com.sun.org.apache.xpath.internal.operations.Bool;

import java.util.*;

public class Solution {
	private int startNode;
	private HashMap<Integer, ArrayList<Integer>> graph;
	private int[] output;


	public Solution(int node, HashMap<Integer, ArrayList<Integer>> g) {

		startNode = node;
		graph = g;
		output = new int[g.size()];

		HashSet<Integer> nodesVisited = new HashSet<>();
		HashMap<Integer, HashSet<Integer>> edgesVisited = new HashMap<>();
		HashSet<Integer> edgeSet;
		Queue<Integer> todoQueue = new LinkedList<>();


		nodesVisited.add(node); //adds first node as visited
		todoQueue.add(node);

		for (int i = 0; i < g.size(); i++)
			output[i] = 0;

		while (!todoQueue.isEmpty())
		{
			node = todoQueue.poll();

			for (int e: g.get(node))
			{
				if (!nodesVisited.contains(e)) {
					todoQueue.add(e);
					nodesVisited.add(e);
					output[e] += 1;
				}

			}
		}


	}

	public int[] outputDistances() {
		return output;
	}
}
