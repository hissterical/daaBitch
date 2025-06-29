import java.util.*;

public class KruskalAlgorithm {
    
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
    
    static class UnionFind {
        int[] parent, rank;
        
        UnionFind(int n) {
            parent = new int[n];
            rank = new int[n];
            for (int i = 0; i < n; i++) {
                parent[i] = i;
                rank[i] = 0;
            }
        }
        
        int find(int x) {
            if (parent[x] != x) {
                parent[x] = find(parent[x]); // Path compression
            }
            return parent[x];
        }
        
        boolean union(int x, int y) {
            int rootX = find(x);
            int rootY = find(y);
            
            if (rootX == rootY) return false; // Cycle detected
            
            if (rank[rootX] < rank[rootY]) {
                parent[rootX] = rootY;
            } else if (rank[rootX] > rank[rootY]) {
                parent[rootY] = rootX;
            } else {
                parent[rootY] = rootX;
                rank[rootX]++;
            }
            return true;
        }
    }
    
    public static List<Edge> kruskalMST(List<Edge> edges, int vertices) {
        long startTime = System.nanoTime();
        
        Collections.sort(edges);
        UnionFind uf = new UnionFind(vertices);
        List<Edge> mst = new ArrayList<>();
        int totalWeight = 0;
        
        for (Edge edge : edges) {
            if (uf.union(edge.source, edge.destination)) {
                mst.add(edge);
                totalWeight += edge.weight;
                
                if (mst.size() == vertices - 1) {
                    break; // MST is complete
                }
            }
        }
        
        long endTime = System.nanoTime();
        System.out.println("Execution time: " + (endTime - startTime) + " nanoseconds");
        
        System.out.println("Minimum Spanning Tree:");
        for (Edge edge : mst) {
            System.out.println("Edge: " + edge.source + " - " + edge.destination + 
                             " Weight: " + edge.weight);
        }
        System.out.println("Total weight of MST: " + totalWeight);
        
        return mst;
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
        
        kruskalMST(edges, v);
        
        sc.close();
    }
}
