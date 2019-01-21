package org.jwolfe.quetzal.library.graph;

import org.jwolfe.quetzal.library.general.Pair;
import org.jwolfe.quetzal.library.matrix.Matrix;
import org.jwolfe.quetzal.library.utilities.Utilities;

import java.security.PublicKey;
import java.util.*;
import java.util.concurrent.atomic.AtomicInteger;

public class AdjacencyGraph {
    public int getVertexCount() {
        return vertexCount;
    }

    int vertexCount;

    public LinkedList<Integer>[] getAdjacencyList() {
        return adjacencyList;
    }

    LinkedList<Integer>[] adjacencyList;

    int[] inTimes;
    int[] outTimes;

    public AdjacencyGraph(int numOfVertices) {
        vertexCount = numOfVertices;
        adjacencyList = new LinkedList[vertexCount];

        for (int i = 0; i < vertexCount; i++) {
            adjacencyList[i] = new LinkedList<>();
        }
    }

    public void addEdge(int u, int v) {
        if (u >= vertexCount
                || v >= vertexCount)
            return;

        adjacencyList[u].add(v);
    }

    public void addUndirectedEdge(int u, int v) {
        if (u >= vertexCount
                || v >= vertexCount)
            return;

        adjacencyList[u].add(v);
        adjacencyList[v].add(u);
    }

    public void BFS(int startVertex) {
        boolean[] visited = new boolean[vertexCount];
        Arrays.fill(visited, false);

        Queue<Integer> queue = new LinkedList<>();
        queue.add(startVertex);

        while (!queue.isEmpty()) {
            int vertex = queue.poll();
            visited[vertex] = true;
            System.out.print(vertex + " ");

            for (int v : adjacencyList[vertex]) {
                if (!visited[v]) {
                    queue.add(v);
                }
            }
        }
    }

    public void DFS(int startVertex) {
        boolean[] visited = new boolean[vertexCount];
        Arrays.fill(visited, false);

        DFSHelper(startVertex, visited);
    }

    private void DFSHelper(int vertex, boolean[] visited) {
        visited[vertex] = true;
        System.out.print(vertex + " ");
        for (int v : adjacencyList[vertex]) {
            if (!visited[v]) {
                DFSHelper(v, visited);
            }
        }
    }

    public void iterativeDFS() {
        boolean[] visited = new boolean[vertexCount];
        Arrays.fill(visited, false);

        for (int i = 0; i < vertexCount; i++) {
            iterativeDFS(i, visited);
        }

        System.out.println();
    }

    private void iterativeDFS(int startVertex, boolean[] visited) {
        Stack<Integer> stack = new Stack<>();
        stack.push(startVertex);

        while (!stack.isEmpty()) {
            int vertex = stack.pop();
            if (!visited[vertex]) {
                visited[vertex] = true;
                System.out.print(vertex + " ");
            }

            for (int child : adjacencyList[vertex]) {
                if (!visited[child]) {
                    stack.push(child);
                }
            }
        }
    }

    public boolean isDirectedCyclic() {
        boolean[] visited = new boolean[vertexCount];
        Arrays.fill(visited, false);

        boolean[] recursionStack = new boolean[vertexCount];
        Arrays.fill(recursionStack, false);

        for (int i = 0; i < vertexCount; i++) {
            if (isDirectedCyclicHelper(i, visited, recursionStack))
                return true;
        }

        return false;
    }

    public boolean isDirectedCyclicHelper(int vertex, boolean[] visited, boolean[] recursionStack) {
        if (visited[vertex])
            return false;

        visited[vertex] = true;
        recursionStack[vertex] = true;

        for (int v : adjacencyList[vertex]) {
            if (!visited[v]
                    && isDirectedCyclicHelper(v, visited, recursionStack))
                return true;
            else if (recursionStack[v])
                return true;

        }

        recursionStack[vertex] = false;
        return false;
    }

    public boolean isUndirectedCyclic() {
        boolean[] visited = new boolean[vertexCount];
        Arrays.fill(visited, false);

        for (int u = 0; u < vertexCount; u++) {
            if (!visited[u]) {
                if (isUndirectedCyclicHelper(u, -1, visited))
                    return true;
            }
        }

        return false;
    }

