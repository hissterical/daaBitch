BELLMAN_FORD(graph, source):
    Initialize distance[source] = 0
    Initialize distance[all other vertices] = INFINITY
    Initialize predecessor[all vertices] = NULL
    
    // Relax all edges V-1 times
    FOR i = 1 to |V| - 1:
        FOR each edge (u, v) with weight w:
            IF distance[u] + w < distance[v]:
                distance[v] = distance[u] + w
                predecessor[v] = u
    
    // Check for negative cycles
    FOR each edge (u, v) with weight w:
        IF distance[u] + w < distance[v]:
            RETURN "Negative cycle detected"
    
    RETURN distance array and predecessor array
    