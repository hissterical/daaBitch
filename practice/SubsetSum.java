import java.util.*;

public class SubsetSum {

    // Main driver
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        // Input array
        System.out.print("Enter number of elements: ");
        int n = sc.nextInt();
        int[] arr = new int[n];

        System.out.println("Enter the elements:");
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }

        // Input target sum
        System.out.print("Enter target sum: ");
        int target = sc.nextInt();

        // Solve
        boolean[][] dp = buildDPTable(arr, target);
        boolean exists = dp[arr.length][target];

        if (exists) {
            System.out.println("Yes, a subset exists.");
            List<Integer> subset = findSubset(arr, dp, target);
            System.out.println("Subset: " + subset);
        } else {
            System.out.println("No, such subset does not exist.");
        }

        sc.close();
    }

    public static boolean[][] buildDPTable(int[] arr, int target) {
        int n = arr.length; 
        boolean [][] dp = new boolean[n+1][target+1];

        for(int i=0; i<=n; i++) {
            dp[i][0]=true;
        }

        for (int i=1; i<=n; i++) {
            for (int sum=1; sum<=target; sum++) {
                if(arr[i-1]> sum) {
                    dp[i][sum] = dp[i-1][sum];
                } else {
                    dp[i][sum] = dp[i-1][sum] || dp[i-1][sum-arr[i-1]];
                }
            }
        }

        return dp;
    }

    public static List<Integer> findSubset(int[] arr, boolean[][] dp, int target) {
        List<Integer> subset = new ArrayList<>();
        int i = arr.length; 
        int sum = target; 

        while(i>0 && sum>0) {
            if(!dp[i-1][sum]) {
                subset.add(arr[i-1]);
                sum -= arr[i-1];
            }
            i--;
        }
        Collections.reverse(subset);
        return subset;
    }


}

    // // Function to reconstruct one subset from DP table
    // public static List<Integer> findSubset(int[] arr, boolean[][] dp, int target) {
    //     List<Integer> subset = new ArrayList<>();
    //     int i = arr.length;
    //     int sum = target;

    //     while (i > 0 && sum > 0) {
    //         if (!dp[i - 1][sum]) {
    //             // Element included
    //             subset.add(arr[i - 1]);
    //             sum -= arr[i - 1];
    //         }
    //         i--;
    //     }

    //     Collections.reverse(subset);
    //     return subset;
    // }
    // // Function to build DP table
    // public static boolean[][] buildDPTable(int[] arr, int target) {
    //     int n = arr.length;
    //     boolean[][] dp = new boolean[n + 1][target + 1];

    //     // Base cases: sum = 0 is always true
    //     for (int i = 0; i <= n; i++) {
    //         dp[i][0] = true;
    //     }

    //     // Fill DP table
    //     for (int i = 1; i <= n; i++) {
    //         for (int sum = 1; sum <= target; sum++) {
    //             if (sum < arr[i - 1]) {
    //                 dp[i][sum] = dp[i - 1][sum];
    //             } else {
    //                 dp[i][sum] = dp[i - 1][sum] || dp[i - 1][sum - arr[i - 1]];
    //             }
    //         }
    //     }

    //     return dp;
    // }