    public boolean isUndirectedCyclicHelper(int vertex, int parent, boolean[] visited) {
        visited[vertex] = true;

        for (int v : adjacencyList[vertex]) {
            if (!visited[v]) {
                if (isUndirectedCyclicHelper(v, vertex, visited))
                    return true;
            } else if (v != parent)
                return true;
        }

        return false;
    }

    public void printSCC() {
        // Kosaraju's algorithm
        Stack<Integer> stack = new Stack<>();

        boolean[] visited = new boolean[vertexCount];
        Arrays.fill(visited, false);

        for (int i = 0; i < vertexCount; i++) {
            if (!visited[i]) {
                sccFillOrderWithDFS(i, visited, stack);
            }
        }

        Arrays.fill(visited, false);
        var transposeGraph = getTranspose();

        while (!stack.isEmpty()) {
            int u = stack.pop();
            if (!visited[u]) {
                transposeGraph.DFSHelper(u, visited);
                System.out.println();
            }
        }
    }

    private AdjacencyGraph getTranspose() {
        AdjacencyGraph transposeGraph = new AdjacencyGraph(this.vertexCount);
        for (int u = 0; u < vertexCount; u++) {
            var iterator = adjacencyList[u].iterator();
            while (iterator.hasNext()) {
                transposeGraph.adjacencyList[iterator.next()].add(u);
            }
        }

        return transposeGraph;
    }

    private void sccFillOrderWithDFS(int vertex, boolean[] visited, Stack<Integer> stack) {
        visited[vertex] = true;
        for (int i = 0; i < adjacencyList[vertex].size(); i++) {
            int v = adjacencyList[vertex].get(i);
            if (!visited[v]) {
                sccFillOrderWithDFS(v, visited, stack);
            }
        }

        stack.push(vertex);
    }

    public boolean isTree() {
        // An undirected graph is tree if it has following properties.
        //      1) There is no cycle.
        //      2) The graph is connected.

        boolean[] visited = new boolean[vertexCount];
        Arrays.fill(visited, false);
        if (isUndirectedCyclicHelper(0, -1, visited)) {
            return false;
        }

        for (int i = 0; i < visited.length; i++) {
            if (!visited[i]) {
                return false;
            }
        }

        return true;
    }

    public void buildTimings() {
        inTimes = new int[vertexCount];
        outTimes = new int[vertexCount];

        int timeRunner = 0;
        boolean[] visited = new boolean[vertexCount];
        Arrays.fill(visited, false);

        for (int i = 0; i < vertexCount; i++) {
            if (!visited[i]) {
                timeRunner = updateTimers(i, visited, timeRunner);
            }
        }
    }

    private int updateTimers(int vertex, boolean[] visited, int timeRunner) {
        visited[vertex] = true;

        timeRunner++;
        inTimes[vertex] = timeRunner;

        for (var v : adjacencyList[vertex]) {
            if (!visited[v]) {
                timeRunner = updateTimers(v, visited, timeRunner);
            }
            timeRunner++;
        }

        timeRunner++;
        outTimes[vertex] = timeRunner;
        return timeRunner;
    }

    public boolean isInSamePath(int u, int v) {
        if (inTimes == null) {
            buildTimings();
        }

        return ((inTimes[u] < inTimes[v]) && (outTimes[u] > outTimes[v]))
                || ((inTimes[v] < inTimes[u]) && (outTimes[v] > outTimes[u]));
    }

    public int findMotherVertex() {
        boolean[] visited = new boolean[vertexCount];
        Arrays.fill(visited, false);

        int candidateVertex = 0;
        for (int i = 0; i < vertexCount; i++) {
            if (!visited[i]) {
                DFSHelper(i, visited);
                candidateVertex = i;
            }
        }

        Arrays.fill(visited, false);
        DFSHelper(candidateVertex, visited);
        for (int i = 0; i < vertexCount; i++) {
            if (!visited[i]) {
                return -1;
            }
        }

        return candidateVertex;
    }

