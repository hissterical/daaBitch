import java.util.*;

public class Dijkstra {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int v = sc.nextInt();
        int[][] graph = new int[v][v];
        System.out.println("Enter adjacency matrix: "); 
        for(int i=0; i<v; i++) 
            for(int j=0; j<v; j++) 
                graph[i][j] = sc.nextInt();
        djikstra(graph, 0);
    }

    
    public static void djikstra(int[][] graph, int src) {
        int v = graph.length;
        int[] dist = new int[v];
        boolean[] visited = new boolean[v];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[src] = 0;

        for(int i=0; i<v-1; i++){
            int u = minDist(dist, visited);
            visited[u] = true;

            for(int j=0; j<v; j++) {
                if(!visited[j] && graph[u][j]!=0 && 
                    dist[u]!=Integer.MAX_VALUE &&
                    dist[u] + graph[u][j] < dist[j]) {
                        dist[j] = dist[u] + graph[u][j];
                    }
            }

        }
        for(int i : dist) {System.out.println(i);}
    }

    static int minDist(int[] dist, boolean[] visited) {
        int min = Integer.MAX_VALUE, minIdx = -1;
        for (int i=0; i<dist.length; i++) {
            if(!visited[i] && dist[i]<min) {
                min = dist[i];
                minIdx = i;
            }
        }
        return minIdx;
    }
}