import java.util.*;

public class BellmanFordAlgorithm {
    
    static class Edge {
        int source, destination, weight;
        
        Edge(int src, int dest, int weight) {
            this.source = src;
            this.destination = dest;
            this.weight = weight;
        }
    }
    
    public static boolean bellmanFord(List<Edge> edges, int vertices, int source) {
        long startTime = System.nanoTime();
        
        int[] distance = new int[vertices];
        int[] predecessor = new int[vertices];
        
        // Initialize distances
        Arrays.fill(distance, Integer.MAX_VALUE);
        Arrays.fill(predecessor, -1);
        distance[source] = 0;
        
        // Relax all edges V-1 times
        for (int i = 0; i < vertices - 1; i++) {
            for (Edge edge : edges) {
                int u = edge.source;
                int v = edge.destination;
                int w = edge.weight;
                
                if (distance[u] != Integer.MAX_VALUE && distance[u] + w < distance[v]) {
                    distance[v] = distance[u] + w;
                    predecessor[v] = u;
                }
            }
        }
        
        // Check for negative cycles
        for (Edge edge : edges) {
            int u = edge.source;
            int v = edge.destination;
            int w = edge.weight;
            
            if (distance[u] != Integer.MAX_VALUE && distance[u] + w < distance[v]) {
                System.out.println("Graph contains negative weight cycle");
                long endTime = System.nanoTime();
                System.out.println("Execution time: " + (endTime - startTime) + " nanoseconds");
                return false;
            }
        }
        
        long endTime = System.nanoTime();
        System.out.println("Execution time: " + (endTime - startTime) + " nanoseconds");
        
        printSolution(distance, predecessor, source);
        return true;
    }
    
    private static void printSolution(int[] distance, int[] predecessor, int source) {
        System.out.println("Shortest distances from vertex " + source + ":");
        for (int i = 0; i < distance.length; i++) {
            System.out.print("To vertex " + i + ": ");
            if (distance[i] == Integer.MAX_VALUE) {
                System.out.println("No path");
            } else {
                System.out.println("Distance = " + distance[i] + ", Path: " + getPath(predecessor, i));
            }
        }
    }
    
    private static String getPath(int[] predecessor, int vertex) {
        List<Integer> path = new ArrayList<>();
        int current = vertex;
        
        while (current != -1) {
            path.add(current);
            current = predecessor[current];
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
        
        List<Edge> edges = new ArrayList<>();
        
        System.out.println("Enter edges (source destination weight):");
        for (int i = 0; i < e; i++) {
            int src = sc.nextInt();
            int dest = sc.nextInt();
            int weight = sc.nextInt();
            edges.add(new Edge(src, dest, weight));
        }
        
        System.out.print("Enter source vertex: ");
        int source = sc.nextInt();
        
        bellmanFord(edges, v, source);
        
        sc.close();
    }
}
