KNAPSACK_01(weights, values, capacity, n):
    Create DP table[n+1][capacity+1]
    
    // Base cases
    FOR i = 0 to n:
        DP[i][0] = 0  // Zero capacity
    FOR w = 0 to capacity:
        DP[0][w] = 0  // Zero items
    
    FOR i = 1 to n:
        FOR w = 1 to capacity:
            IF weights[i-1] <= w:
                include = values[i-1] + DP[i-1][w - weights[i-1]]
                exclude = DP[i-1][w]
                DP[i][w] = max(include, exclude)
            ELSE:
                DP[i][w] = DP[i-1][w]  // Can't include item
    
    RETURN DP[n][capacity]
