WEIGHTED_INTERVAL_SCHEDULING(intervals):
    Sort intervals by finish time
    Compute p[i] for each interval i (latest compatible interval)
    Initialize dp[0] = 0
    
    FOR i = 1 to n:
        include = weight[i] + dp[p[i]]  // Include current interval
        exclude = dp[i-1]              // Exclude current interval
        dp[i] = max(include, exclude)
    
    RETURN dp[n]

COMPUTE_P(intervals):
    FOR i = 1 to n:
        p[i] = largest index j < i such that intervals[j].end <= intervals[i].start
        IF no such j exists:
            p[i] = 0
