import java.util.*;

class Edge {
    int source, destination, weight;

    Edge(int source, int destination, int weight) {
        this.source = source;
        this.destination = destination;
        this.weight = weight;
    }
}

class Graph {
    int vertices, edges;
    List<Edge> edgeList;

    Graph(int vertices, int edges) {
        this.vertices = vertices;
        this.edges = edges;
        this.edgeList = new ArrayList<>(edges);
    }

    void addEdge(int source, int destination, int weight) {
        Edge edge = new Edge(source, destination, weight);
        edgeList.add(edge);
    }

    void bellmanFord(int source) {
        int[] distance = new int[vertices];
        Arrays.fill(distance, Integer.MAX_VALUE);
        distance[source] = 0;

        int[] predecessor = new int[vertices];
        Arrays.fill(predecessor, -1);

        // Relax all edges |V| - 1 times
        for (int i = 1; i < vertices; i++) {
            for (Edge edge : edgeList) {
                int u = edge.source;
                int v = edge.destination;
                int weight = edge.weight;
                if (distance[u] != Integer.MAX_VALUE && distance[u] + weight < distance[v]) {
                    distance[v] = distance[u] + weight;
                    predecessor[v] = u;
                }
            }
        }

        // Check for negative-weight cycles
        for (Edge edge : edgeList) {
            int u = edge.source;
            int v = edge.destination;
            int weight = edge.weight;
            if (distance[u] != Integer.MAX_VALUE && distance[u] + weight < distance[v]) {
                System.out.println("Граф содержит отрицательный цикл");
                return;
            }
        }

        // Print the shortest distances and paths
        printSolution(distance, predecessor);
    }

    void printSolution(int[] distance, int[] predecessor) {
        System.out.println("Вершина\tРасстояние\tПуть");
        for (int i = 0; i < vertices; i++) {
            System.out.print(i + "\t" + distance[i] + "\t\t");
            printPath(predecessor, i);
            System.out.println();
        }
    }

    void printPath(int[] predecessor, int vertex) {
        if (vertex == -1) return;
        printPath(predecessor, predecessor[vertex]);
        System.out.print(vertex + " ");
    }
}

public class BellmanFordAlgorithm {
    public static void main(String[] args) {
        int vertices = 5;
        int edges = 8;
        Graph graph = new Graph(vertices, edges);

        // Пример графа
        graph.addEdge(0, 1, -1);
        graph.addEdge(0, 2, 4);
        graph.addEdge(1, 2, 3);
        graph.addEdge(1, 3, 2);
        graph.addEdge(1, 4, 2);
        graph.addEdge(3, 2, 5);
        graph.addEdge(3, 1, 1);
        graph.addEdge(4, 3, -3);

        int source = 0;
        graph.bellmanFord(source);
    }
}