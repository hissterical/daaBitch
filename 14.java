import java.util.Scanner;

public class TSP {
    private int n; // number of cities
    private int[][] distance; // distance matrix
    private boolean[] visited; // track visited cities
    private int minCost; // minimum cost found
    private int[] bestPath; // best path found
    private int[] currentPath; // current path being explored
    
    public TSP(int n) {
        this.n = n;
        this.distance = new int[n][n];
        this.visited = new boolean[n];
        this.minCost = Integer.MAX_VALUE;
        this.bestPath = new int[n + 1]; // +1 to return to start
        this.currentPath = new int[n + 1];
    }
    
    // Read distance matrix from user
    public void inputData() {
        Scanner sc = new Scanner(System.in);
        
        System.out.println("Enter the distance matrix:");
        System.out.println("(Enter 0 for same city, large number for no direct path)");
        
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print("Distance from city " + i + " to city " + j + ": ");
                distance[i][j] = sc.nextInt();
            }
        }
    }
    
    // Backtracking function to find minimum cost path
    private void findMinPath(int currentCity, int citiesVisited, int currentCost) {
        // If all cities visited, return to start city
        if (citiesVisited == n) {
            int totalCost = currentCost + distance[currentCity][0];
            if (totalCost < minCost) {
                minCost = totalCost;
                // Copy current path to best path
                for (int i = 0; i <= n; i++) {
                    bestPath[i] = currentPath[i];
                }
                bestPath[n] = 0; // return to start city
            }
            return;
        }
        
        // Try visiting all unvisited cities
        for (int nextCity = 0; nextCity < n; nextCity++) {
            if (!visited[nextCity]) {
                // Pruning: skip if current cost already exceeds minimum
                if (currentCost + distance[currentCity][nextCity] < minCost) {
                    visited[nextCity] = true;
                    currentPath[citiesVisited] = nextCity;
                    
                    findMinPath(nextCity, citiesVisited + 1, 
                              currentCost + distance[currentCity][nextCity]);
                    
                    // Backtrack
                    visited[nextCity] = false;
                }
            }
        }
    }
    
    // Solve TSP and display results
    public void solveTSP() {
        long startTime = System.nanoTime();
        
        // Start from city 0
        visited[0] = true;
        currentPath[0] = 0;
        findMinPath(0, 1, 0);
        
        long endTime = System.nanoTime();
        
        // Display results
        if (minCost == Integer.MAX_VALUE) {
            System.out.println("No solution found!");
        } else {
            System.out.println("\nShortest path found:");
            System.out.print("Path: ");
            for (int i = 0; i <= n; i++) {
                System.out.print("City " + bestPath[i]);
                if (i < n) System.out.print(" -> ");
            }
            System.out.println("\nTotal distance: " + minCost);
        }
        
        System.out.println("Time taken: " + (endTime - startTime) + " nanoseconds");
    }
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        System.out.print("Enter the number of cities: ");
        int n = sc.nextInt();
        
        if (n <= 0) {
            System.out.println("Please enter a positive number of cities");
            return;
        }
        
        TSP tsp = new TSP(n);
        tsp.inputData();
        tsp.solveTSP();
        
        sc.close();
    }
}
