import java.util.*;

class Graph {
    private int V; // Number of vertices
    private List<Node>[] adjList; // Adjacency list

    static class Node {
        int vertex, weight;

        Node(int vertex, int weight) {
            this.vertex = vertex;
            this.weight = weight;
        }
    }

    Graph(int v) {
        V = v;
        adjList = new ArrayList[V];
        for (int i = 0; i < V; i++) {
            adjList[i] = new ArrayList<>();
        }
    }

    void addEdge(int source, int destination, int weight) {
        adjList[source].add(new Node(destination, weight));
    }

    void dijkstra(int source) {
        int[] distance = new int[V];
        Arrays.fill(distance, Integer.MAX_VALUE);
        distance[source] = 0;

        PriorityQueue<Node> minHeap = new PriorityQueue<>(Comparator.comparingInt(node -> node.weight));
        minHeap.add(new Node(source, 0));

        while (!minHeap.isEmpty()) {
            int u = minHeap.poll().vertex;

            for (Node neighbor : adjList[u]) {
                int v = neighbor.vertex;
                int weight = neighbor.weight;

                if (distance[u] != Integer.MAX_VALUE && distance[u] + weight < distance[v]) {
                    distance[v] = distance[u] + weight;
                    minHeap.add(new Node(v, distance[v]));
                }
            }
        }

        // Print the shortest distances from the source to all vertices
        System.out.println("Shortest Distances from Source (Vertex " + source + "):");
        for (int i = 0; i < V; i++) {
            System.out.println("Vertex " + i + ": " + distance[i]);
        }
    }
}

public class Main {
    public static void main(String[] args) {
        int V = 5; // Number of vertices
        Graph graph = new Graph(V);

        // Add edges to the graph (source, destination, weight)
        graph.addEdge(0, 1, 2);
        graph.addEdge(0, 2, 4);
        graph.addEdge(1, 2, 1);
        graph.addEdge(1, 3, 7);
        graph.addEdge(2, 4, 3);
        graph.addEdge(3, 4, 2);

        int sourceVertex = 0; // Choose the source vertex for Dijkstra's algorithm
        graph.dijkstra(sourceVertex);
    }
}
