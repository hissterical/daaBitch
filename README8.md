KRUSKAL(graph):
    Initialize MST as empty set
    Sort all edges in non-decreasing order of weight
    Initialize Union-Find data structure for all vertices
    
    FOR each edge (u, v) in sorted order:
        IF FIND(u) != FIND(v):  // No cycle formed
            Add edge (u, v) to MST
            UNION(u, v)
            IF MST has V-1 edges:
                BREAK
    
    RETURN MST

FIND(x):
    IF parent[x] != x:
        parent[x] = FIND(parent[x])  // Path compression
    RETURN parent[x]

UNION(x, y):
    rootX = FIND(x)
    rootY = FIND(y)
    IF rank[rootX] < rank[rootY]:
        parent[rootX] = rootY
    ELSE IF rank[rootX] > rank[rootY]:
        parent[rootY] = rootX
    ELSE:
        parent[rootY] = rootX
        rank[rootX]++
    