import java.util.*;

public class DijkstraAlgorithm {
    private int vertices;
    private List<List<Edge>> adjList;
    
    static class Edge {
        int destination;
        int weight;
        
        Edge(int dest, int weight) {
            this.destination = dest;
            this.weight = weight;
        }
    }
    
    static class Node implements Comparable<Node> {
        int vertex;
        int distance;
        
        Node(int vertex, int distance) {
            this.vertex = vertex;
            this.distance = distance;
        }
        
        public int compareTo(Node other) {
            return Integer.compare(this.distance, other.distance);
        }
    }
    
    public DijkstraAlgorithm(int vertices) {
        this.vertices = vertices;
        adjList = new ArrayList<>();
        for (int i = 0; i < vertices; i++) {
            adjList.add(new ArrayList<>());
        }
    }
    
    public void addEdge(int source, int dest, int weight) {
        adjList.get(source).add(new Edge(dest, weight));
        adjList.get(dest).add(new Edge(source, weight)); // For undirected graph
    }
    
    public void dijkstra(int source) {
        long startTime = System.nanoTime();
        
        int[] distance = new int[vertices];
        int[] previous = new int[vertices];
        boolean[] visited = new boolean[vertices];
        
        Arrays.fill(distance, Integer.MAX_VALUE);
        Arrays.fill(previous, -1);
        distance[source] = 0;
        
        PriorityQueue<Node> pq = new PriorityQueue<>();
        pq.offer(new Node(source, 0));
        
        while (!pq.isEmpty()) {
            Node current = pq.poll();
            int u = current.vertex;
            
            if (visited[u]) continue;
            visited[u] = true;
            
            for (Edge edge : adjList.get(u)) {
                int v = edge.destination;
                int weight = edge.weight;
                
                if (!visited[v] && distance[u] + weight < distance[v]) {
                    distance[v] = distance[u] + weight;
                    previous[v] = u;
                    pq.offer(new Node(v, distance[v]));
                }
            }
        }
        
        long endTime = System.nanoTime();
        System.out.println("Execution time: " + (endTime - startTime) + " nanoseconds");
        
        printSolution(distance, previous, source);
    }
    
    private void printSolution(int[] distance, int[] previous, int source) {
        System.out.println("Shortest distances from vertex " + source + ":");
        for (int i = 0; i < vertices; i++) {
            System.out.print("To vertex " + i + ": ");
            if (distance[i] == Integer.MAX_VALUE) {
                System.out.println("No path");
            } else {
                System.out.println("Distance = " + distance[i] + ", Path: " + getPath(previous, i));
            }
        }
    }
    
    private String getPath(int[] previous, int vertex) {
        List<Integer> path = new ArrayList<>();
        int current = vertex;
        
        while (current != -1) {
            path.add(current);
            current = previous[current];
        }
        
        Collections.reverse(path);
        return path.toString();
    }
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        System.out.print("Enter number of vertices: ");
        int v = sc.nextInt();
        
        System.out.print("Enter number of edges: ");
        int e = sc.nextInt();
        
        DijkstraAlgorithm graph = new DijkstraAlgorithm(v);
        
        System.out.println("Enter edges (source destination weight):");
        for (int i = 0; i < e; i++) {
            int src = sc.nextInt();
            int dest = sc.nextInt();
            int weight = sc.nextInt();
            graph.addEdge(src, dest, weight);
        }
        
        System.out.print("Enter source vertex: ");
        int source = sc.nextInt();
        
        graph.dijkstra(source);
        
        sc.close();
    }
}
