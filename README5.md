QUICKSORT(array, low, high):
    IF low < high:
        pivotIndex = PARTITION(array, low, high)
        QUICKSORT(array, low, pivotIndex - 1)
        QUICKSORT(array, pivotIndex + 1, high)

PARTITION(array, low, high):
    pivot = array[high]  // Choose last element as pivot
    i = low - 1
    
    FOR j = low TO high - 1:
        IF array[j] <= pivot:
            i++
            SWAP(array[i], array[j])
    
    SWAP(array[i + 1], array[high])
    RETURN i + 1
