import java.util.*;

public class SubsetSumProblem {
    
    public static boolean subsetSum(int[] arr, int target) {
        long startTime = System.nanoTime();
        
        int n = arr.length;
        boolean[][] dp = new boolean[n + 1][target + 1];
        
        // Base case: empty subset sums to 0
        for (int i = 0; i <= n; i++) {
            dp[i][0] = true;
        }
        
        // Base case: no elements can't sum to positive target
        for (int j = 1; j <= target; j++) {
            dp[0][j] = false;
        }
        
        // Fill DP table
        for (int i = 1; i <= n; i++) {
            for (int j = 1; j <= target; j++) {
                if (arr[i - 1] > j) {
                    dp[i][j] = dp[i - 1][j]; // Can't include current element
                } else {
                    boolean include = dp[i - 1][j - arr[i - 1]];
                    boolean exclude = dp[i - 1][j];
                    dp[i][j] = include || exclude;
                }
            }
        }
        
        long endTime = System.nanoTime();
        System.out.println("Execution time: " + (endTime - startTime) + " nanoseconds");
        
        if (dp[n][target]) {
            printSubset(arr, dp, n, target);
        }
        
        return dp[n][target];
    }
    
    private static void printSubset(int[] arr, boolean[][] dp, int n, int target) {
        List<Integer> subset = new ArrayList<>();
        int i = n, j = target;
        
        while (i > 0 && j > 0) {
            if (dp[i][j] && !dp[i - 1][j]) {
                subset.add(arr[i - 1]);
                j -= arr[i - 1];
            }
            i--;
        }
        
        Collections.reverse(subset);
        System.out.println("Subset found: " + subset);
    }
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        System.out.print("Enter number of elements: ");
        int n = sc.nextInt();
        
        int[] arr = new int[n];
        System.out.println("Enter array elements:");
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }
        
        System.out.print("Enter target sum: ");
        int target = sc.nextInt();
        
        System.out.println("Array: " + Arrays.toString(arr));
        System.out.println("Target: " + target);
        
        boolean result = subsetSum(arr, target);
        
        if (result) {
            System.out.println("Subset with target sum exists!");
        } else {
            System.out.println("No subset with target sum found.");
        }
        
        sc.close();
    }
}
