import java.util.*;

class Router {
    int id;
    List<Link> links;

    Router(int id) {
        this.id = id;
        this.links = new ArrayList<>();
    }

    void addLink(Router destination, int delay) {
        links.add(new Link(destination, delay));
    }
}

class Link {
    Router destination;
    int delay;

    Link(Router destination, int delay) {
        this.destination = destination;
        this.delay = delay;
    }
}

class Network {
    List<Router> routers;

    Network() {
        this.routers = new ArrayList<>();
    }

    void addRouter(Router router) {
        routers.add(router);
    }

    void bellmanFord(Router source) {
        int vertices = routers.size();
        int[] distance = new int[vertices];
        Arrays.fill(distance, Integer.MAX_VALUE);
        distance[source.id] = 0;

        int[] predecessor = new int[vertices];
        Arrays.fill(predecessor, -1);

        // Relax all edges |V| - 1 times
        for (int i = 1; i < vertices; i++) {
            for (Router router : routers) {
                for (Link link : router.links) {
                    Router u = router;
                    Router v = link.destination;
                    int weight = link.delay;
                    if (distance[u.id] != Integer.MAX_VALUE && distance[u.id] + weight < distance[v.id]) {
                        distance[v.id] = distance[u.id] + weight;
                        predecessor[v.id] = u.id;
                    }
                }
            }
        }

        // Check for negative-weight cycles
        for (Router router : routers) {
            for (Link link : router.links) {
                Router u = router;
                Router v = link.destination;
                int weight = link.delay;
                if (distance[u.id] != Integer.MAX_VALUE && distance[u.id] + weight < distance[v.id]) {
                    System.out.println("Сетевая топология содержит отрицательный цикл");
                    return;
                }
            }
        }

        // Print the shortest delays and paths
        printSolution(distance, predecessor);
    }

    void printSolution(int[] distance, int[] predecessor) {
        System.out.println("Маршрутизатор\tЗадержка\tПуть");
        for (Router router : routers) {
            System.out.print(router.id + "\t" + distance[router.id] + "\t\t");
            printPath(predecessor, router.id);
            System.out.println();
        }
    }

    void printPath(int[] predecessor, int vertex) {
        if (vertex == -1) return;
        printPath(predecessor, predecessor[vertex]);
        System.out.print(vertex + " ");
    }
}

public class NetworkRoutingExample {
    public static void main(String[] args) {
        Network network = new Network();

        Router router0 = new Router(0);
        Router router1 = new Router(1);
        Router router2 = new Router(2);
        Router router3 = new Router(3);
        Router router4 = new Router(4);

        network.addRouter(router0);
        network.addRouter(router1);
        network.addRouter(router2);
        network.addRouter(router3);
        network.addRouter(router4);

        router0.addLink(router1, -1);
        router0.addLink(router2, 4);
        router1.addLink(router2, 3);
        router1.addLink(router3, 2);
        router1.addLink(router4, 2);
        router3.addLink(router2, 5);
        router3.addLink(router1, 1);
        router4.addLink(router3, -3);

        network.bellmanFord(router0);
    }
}