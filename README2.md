TOPOLOGICAL_SORT(graph):
    Calculate in-degree for all vertices
    Initialize queue with all vertices having in-degree 0
    Initialize result list
    
    WHILE queue is not empty:
        vertex = dequeue()
        Add vertex to result
        FOR each neighbor of vertex:
            Decrease in-degree of neighbor by 1
            IF in-degree becomes 0:
                Enqueue neighbor
    
    IF result contains all vertices:
        RETURN result
    ELSE:
        Graph has cycle (no topological sort possible)
