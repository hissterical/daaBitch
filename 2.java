import java.util.*;

public class TopologicalSort {
    private int vertices;
    private List<List<Integer>> adjList;
    
    public TopologicalSort(int v) {
        vertices = v;
        adjList = new ArrayList<>();
        for (int i = 0; i < v; i++) {
            adjList.add(new ArrayList<>());
        }
    }
    
    public void addEdge(int u, int v) {
        adjList.get(u).add(v);
    }
    
    public List<Integer> topologicalSort() {
        long startTime = System.nanoTime();
        
        int[] inDegree = new int[vertices];
        
        // Calculate in-degrees
        for (int i = 0; i < vertices; i++) {
            for (int neighbor : adjList.get(i)) {
                inDegree[neighbor]++;
            }
        }
        
        Queue<Integer> queue = new LinkedList<>();
        List<Integer> result = new ArrayList<>();
        
        // Add vertices with 0 in-degree to queue
        for (int i = 0; i < vertices; i++) {
            if (inDegree[i] == 0) {
                queue.offer(i);
            }
        }
        
        // Process vertices
        while (!queue.isEmpty()) {
            int vertex = queue.poll();
            result.add(vertex);
            
            // Reduce in-degree of neighbors
            for (int neighbor : adjList.get(vertex)) {
                inDegree[neighbor]--;
                if (inDegree[neighbor] == 0) {
                    queue.offer(neighbor);
                }
            }
        }
        
        long endTime = System.nanoTime();
        System.out.println("Execution time: " + (endTime - startTime) + " nanoseconds");
        
        if (result.size() != vertices) {
            System.out.println("Graph contains cycle - no topological sort possible");
            return new ArrayList<>();
        }
        
        return result;
    }
    
    public void printGraph() {
        for (int i = 0; i < vertices; i++) {
            System.out.print("Vertex " + i + ": ");
            for (int neighbor : adjList.get(i)) {
                System.out.print(neighbor + " ");
            }
            System.out.println();
        }
    }
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        System.out.print("Enter number of vertices: ");
        int v = sc.nextInt();
        
        System.out.print("Enter number of edges: ");
        int e = sc.nextInt();
        
        TopologicalSort graph = new TopologicalSort(v);
        
        System.out.println("Enter edges (from to):");
        for (int i = 0; i < e; i++) {
            int from = sc.nextInt();
            int to = sc.nextInt();
            graph.addEdge(from, to);
        }
        
        System.out.println("\nGraph representation:");
        graph.printGraph();
        
        List<Integer> topoSort = graph.topologicalSort();
        
        if (!topoSort.isEmpty()) {
            System.out.println("\nTopological Sort: " + topoSort);
        }
        
        sc.close();
    }
}
