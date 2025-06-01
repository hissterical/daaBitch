import java.util.*;

public class BellmanFord {

    static class Edge {
        int src, dest, weight;
        Edge(int src, int dest, int weight) {
            this.src = src;
            this.dest = dest;
            this.weight = weight;
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int v = sc.nextInt();
        int e = sc.nextInt();

        List<Edge> edges = new ArrayList<>();
        System.out.println("Enter edges (src dest weight): ");
        for(int i=0; i<e; i++) {
            int s = sc.nextInt();
            int d = sc.nextInt();
            int w = sc.nextInt();
            edges.add(new Edge(s, d, w));
        }

        int[] dist = new int[v];
        Arrays.fill(dist, Integer.MAX_VALUE);
        dist[0] = 0; // Starting from vertex 0

        for(int i=0; i<v; i++) {
            for(Edge edge: edges) {
                if(dist[edge.src] != Integer.MAX_VALUE &&
                    dist[edge.src] + edge.weight < dist[edge.dest]) {
                        dist[edge.dest] = dist[edge.src] + edge.weight;
                    }
            }
        }

        boolean hasNegativeCycle = false;
        for(Edge edge: edges) {
            if(dist[edge.src] != Integer.MAX_VALUE &&
                dist[edge.src] + edge.weight < dist[edge.dest]) {
                    hasNegativeCycle = true;
                    break;
                }
        }

        if(hasNegativeCycle) {
            System.out.println("Graph contains negative weight cycle");
        } else {
            System.out.println("Vertex Distance from Source");
            for(int i=0; i<v; i++) {
                System.out.println(i + "\t\t" + dist[i]);
            }
        }

    }
}