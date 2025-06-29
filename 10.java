import java.util.*;

public class KnapsackProblem {
    
    public static int knapsack(int[] weights, int[] values, int capacity) {
        long startTime = System.nanoTime();
        
        int n = weights.length;
        int[][] dp = new int[n + 1][capacity + 1];
        
        // Base cases: 0 items or 0 capacity = 0 value
        for (int i = 0; i <= n; i++) {
            dp[i][0] = 0;
        }
        for (int w = 0; w <= capacity; w++) {
            dp[0][w] = 0;
        }
        
        // Fill DP table
        for (int i = 1; i <= n; i++) {
            for (int w = 1; w <= capacity; w++) {
                if (weights[i - 1] <= w) {
                    int include = values[i - 1] + dp[i - 1][w - weights[i - 1]];
                    int exclude = dp[i - 1][w];
                    dp[i][w] = Math.max(include, exclude);
                } else {
                    dp[i][w] = dp[i - 1][w];
                }
            }
        }
        
        long endTime = System.nanoTime();
        System.out.println("Execution time: " + (endTime - startTime) + " nanoseconds");
        
        printSolution(dp, weights, values, capacity, n);
        return dp[n][capacity];
    }
    
    private static void printSolution(int[][] dp, int[] weights, int[] values, int capacity, int n) {
        List<Integer> selectedItems = new ArrayList<>();
        int w = capacity;
        
        // Backtrack to find selected items
        for (int i = n; i > 0 && w > 0; i--) {
            if (dp[i][w] != dp[i - 1][w]) {
                selectedItems.add(i - 1);
                w -= weights[i - 1];
            }
        }
        
        Collections.reverse(selectedItems);
        System.out.println("Selected items (0-indexed): " + selectedItems);
        System.out.println("Maximum value: " + dp[n][capacity]);
    }
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        System.out.print("Enter number of items: ");
        int n = sc.nextInt();
        
        int[] weights = new int[n];
        int[] values = new int[n];
        
        System.out.println("Enter weights:");
        for (int i = 0; i < n; i++) {
            weights[i] = sc.nextInt();
        }
        
        System.out.println("Enter values:");
        for (int i = 0; i < n; i++) {
            values[i] = sc.nextInt();
        }
        
        System.out.print("Enter knapsack capacity: ");
        int capacity = sc.nextInt();
        
        System.out.println("Items (Weight, Value):");
        for (int i = 0; i < n; i++) {
            System.out.println("Item " + i + ": (" + weights[i] + ", " + values[i] + ")");
        }
        
        knapsack(weights, values, capacity);
        
        sc.close();
    }
}
