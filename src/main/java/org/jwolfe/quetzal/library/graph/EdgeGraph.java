package org.jwolfe.quetzal.library.graph;

import java.util.Arrays;

public class EdgeGraph {
	int vertexCount;
	int edgeCount;

	Edge[] edges;

	public class Edge implements Comparable<Edge> {
		public int getSource() {
			return source;
		}

		public void setSource(int source) {
			this.source = source;
		}

		int source;

		public int getDestination() {
			return destination;
		}

		public void setDestination(int destination) {
			this.destination = destination;
		}

		int destination;

		public int getWeight() {
			return weight;
		}

		public void setWeight(int weight) {
			this.weight = weight;
		}

		int weight;

		public Edge() {

		}

		public Edge(int source, int destination) {
			this();
			this.source = source;
			this.destination = destination;
		}

		public Edge(int source, int destination, int weight) {
			this(source, destination);
			this.weight = weight;
		}

		@Override
		public int compareTo(Edge o) {
			return this.weight - o.weight;
		}
	}

	public EdgeGraph(int vertexCount, int edgeCount) {
		this.vertexCount = vertexCount;
		this.edgeCount = edgeCount;

		edges = new Edge[edgeCount];
		for (int i = 0; i < edgeCount; i++) {
			edges[i] = new Edge(-1, -1);
		}
	}

	public Edge addEdge(int source, int destination) {
		int index;
		for (index = 0; index < edges.length; index++) {
			if (edges[index] == null || edges[index].source == -1) {
				break;
			}
		}

		if (index == edges.length) {
			return null;
		}

		Edge edge = new Edge();
		edge.source = source;
		edge.destination = destination;

		edges[index] = edge;
		return edge;
	}

	public Edge addEdge(int source, int destination, int weight) {
		var edge = addEdge(source, destination);
		edge.weight = weight;
		return edge;
	}

	public boolean hascycle() {
		// Union Find
		int[] parent = new int[vertexCount];
		Arrays.fill(parent, -1);

		for (int i = 0; i < edgeCount; i++) {
			int x = find(parent, edges[i].source);
			int y = find(parent, edges[i].destination);

			if (x == y) {
				return true;
			}

			union(parent, edges[i].source, edges[i].destination);
		}

		return false;
	}

	private int find(int[] parent, int vertex) {
		if (parent[vertex] == -1) {
			return vertex;
		}

		return find(parent, parent[vertex]);
	}

	private void union(int[] parent, int vertex1, int vertex2) {
		int xSet = find(parent, vertex1);
		int ySet = find(parent, vertex2);

		parent[xSet] = ySet;
	}

	public void kruskalMST() {
		Edge[] mstEdges = new Edge[vertexCount - 1];
		int[] parent = new int[vertexCount];
		Arrays.fill(parent, -1);
		Arrays.sort(edges);

		int mstIndex = 0;
		int edgeIndex = 0;

		while (mstIndex < vertexCount - 1) {
			Edge edge = edges[edgeIndex];
			edgeIndex++;

			int x = find(parent, edge.source);
			int y = find(parent, edge.destination);
			if (x != y) {
				// Taking this edge does not cause a cycle.
				mstEdges[mstIndex] = edge;
				mstIndex++;

				union(parent, edge.source, edge.destination);
			}
		}

		for (int i = 0; i < mstEdges.length; i++) {
			var edge = mstEdges[i];
			System.out.println((i + 1) + ":\t" + edge.source + "\t->\t" + edge.destination + "\t(" + edge.weight + ")");
		}
	}

	public int[] bellmanFord(int source) {
		// Shortest paths from source to every other vertex
		int[] distances = new int[vertexCount];
		Arrays.fill(distances, Integer.MAX_VALUE);
		distances[source] = 0;

		// Loop |V-1| times
		for (int i = 0; i < vertexCount - 1; i++) {
			for (Edge e : edges) {
				var u = e.source;
				var v = e.destination;
				var w = e.weight;

				if (distances[u] != Integer.MAX_VALUE && distances[u] + w < distances[v]) {
					distances[v] = distances[u] + w;
				}
			}
		}

		return distances;
	}
}
