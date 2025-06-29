import java.util.*;

public class WeightedIntervalScheduling {
    
    static class Interval {
        int start, end, weight, id;
        
        Interval(int id, int start, int end, int weight) {
            this.id = id;
            this.start = start;
            this.end = end;
            this.weight = weight;
        }
    }
    
    public static int weightedIntervalScheduling(Interval[] intervals) {
        long startTime = System.nanoTime();
        
        int n = intervals.length;
        
        // Sort by finish time
        Arrays.sort(intervals, (a, b) -> Integer.compare(a.end, b.end));
        
        // Compute p array
        int[] p = new int[n + 1];
        for (int i = 1; i <= n; i++) {
            p[i] = findLatestCompatible(intervals, i - 1);
        }
        
        // DP array
        int[] dp = new int[n + 1];
        dp[0] = 0;
        
        // Fill DP table
        for (int i = 1; i <= n; i++) {
            int include = intervals[i - 1].weight + dp[p[i]];
            int exclude = dp[i - 1];
            dp[i] = Math.max(include, exclude);
        }
        
        long endTime = System.nanoTime();
        System.out.println("Execution time: " + (endTime - startTime) + " nanoseconds");
        
        printSolution(intervals, dp, p, n);
        return dp[n];
    }
    
    private static int findLatestCompatible(Interval[] intervals, int current) {
        // Binary search for latest compatible interval
        int left = 0, right = current - 1, result = 0;
        
        while (left <= right) {
            int mid = left + (right - left) / 2;
            if (intervals[mid].end <= intervals[current].start) {
                result = mid + 1; // Store 1-indexed position
                left = mid + 1;
            } else {
                right = mid - 1;
            }
        }
        return result;
    }
    
    private static void printSolution(Interval[] intervals, int[] dp, int[] p, int n) {
        List<Integer> selected = new ArrayList<>();
        int i = n;
        
        while (i > 0) {
            int include = intervals[i - 1].weight + dp[p[i]];
            int exclude = dp[i - 1];
            
            if (include >= exclude) {
                selected.add(intervals[i - 1].id);
                i = p[i];
            } else {
                i = i - 1;
            }
        }
        
        Collections.reverse(selected);
        System.out.println("Selected intervals: " + selected);
        System.out.println("Maximum weight: " + dp[n]);
    }
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        System.out.print("Enter number of intervals: ");
        int n = sc.nextInt();
        
        Interval[] intervals = new Interval[n];
        
        System.out.println("Enter intervals (id start end weight):");
        for (int i = 0; i < n; i++) {
            int id = sc.nextInt();
            int start = sc.nextInt();
            int end = sc.nextInt();
            int weight = sc.nextInt();
            intervals[i] = new Interval(id, start, end, weight);
        }
        
        System.out.println("Original intervals:");
        for (Interval interval : intervals) {
            System.out.println("ID: " + interval.id + ", [" + interval.start + 
                             ", " + interval.end + "], Weight: " + interval.weight);
        }
        
        weightedIntervalScheduling(intervals);
        
        sc.close();
    }
}
