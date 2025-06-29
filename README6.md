DIJKSTRA(graph, source):
    Initialize distance[source] = 0
    Initialize distance[all other vertices] = INFINITY
    Create priority queue Q with all vertices
    Create empty set visited
    
    WHILE Q is not empty:
        u = vertex in Q with minimum distance
        Remove u from Q
        Add u to visited
        
        FOR each neighbor v of u:
            IF v not in visited:
                alt = distance[u] + weight(u, v)
                IF alt < distance[v]:
                    distance[v] = alt
                    previous[v] = u
    
    RETURN distance array and previous array
