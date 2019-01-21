package org.jwolfe.quetzal.library.graph;

import org.jwolfe.quetzal.library.general.IntPair;
import org.jwolfe.quetzal.library.heap.MinHeap;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

public class WeightedAdjacencyGraph {
    public int getVertexCount() {
        return vertexCount;
    }

    int vertexCount;

    public LinkedList<IntPair>[] getAdjacencyList() {
        return adjacencyList;
    }

    LinkedList<IntPair>[] adjacencyList;


    public WeightedAdjacencyGraph(int numOfVertices) {
        vertexCount = numOfVertices;
        adjacencyList = new LinkedList[vertexCount];

        for (int i = 0; i < vertexCount; i++) {
            adjacencyList[i] = new LinkedList<>();
        }
    }

    public void addEdge(int u, int v) {
        addEdge(u, v, 1);
    }

    public void addUndirectedEdge(int u, int v) {
        addUndirectedEdge(u, v, 1);
    }

    public void addEdge(int u, int v, int w) {
        if (u >= vertexCount
                || v >= vertexCount)
            return;

        adjacencyList[u].add(new IntPair(v, w));
    }

    public void addUndirectedEdge(int u, int v, int w) {
        if (u >= vertexCount
                || v >= vertexCount)
            return;

        adjacencyList[u].add(new IntPair(v, w));
        adjacencyList[v].add(new IntPair(u, w));
    }

    public Map<Integer, Integer> dijkstrasShortestPaths(int startVertex) {

        class VertexDistance implements Comparable {
            int vertex;

            int distance;

            VertexDistance(int vertex, int distance) {
                this.vertex = vertex;
                this.distance = distance;
            }

            @Override
            public int compareTo(Object o) {
                VertexDistance vd = (VertexDistance) o;
                return this.distance - vd.distance;
            }
        }

        if (startVertex < 0 | startVertex >= vertexCount) {
            return null;
        }

        Map<Integer, Integer> shortestDistances = new HashMap<>();
        Map<Integer, Integer> vertexIndexesInHeap = new HashMap<>();
        MinHeap<VertexDistance> heap = new MinHeap<>(vertexCount, (vd, i) -> vertexIndexesInHeap.put(vd.vertex, i));

        for (int i = 0; i < vertexCount; i++) {
            int distance = Integer.MAX_VALUE;
            if (i == startVertex) {
                // Mark start-vertex
                distance = 0;
            }

            var vd = new VertexDistance(i, distance);

            shortestDistances.put(i, distance);
            heap.insert(vd);
        }

        while (!heap.isEmpty()) {
            var vd = heap.extractMin();
            int u = vd.vertex;
            int d = shortestDistances.get(u);

            for (var adjacentVertex : adjacencyList[u]) {
                int v = adjacentVertex.getA();
                int w = adjacentVertex.getB();

                int currentDistance = shortestDistances.get(v);
                if (d != Integer.MAX_VALUE && currentDistance > d + w) {
                    currentDistance = d + w;
                    shortestDistances.put(v, currentDistance);

                    int index = vertexIndexesInHeap.get(v);
                    var vvd = heap.getAtIndex(index);
                    vvd.distance = currentDistance;
                    heap.decreaseKey(index, vvd);
                }
            }
        }

        return shortestDistances;
    }
}
