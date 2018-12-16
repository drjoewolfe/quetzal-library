package org.jwolfe.quetzal.library.graph;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class EdgeGraphTest {

    @Test
    void hasCycle() {
        int v, e;
        EdgeGraph graph;

        v = 3;
        e = 3;
        graph = new EdgeGraph(v, e);
        // add edge 0-1
        graph.edges[0].source = 0;
        graph.edges[0].destination = 1;
        // add edge 1-2
        graph.edges[1].source = 1;
        graph.edges[1].destination = 2;
        // add edge 0-2
        graph.edges[2].source = 0;
        graph.edges[2].destination = 2;
        boolean hasCycle = graph.hascycle();
        if (hasCycle) {
            System.out.println("Graph contains cycle");
        }
        else {
            System.out.println("Graph doesn't contain cycle");
        }

        assertEquals(true, hasCycle);
    }

    @Test
    void kruskalMST() {
        int v, e;
        EdgeGraph graph;

        /* Let us create following weighted graph
                 10
            0--------1
            |  \     |
           6|   5\   |15
            |      \ |
            2--------3
                4       */
        v = 4;  // Number of vertices in graph
        e = 5;  // Number of edges in graph
        graph = new EdgeGraph(v, e);

        // add edge 0-1
        graph.edges[0].source = 0;
        graph.edges[0].destination = 1;
        graph.edges[0].weight = 10;

        // add edge 0-2
        graph.edges[1].source = 0;
        graph.edges[1].destination = 2;
        graph.edges[1].weight = 6;

        // add edge 0-3
        graph.edges[2].source = 0;
        graph.edges[2].destination = 3;
        graph.edges[2].weight = 5;

        // add edge 1-3
        graph.edges[3].source = 1;
        graph.edges[3].destination = 3;
        graph.edges[3].weight = 15;

        // add edge 2-3
        graph.edges[4].source = 2;
        graph.edges[4].destination = 3;
        graph.edges[4].weight = 4;

        graph.kruskalMST();
    }
}