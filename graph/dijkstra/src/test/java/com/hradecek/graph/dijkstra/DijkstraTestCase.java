package com.hradecek.graph.dijkstra;

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
    private Graph graph;

    @Before
    public void init() {
        this.graph = initGraph();
    }

    private Graph initGraph() {
        SimpleDirectedWeightedGraph<String, DefaultWeightedEdge> graph = new SimpleDirectedWeightedGraph<>(DefaultWeightedEdge.class);

        // For each entry of mapRepresentation
        for (Map.Entry<String, List<Pair<String, Integer>>> entry : mapRepresentation.entrySet()) {
            // Add vertex
            String from = entry.getKey();
            graph.addVertex(from);
            // Add all adjacent vertices together with edge weight
            for (Pair<String, Integer> to : entry.getValue()) {
                DefaultWeightedEdge edge = graph.addEdge(from, to.first);
                graph.setEdgeWeight(edge, to.second);
            }
        }

        return graph;
    }

    @Test
    public void testPathBetween() {
    }

    @Test
    public void testWeightOfTheShortestPath() {

    }
}
