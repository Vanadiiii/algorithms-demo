package me.imatveev.leetcode.structure;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedHashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Queue;
import java.util.Set;
import java.util.Stack;
import java.util.function.Predicate;
import java.util.stream.Collectors;

/**
 * contains only unique values :))
 */
public class Graph<T> {
    private final Map<Vertex<T>, List<Vertex<T>>> adjacentVertices;

    public Graph() {
        this.adjacentVertices = new HashMap<>();
    }

    public void addVertex(T vertex) {
        adjacentVertices.putIfAbsent(Vertex.of(vertex), new ArrayList<>());
    }

    public void removeVertex(T value) {
        final Vertex<T> vertex = Vertex.of(value);

        adjacentVertices.values()
                .forEach(vertices -> vertices.remove(vertex));

        adjacentVertices.remove(vertex);
    }

    public void addEdge(T from, T to) {
        addVertex(from);
        addVertex(to);

        final List<Vertex<T>> vertices = adjacentVertices.get(Vertex.of(from));

        vertices.add(Vertex.of(to));
    }

    public void removeEdge(T from, T to) {
        final List<Vertex<T>> vertices = adjacentVertices.get(Vertex.of(from));

        if (vertices != null) {
            vertices.remove(Vertex.of(to));
        }
    }

    public List<T> getAdjacentVertexes(T vertex) {
        return adjacentVertices.get(Vertex.of(vertex))
                .stream()
                .map(Vertex::getValue)
                .collect(Collectors.toList());
    }

    public boolean hasPathDfs(T from, T to) {
        final Set<T> visited = new LinkedHashSet<>();
        final Stack<T> stack = new Stack<>();

        stack.push(from);

        while (!stack.isEmpty()) {
            T value = stack.pop();

            if (!visited.contains(value)) {
                visited.add(value);

                if (value == to) {
                    return true;
                } else {
                    adjacentVertices.getOrDefault(Vertex.of(value), new ArrayList<>())
                            .stream()
                            .map(Vertex::getValue)
                            .forEach(stack::push);
                }
            }
        }

        return false;
    }

    public boolean hasPathDfsRec(T from, T to) {
        return hasPathDfsRecInner(from, to, new HashSet<>(), new boolean[]{false});
    }

    private boolean hasPathDfsRecInner(T from, T to, Set<T> visited, boolean[] result) {
        if (result[0]) {
            return true;
        }
        if (Objects.equals(from, to)) {
            result[0] = true;
            return true;
        }
        visited.add(from);

        final List<T> nextValues = adjacentVertices.getOrDefault(Vertex.of(from), new ArrayList<>())
                .stream()
                .map(Vertex::getValue)
                .filter(Predicate.not(visited::contains))
                .toList();

        for (T value : nextValues) {
            result[0] |= hasPathDfsRecInner(value, to, visited, result);
            if (result[0]) {
                break;
            }
        }

        return result[0];
    }

    public boolean hasPathBfs(T from, T to) {
        final Set<T> visited = new LinkedHashSet<>();
        final Queue<T> queue = new LinkedList<>();

        queue.add(from);

        while (!queue.isEmpty()) {
            T value = queue.poll();
            visited.add(value);

            if (Objects.equals(value, to)) {
                return true;
            }

            adjacentVertices.getOrDefault(Vertex.of(value), new ArrayList<>())
                    .stream()
                    .map(Vertex::getValue)
                    .filter(Predicate.not(visited::contains))
                    .forEach(queue::add);
        }

        return false;
    }

    @Override
    public String toString() {
        return "Graph{" +
                "adjacentVertices=" + adjacentVertices +
                '}';
    }

    private static class Vertex<T> {
        private final T value;
        private boolean visited;

        private Vertex(T value) {
            this.value = value;
            this.visited = false;
        }

        private static <T> Vertex<T> of(T value) {
            return new Vertex<>(value);
        }

        public T getValue() {
            return value;
        }

        public boolean isVisited() {
            return visited;
        }

        public void setVisited(boolean visited) {
            this.visited = visited;
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;
            Vertex<?> vertex = (Vertex<?>) o;
            return Objects.equals(value, vertex.value);
        }

        @Override
        public int hashCode() {
            return Objects.hash(value);
        }
    }
}
