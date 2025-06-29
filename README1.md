GALE_SHAPLEY(men_preferences, women_preferences):
    Initialize all men and women as free
    WHILE there exists a free man who hasn't proposed to all women:
        m = next free man
        w = highest ranked woman on m's list who hasn't rejected him
        IF w is free:
            Engage m and w
        ELSE IF w prefers m over her current partner m':
            Free m' 
            Engage m and w
        ELSE:
            w rejects m
    RETURN all engaged pairs
