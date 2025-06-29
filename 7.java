import java.util.*;

public class PrimAlgorithm {
    private int vertices;
    private List<List<Edge>> adjList;
    
    static class Edge implements Comparable<Edge> {
        int source, destination, weight;
        
        Edge(int src, int dest, int weight) {
            this.source = src;
            this.destination = dest;
            this.weight = weight;
        }
        
        public int compareTo(Edge other) {
            return Integer.compare(this.weight, other.weight);
        }
    }
    
    public PrimAlgorithm(int vertices) {
        this.vertices = vertices;
        adjList = new ArrayList<>();
        for (int i = 0; i < vertices; i++) {
            adjList.add(new ArrayList<>());
        }
    }
    
    public void addEdge(int src, int dest, int weight) {
        adjList.get(src).add(new Edge(src, dest, weight));
        adjList.get(dest).add(new Edge(dest, src, weight));
    }
    
    public void primMST() {
        long startTime = System.nanoTime();
        
        boolean[] visited = new boolean[vertices];
        PriorityQueue<Edge> pq = new PriorityQueue<>();
        List<Edge> mst = new ArrayList<>();
        int totalWeight = 0;
        
        // Start with vertex 0
        visited[0] = true;
        for (Edge edge : adjList.get(0)) {
            pq.offer(edge);
        }
        
        while (!pq.isEmpty() && mst.size() < vertices - 1) {
            Edge minEdge = pq.poll();
            
            if (visited[minEdge.destination]) {
                continue; // Skip if destination is already visited
            }
            
            // Add edge to MST
            mst.add(minEdge);
            totalWeight += minEdge.weight;
            visited[minEdge.destination] = true;
            
            // Add all edges from newly added vertex
            for (Edge edge : adjList.get(minEdge.destination)) {
                if (!visited[edge.destination]) {
                    pq.offer(edge);
                }
            }
        }
        
        long endTime = System.nanoTime();
        System.out.println("Execution time: " + (endTime - startTime) + " nanoseconds");
        
        printMST(mst, totalWeight);
    }
    
    private void printMST(List<Edge> mst, int totalWeight) {
        System.out.println("Minimum Spanning Tree:");
        for (Edge edge : mst) {
            System.out.println("Edge: " + edge.source + " - " + edge.destination + 
                             " Weight: " + edge.weight);
        }
        System.out.println("Total weight of MST: " + totalWeight);
    }
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        System.out.print("Enter number of vertices: ");
        int v = sc.nextInt();
        
        System.out.print("Enter number of edges: ");
        int e = sc.nextInt();
        
        PrimAlgorithm graph = new PrimAlgorithm(v);
        
        System.out.println("Enter edges (source destination weight):");
        for (int i = 0; i < e; i++) {
            int src = sc.nextInt();
            int dest = sc.nextInt();
            int weight = sc.nextInt();
            graph.addEdge(src, dest, weight);
        }
        
        graph.primMST();
        
        sc.close();
    }
}
