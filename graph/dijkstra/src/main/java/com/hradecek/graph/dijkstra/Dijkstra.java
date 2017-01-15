package com.hradecek.graph.dijkstra;

import org.jgrapht.GraphPath;
import org.jgrapht.alg.util.Pair;
import org.jgrapht.graph.SimpleDirectedWeightedGraph;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

public class Dijkstra {

    public static <V, E> GraphPath findPathBetween(SimpleDirectedWeightedGraph<V, E> graph, V source, V destination) {
        /*
         * STEP-1
         * Here we hold list of vertices of the shortest path along with its weight, for every vertex from source.
         *
         * Two steps happen in one line:
         * 1. init the shortest path to undefined (null)
         * 2. init weight of the shortest path to infinity (Double.MAX_VALUE)
         */
        HashMap<V, Pair<V, Double>> paths = new HashMap<>(graph.vertexSet().size());
        graph.vertexSet().forEach(v -> paths.put(v, new Pair<>(null, Double.MAX_VALUE)));

        /*
         * STEP-2
         * Set shortest path from source to source as weight of 0
         */
        paths.put(source, new Pair<>(source, 0.0));

        /*
         * STEP-3
         * List of not-visited vertices. In the beginning it contains all vertices.
         */
        Set<V> unvisited = new HashSet<>(graph.vertexSet());


        /* STEP-4
         * Actual algorithm
         */
        while (!unvisited.isEmpty()) {
            V bestVertex = getBestVertex(paths, unvisited);
            for (E edge : graph.outgoingEdgesOf(bestVertex)) {
                V adjacent = graph.getEdgeTarget(edge);
                double alternative = paths.get(bestVertex).second + graph.getEdgeWeight(edge);
                if (alternative < paths.get(adjacent).second) {
                    paths.put(adjacent, new Pair<>(bestVertex, alternative));
                }
            }
        }

        return null;
    }

    /*
     * Extract vertex with minimal weight from the set of unvisited vertices.
     * Note: Extracted vertex is already visited, meaning it is removed from the set.
     */
    private static <V> V getBestVertex (HashMap<V, Pair<V, Double>> paths, Set<V> unvisited) {
        // Suppose the first one is the best one
        // Note: we are sure, there is at least one vertex, due to main while cycle.
        V bestVertex = unvisited.iterator().next();

        for (V vertex : unvisited) {
            if (Double.compare(paths.get(vertex).second, paths.get(bestVertex).second) <= 0) {
                bestVertex = vertex;
            }
        }

        unvisited.remove(bestVertex);

        return bestVertex;
    }
}
