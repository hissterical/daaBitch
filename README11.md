SUBSET_SUM(arr, n, target):
    Create DP table[n+1][target+1] of boolean values
    
    // Base cases
    FOR i = 0 to n:
        DP[i][0] = true  // Empty subset always sums to 0
    FOR j = 1 to target:
        DP[0][j] = false  // No elements can't sum to positive target
    
    FOR i = 1 to n:
        FOR j = 1 to target:
            IF arr[i-1] > j:
                DP[i][j] = DP[i-1][j]  // Can't include current element
            ELSE:
                include = DP[i-1][j - arr[i-1]]
                exclude = DP[i-1][j]
                DP[i][j] = include OR exclude
    
    RETURN DP[n][target]
