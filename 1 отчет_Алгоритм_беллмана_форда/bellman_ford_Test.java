import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class BellmanFordAlgorithmTest {

    @Test
    void testSimpleGraph() {
        int vertices = 3;
        int edges = 2;
        Graph graph = new Graph(vertices, edges);

        graph.addEdge(0, 1, 1);
        graph.addEdge(1, 2, 2);

        int[] expectedDistances = {0, 1, 3};
        int[] expectedPredecessors = {-1, 0, 1};

        assertBellmanFord(graph, 0, expectedDistances, expectedPredecessors);
    }

    @Test
    void testGraphWithNegativeWeights() {
        int vertices = 4;
        int edges = 4;
        Graph graph = new Graph(vertices, edges);

        graph.addEdge(0, 1, 1);
        graph.addEdge(1, 2, -1);
        graph.addEdge(2, 3, -2);
        graph.addEdge(0, 3, 4);

        int[] expectedDistances = {0, 1, 0, -2};
        int[] expectedPredecessors = {-1, 0, 1, 2};

        assertBellmanFord(graph, 0, expectedDistances, expectedPredecessors);
    }

    @Test
    void testGraphWithNegativeCycle() {
        int vertices = 3;
        int edges = 3;
        Graph graph = new Graph(vertices, edges);

        graph.addEdge(0, 1, 1);
        graph.addEdge(1, 2, -2);
        graph.addEdge(2, 0, -1);

        assertThrows(RuntimeException.class, () -> graph.bellmanFord(0));
    }

    private void assertBellmanFord(Graph graph, int source, int[] expectedDistances, int[] expectedPredecessors) {
        int[] distances = new int[graph.vertices];
        int[] predecessors = new int[graph.vertices];

        graph.bellmanFord(source);

        for (int i = 0; i < graph.vertices; i++) {
            assertEquals(expectedDistances[i], distances[i]);
            assertEquals(expectedPredecessors[i], predecessors[i]);
        }
    }
}