PRIM(graph, start):
    Initialize MST as empty set
    Initialize visited array as false for all vertices
    Initialize priority queue with all edges from start vertex
    Mark start vertex as visited
    
    WHILE MST has less than V-1 edges:
        edge = extract minimum weight edge from priority queue
        IF destination vertex is not visited:
            Add edge to MST
            Mark destination vertex as visited
            Add all edges from destination vertex to priority queue
            (only edges to unvisited vertices)
    
    RETURN MST
