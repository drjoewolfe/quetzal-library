package org.jwolfe.quetzal.library.graph;

import org.junit.jupiter.api.Test;
import org.jwolfe.quetzal.library.general.IntPair;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class WeightedAdjacencyGraphTest {
    @Test
    void dijkstrasShortestPaths() {
        WeightedAdjacencyGraph graph;
        Map<Integer, Integer> shortestPathLengths;
        Map<Integer, Integer> expectedShortestPathLengths;

        graph = new WeightedAdjacencyGraph(9);
        graph.addUndirectedEdge(0, 1, 4);
        graph.addUndirectedEdge(0, 7, 8);
        graph.addUndirectedEdge(1, 2, 8);
        graph.addUndirectedEdge(1, 7, 11);
        graph.addUndirectedEdge(2, 3, 7);
        graph.addUndirectedEdge(2, 8, 2);
        graph.addUndirectedEdge(2, 5, 4);
        graph.addUndirectedEdge(3, 4, 9);
        graph.addUndirectedEdge(3, 5, 14);
        graph.addUndirectedEdge(4, 5, 10);
        graph.addUndirectedEdge(5, 6, 2);
        graph.addUndirectedEdge(6, 7, 1);
        graph.addUndirectedEdge(6, 8, 6);
        graph.addUndirectedEdge(7, 8, 7);

        expectedShortestPathLengths = new HashMap<>();
        expectedShortestPathLengths.put(0, 0);
        expectedShortestPathLengths.put(1, 4);
        expectedShortestPathLengths.put(2, 12);
        expectedShortestPathLengths.put(3, 19);
        expectedShortestPathLengths.put(4, 21);
        expectedShortestPathLengths.put(5, 11);
        expectedShortestPathLengths.put(6, 9);
        expectedShortestPathLengths.put(7, 8);
        expectedShortestPathLengths.put(8, 14);

        shortestPathLengths = graph.dijkstrasShortestPaths(0);

        assertNotNull(shortestPathLengths);
        assertEquals(expectedShortestPathLengths.size(), shortestPathLengths.size());
        for (var key : expectedShortestPathLengths.keySet()) {
            assertTrue(shortestPathLengths.containsKey(key));
            assertEquals(expectedShortestPathLengths.get(key), shortestPathLengths.get(key));
        }
    }

    @Test
    void primMST() {
        WeightedAdjacencyGraph graph;
        List<IntPair> mst;
        List<IntPair> expectedMst;

        graph = new WeightedAdjacencyGraph(9);
        graph.addUndirectedEdge(0, 1, 4);
        graph.addUndirectedEdge(0, 7, 8);
        graph.addUndirectedEdge(1, 2, 8);
        graph.addUndirectedEdge(1, 7, 11);
        graph.addUndirectedEdge(2, 3, 7);
        graph.addUndirectedEdge(2, 8, 2);
        graph.addUndirectedEdge(2, 5, 4);
        graph.addUndirectedEdge(3, 4, 9);
        graph.addUndirectedEdge(3, 5, 14);
        graph.addUndirectedEdge(4, 5, 10);
        graph.addUndirectedEdge(5, 6, 2);
        graph.addUndirectedEdge(6, 7, 1);
        graph.addUndirectedEdge(6, 8, 6);
        graph.addUndirectedEdge(7, 8    , 7);

        expectedMst = new ArrayList<>();
        expectedMst.add(new IntPair(0, 1));
        expectedMst.add(new IntPair(5, 2));
        expectedMst.add(new IntPair(2, 3));
        expectedMst.add(new IntPair(3, 4));
        expectedMst.add(new IntPair(6, 5));
        expectedMst.add(new IntPair(7, 6));
        expectedMst.add(new IntPair(0, 7));
        expectedMst.add(new IntPair(2, 8));

        mst = graph.primMST();

        assertNotNull(mst);
        assertEquals(expectedMst.size(), mst.size());
        for (var pair : expectedMst) {
            assertTrue(mst.contains(pair));
        }
    }
}