package org.jwolfe.quetzal.library.graph;

import org.jwolfe.quetzal.library.general.IntPair;
import org.jwolfe.quetzal.library.heap.MinHeap;

import java.util.*;

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

    public List<IntPair> primMST() {

        class VertexKey implements Comparable {
            int vertex;

            int key;

            VertexKey(int vertex, int key) {
                this.vertex = vertex;
                this.key = key;
            }

            @Override
            public int compareTo(Object o) {
                VertexKey vk = (VertexKey) o;
                return this.key - vk.key;
            }
        }

        int[] parent = new int[vertexCount];
        int[] keys = new int[vertexCount];
        Map<Integer, Integer> vertexIndexesInHeap = new HashMap<>();
        MinHeap<VertexKey> heap = new MinHeap<>(vertexCount, (vk, i) -> vertexIndexesInHeap.put(vk.vertex, i));

        for (int i = 0; i < vertexCount; i++) {
            int key = Integer.MAX_VALUE;
            if (i == 0) {
                // Mark start-vertex
                key = 0;
            }

            var vk = new VertexKey(i, key);

            parent[i] = -1;
            keys[i] = key;
            heap.insert(vk);
        }

        while (!heap.isEmpty()) {
            var vk = heap.extractMin();
            int u = vk.vertex;

            for (var adjacentVertex : adjacencyList[u]) {
                int v = adjacentVertex.getA();
                int w = adjacentVertex.getB();

                int heapIndex = vertexIndexesInHeap.get(v);

                if (heapIndex >= 0 && keys[v] > w) {
                    keys[v] = w;
                    parent[v] = u;

                    var vvk = heap.getAtIndex(heapIndex);
                    vvk.key = w;
                    heap.decreaseKey(heapIndex, vvk);
                }
            }
        }

        List<IntPair> mst = new ArrayList<>();
        for (int i = 1; i < vertexCount; i++) {
            mst.add(new IntPair(parent[i], i));
        }

        return mst;
    }
}