    public List<Pair<Integer, Integer>> getBridges() {
        List<Pair<Integer, Integer>> bridges = new LinkedList<>();

        boolean[] visited = new boolean[vertexCount];
        int[] discoveryTime = new int[vertexCount];
        int[] low = new int[vertexCount];
        int[] parent = new int[vertexCount];

        Arrays.fill(visited, false);
        Arrays.fill(parent, -1);

        AtomicInteger time = new AtomicInteger(0);

        for (int i = 0; i < vertexCount; i++) {
            if (!visited[i]) {
                getBridges(i, visited, discoveryTime, low, parent, time, bridges);
            }
        }

        return bridges;
    }

    private void getBridges(int vertex, boolean[] visited, int[] discoveryTime, int[] low, int[] parent, AtomicInteger time, List<Pair<Integer, Integer>> bridges) {
        visited[vertex] = true;
        discoveryTime[vertex] = time.getAndIncrement();
        low[vertex] = discoveryTime[vertex];

        for (int child : adjacencyList[vertex]) {
            if (!visited[child]) {
                parent[child] = vertex;
                getBridges(child, visited, discoveryTime, low, parent, time, bridges);

                low[vertex] = Math.min(low[vertex], low[child]);

                if (low[child] > discoveryTime[vertex]) {
                    bridges.add(new Pair<>(vertex, child));
                }
            } else {
                if (child != parent[vertex]) {
                    low[vertex] = Math.min(low[vertex], discoveryTime[child]);
                }
            }
        }
    }

    public List<Pair<Integer, Integer>> colorGraph() {
        int[] assignments = new int[vertexCount];
        boolean[] colorAvailable = new boolean[vertexCount];

        Arrays.fill(assignments, -1);
        assignments[0] = 0;
        for (int u = 1; u < vertexCount; u++) {
            Arrays.fill(colorAvailable, true);

            var adjacentVertices = adjacencyList[u];
            for (var v : adjacentVertices) {
                if (assignments[v] != -1) {
                    colorAvailable[assignments[v]] = false;
                }
            }

            int color = 0;
            for (; color < vertexCount; color++) {
                if (colorAvailable[color]) {
                    break;
                }
            }

            assignments[u] = color;
        }

        List<Pair<Integer, Integer>> colorAssignments = new LinkedList<>();
        for (int u = 0; u < vertexCount; u++) {
            colorAssignments.add(new Pair(u, assignments[u]));
        }

        return colorAssignments;
    }

    public int getNumberOfTriangles() {
        int[][] adjacencyArray = Utilities.constructAdjacencyArrayFromUndirectedGraph(this);

        // Calculate A*A*A
        int[][] adjacencyArray2 = Matrix.multiply(adjacencyArray, adjacencyArray);
        int[][] adjacencyArray3 = Matrix.multiply(adjacencyArray, adjacencyArray2);

        return Matrix.getTrace(adjacencyArray3) / 6;
    }

    public List<List<Integer>> getAllPathsFromSourceToDestination(int source, int destination) {
        if (source < 0 || source >= vertexCount
                || destination < 0 || destination >= vertexCount) {
            return null;
        }

        List<List<Integer>> allPaths = new ArrayList<>();
        List<Integer> currentPath = new ArrayList<>();
        generateAllPathsFromSourceToDestination(source, destination, source, currentPath, allPaths);

        if (allPaths.size() == 0) {
            return null;
        }

        return allPaths;
    }

    private void generateAllPathsFromSourceToDestination(int source, int destination,
                                                         int current, List<Integer> currentPath, List<List<Integer>> allPaths) {
        currentPath.add(current);
        int index = currentPath.size() - 1;

        if (current == destination) {
            allPaths.add(new ArrayList<>(currentPath));
            currentPath.remove(index);
            return;
        }

        for (var next : adjacencyList[current]) {
            if (!currentPath.contains(next)) {
                generateAllPathsFromSourceToDestination(source, destination, next, currentPath, allPaths);
            }
        }

        currentPath.remove(index);
    }
}
