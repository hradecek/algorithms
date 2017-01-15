package com.hradecek.graph.dijkstra;

import org.jgrapht.DirectedGraph;
import org.jgrapht.Graph;
import org.jgrapht.alg.util.Pair;
import org.jgrapht.graph.DefaultWeightedEdge;
import org.jgrapht.graph.SimpleDirectedWeightedGraph;
import org.junit.Before;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

public class DijkstraTestCase {


    /**
     *
     */
    private Map<String, List<Pair<String, Integer>>> mapRepresentation = new HashMap<String, List<Pair<String, Integer>>>() {{
        put("A", Arrays.asList(
                Pair.of("B", 20), Pair.of("D", 80), Pair.of("G", 90))
        );
        put("B",
                Collections.singletonList(Pair.of("F", 10))
        );
        put("C",
                Arrays.asList(Pair.of("D", 10), Pair.of("F", 50), Pair.of("H", 20))
        );
        put("D",
                Arrays.asList(Pair.of("C", 10), Pair.of("G", 20))
        );
        put("E",
                Arrays.asList(Pair.of("B", 50), Pair.of("G", 30))
        );
        put("F",
                Arrays.asList(Pair.of("D", 40), Pair.of("C", 10))
        );
        put("G",
                Collections.singletonList(Pair.of("A", 20))
        );
        put("H",
                Collections.emptyList()
        );
    }};

    /**
     *
     */
    private SimpleDirectedWeightedGraph graph;

    @Before
    public void init() {
        this.graph = initGraph();
    }

    private SimpleDirectedWeightedGraph<String, DefaultWeightedEdge> initGraph() {
        SimpleDirectedWeightedGraph<String, DefaultWeightedEdge> graph = new SimpleDirectedWeightedGraph<>(DefaultWeightedEdge.class);

        // Add vertices
        mapRepresentation.keySet().forEach(graph::addVertex);

        // For each entry of mapRepresentation
        for (Map.Entry<String, List<Pair<String, Integer>>> entry : mapRepresentation.entrySet()) {
            // Starting vertex
            String from = entry.getKey();
            // Add all adjacent vertices together with edge weight
            for (Pair<String, Integer> to : entry.getValue()) {
                DefaultWeightedEdge edge = graph.addEdge(from, to.first);
                graph.setEdgeWeight(edge, to.second);
            }
        }

        return graph;
    }

    @Test
    public void testFindShortestPathBetween() {
        assertTrue(true);
    }

    @Test
    public void testFindShortestPathBetweenWeight() {
        // The weight of the shortest path A ~> H is equal to 60
        assertEquals(60, Dijkstra.findPathBetween(graph, "A", "H").getWeight(), 0.001);
        // There is no such a path from A ~> E
        assertTrue(null == Dijkstra.findPathBetween(graph, "A", "E"));
    }
}
