MERGE_SORT(array, left, right):
    IF left >= right:
        RETURN
    
    mid = (left + right) / 2
    MERGE_SORT(array, left, mid)
    MERGE_SORT(array, mid + 1, right)
    MERGE(array, left, mid, right)

MERGE(array, left, mid, right):
    Create temporary arrays leftArray and rightArray
    Copy elements to temporary arrays
    
    i = 0, j = 0, k = left
    WHILE i < leftArray.length AND j < rightArray.length:
        IF leftArray[i] <= rightArray[j]:
            array[k] = leftArray[i]
            i++
        ELSE:
            array[k] = rightArray[j]
            j++
        k++
    
    Copy remaining elements from leftArray and rightArray
