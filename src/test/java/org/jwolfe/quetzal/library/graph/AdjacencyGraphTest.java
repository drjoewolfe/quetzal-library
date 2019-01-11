package org.jwolfe.quetzal.library.graph;

import org.junit.jupiter.api.Test;
import org.jwolfe.quetzal.library.general.Pair;
import org.jwolfe.quetzal.library.utilities.Utilities;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class AdjacencyGraphTest {

    @Test
    void BFS() {
        System.out.println();

        AdjacencyGraph g = new AdjacencyGraph(4);
        g.addEdge(0, 1);
        g.addEdge(0, 2);
        g.addEdge(1, 2);
        g.addEdge(2, 0);
        g.addEdge(2, 3);
        g.addEdge(3, 3);

        g.BFS(2);
    }

    @Test
    void DFS() {
        System.out.println();

        AdjacencyGraph g = new AdjacencyGraph(4);
        g.addEdge(0, 1);
        g.addEdge(0, 2);
        g.addEdge(1, 2);
        g.addEdge(2, 0);
        g.addEdge(2, 3);
        g.addEdge(3, 3);

        g.DFS(2);
    }

    @Test
    void iterativeDFS() {
        AdjacencyGraph graph = new AdjacencyGraph(5);
        graph.addEdge(1, 0);
        graph.addEdge(2, 1);
        graph.addEdge(3, 4);
        graph.addEdge(4, 0);
        graph.iterativeDFS();

        graph = new AdjacencyGraph(5);
        graph.addEdge(1, 0);
        graph.addEdge(0, 2);
        graph.addEdge(2, 1);
        graph.addEdge(0, 3);
        graph.addEdge(1, 4);
        graph.iterativeDFS();

        graph = new AdjacencyGraph(5);
        graph.addEdge(0, 3);
        graph.addEdge(3, 4);
        graph.addEdge(4, 0);
        graph.addEdge(0, 2);
        graph.addEdge(2, 1);
        graph.addEdge(1, 0);
        graph.iterativeDFS();
    }

    @Test
    void isDirectedCyclic() {
        System.out.println();

        AdjacencyGraph g = new AdjacencyGraph(4);
        g.addEdge(0, 1);
        g.addEdge(0, 2);
        g.addEdge(1, 2);
        g.addEdge(2, 0);
        g.addEdge(2, 3);
        g.addEdge(3, 3);

        boolean isCyclic = g.isDirectedCyclic();
        System.out.println(isCyclic);

        g = new AdjacencyGraph(4);
        g.addEdge(0, 1);
        g.addEdge(0, 2);
        g.addEdge(1, 2);
        g.addEdge(2, 3);

        isCyclic = g.isDirectedCyclic();
        System.out.println(isCyclic);
    }

    @Test
    void isUndirectedCyclic() {
        System.out.println();

        AdjacencyGraph g = new AdjacencyGraph(4);
        g.addEdge(0, 1);
        g.addEdge(0, 2);
        g.addEdge(1, 2);
        g.addEdge(2, 0);
        g.addEdge(2, 3);
        g.addEdge(3, 3);

        boolean isCyclic = g.isUndirectedCyclic();
        System.out.println(isCyclic);

        g = new AdjacencyGraph(4);
        g.addEdge(0, 1);
        g.addEdge(0, 2);
        g.addEdge(1, 2);
        g.addEdge(2, 3);

        isCyclic = g.isUndirectedCyclic();
        System.out.println(isCyclic);

        g = new AdjacencyGraph(5);
        g.addEdge(1, 0);
        g.addEdge(0, 2);
        g.addEdge(2, 0);
        g.addEdge(0, 3);
        g.addEdge(3, 4);

        isCyclic = g.isUndirectedCyclic();
        System.out.println(isCyclic);

        g = new AdjacencyGraph(3);
        g.addEdge(0, 1);
        g.addEdge(1, 2);

        isCyclic = g.isUndirectedCyclic();
        System.out.println(isCyclic);
    }

    @Test
    void printSCC() {
        // Create a graph given in the above diagram
        AdjacencyGraph g = new AdjacencyGraph(5);
        g.addEdge(1, 0);
        g.addEdge(0, 2);
        g.addEdge(2, 1);
        g.addEdge(0, 3);
        g.addEdge(3, 4);

        System.out.println("Following are strongly connected components in the graph ");
        g.printSCC();
    }

    @Test
    void isTree() {
        // Create a graph given in the above diagram
        AdjacencyGraph graph;
        boolean isTree;

        graph = new AdjacencyGraph(5);
        graph.addUndirectedEdge(1, 0);
        graph.addUndirectedEdge(0, 2);
        graph.addUndirectedEdge(0, 3);
        graph.addUndirectedEdge(3, 4);
        isTree = graph.isTree();
        System.out.println(isTree);
        assertTrue(isTree);

        graph = new AdjacencyGraph(5);
        graph.addUndirectedEdge(1, 0);
        graph.addUndirectedEdge(0, 2);
        graph.addUndirectedEdge(2, 1);
        graph.addUndirectedEdge(0, 3);
        graph.addUndirectedEdge(3, 4);
        isTree = graph.isTree();
        System.out.println(isTree);
        assertFalse(isTree);
    }

    @Test
    void isInSamePath() {
        AdjacencyGraph graph = new AdjacencyGraph(10);
        graph.addEdge(1, 2);
        graph.addEdge(1, 3);
        graph.addEdge(3, 6);
        graph.addEdge(2, 4);
        graph.addEdge(2, 5);
        graph.addEdge(5, 7);
        graph.addEdge(5, 8);
        graph.addEdge(5, 9);

        boolean inPath;

        inPath = graph.isInSamePath(1, 5);
        assertTrue(inPath);

        inPath = graph.isInSamePath(2, 9);
        assertTrue(inPath);

        inPath = graph.isInSamePath(2, 6);
        assertFalse(inPath);
    }

    @Test
    void findMotherVertex() {
        AdjacencyGraph graph = new AdjacencyGraph(7);
        graph.addEdge(0, 1);
        graph.addEdge(0, 2);
        graph.addEdge(1, 3);
        graph.addEdge(4, 1);
        graph.addEdge(6, 4);
        graph.addEdge(5, 6);
        graph.addEdge(5, 2);
        graph.addEdge(6, 0);

        int motherVertex = graph.findMotherVertex();
        System.out.println();
        System.out.println("Mother Vertex: " + motherVertex);
        assertEquals(5, motherVertex);
    }

    @Test
    void getBridges() {
        AdjacencyGraph graph;
        List<Pair<Integer, Integer>> bridges;

        graph = new AdjacencyGraph(5);
        graph.addEdge(1, 0);
        graph.addEdge(0, 2);
        graph.addEdge(2, 1);
        graph.addEdge(0, 3);
        graph.addEdge(3, 4);
        bridges = graph.getBridges();
        Utilities.printPairList(bridges);
        assertTrue(bridges.contains(new Pair(3, 4)));
        assertTrue(bridges.contains(new Pair(0, 3)));

        graph = new AdjacencyGraph(4);
        graph.addEdge(0, 1);
        graph.addEdge(1, 2);
        graph.addEdge(2, 3);
        bridges = graph.getBridges();
        Utilities.printPairList(bridges);
        assertTrue(bridges.contains(new Pair(2, 3)));
        assertTrue(bridges.contains(new Pair(1, 2)));
        assertTrue(bridges.contains(new Pair(0, 1)));

        graph = new AdjacencyGraph(7);
        graph.addEdge(0, 1);
        graph.addEdge(1, 2);
        graph.addEdge(2, 0);
        graph.addEdge(1, 3);
        graph.addEdge(1, 4);
        graph.addEdge(1, 6);
        graph.addEdge(3, 5);
        graph.addEdge(4, 5);
        bridges = graph.getBridges();
        Utilities.printPairList(bridges);
        assertTrue(bridges.contains(new Pair(1, 6)));
    }

    @Test
    void colorGraph() {
        AdjacencyGraph graph;
        List<Pair<Integer, Integer>> colorAssignments;

        graph = new AdjacencyGraph(5);
        graph.addUndirectedEdge(0, 1);
        graph.addUndirectedEdge(0, 2);
        graph.addUndirectedEdge(1, 2);
        graph.addUndirectedEdge(1, 3);
        graph.addUndirectedEdge(2, 3);
        graph.addUndirectedEdge(3, 4);
        colorAssignments = graph.colorGraph();
        Utilities.printPairList(colorAssignments);

        System.out.println();
        graph = new AdjacencyGraph(5);
        graph.addUndirectedEdge(0, 1);
        graph.addUndirectedEdge(0, 2);
        graph.addUndirectedEdge(1, 2);
        graph.addUndirectedEdge(1, 4);
        graph.addUndirectedEdge(2, 4);
        graph.addUndirectedEdge(4, 3);
        colorAssignments = graph.colorGraph();
        Utilities.printPairList(colorAssignments);
    }

    @Test
    void getNumberOfTriangles() {
        AdjacencyGraph graph;
        int numTriangles;

        graph = Utilities.constructUndirectedGraphFromAdjacencyArray(new int[][]{
                {0, 1, 1, 0},
                {1, 0, 1, 1},
                {1, 1, 0, 1},
                {0, 1, 1, 0}});
        numTriangles = graph.getNumberOfTriangles();
        assertEquals(2, numTriangles);
    }

    @Test
    void getAllPathsFromSourceToDestination() {
        AdjacencyGraph graph;
        List<List<Integer>> allPaths;
        List<List<Integer>> expectedPaths;

        graph = new AdjacencyGraph(4);
        graph.addEdge(0, 1);
        graph.addEdge(0, 2);
        graph.addEdge(0, 3);
        graph.addEdge(2, 0);
        graph.addEdge(2, 1);
        graph.addEdge(1, 3);
        expectedPaths = Utilities.constructList(Utilities.constructList(2, 0, 1, 3),
                Utilities.constructList(2, 0, 3),
                Utilities.constructList(2, 1, 3));
        allPaths = graph.getAllPathsFromSourceToDestination(2, 3);
        assertEquals(expectedPaths.size(), allPaths.size());
        for (var path : expectedPaths) {
            assertTrue(allPaths.contains(path));
        }
    }
}