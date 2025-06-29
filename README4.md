COUNT_INVERSIONS(arr, left, right):
    IF left >= right:
        RETURN 0
    
    mid = (left + right) / 2
    invCount = 0
    
    invCount += COUNT_INVERSIONS(arr, left, mid)
    invCount += COUNT_INVERSIONS(arr, mid + 1, right)
    invCount += MERGE_AND_COUNT(arr, left, mid, right)
    
    RETURN invCount

MERGE_AND_COUNT(arr, left, mid, right):
    Create leftArray and rightArray
    Copy elements to temporary arrays
    
    i = 0, j = 0, k = left, invCount = 0
    WHILE i < leftArray.length AND j < rightArray.length:
        IF leftArray[i] <= rightArray[j]:
            arr[k] = leftArray[i]
            i++
        ELSE:
            arr[k] = rightArray[j]
            invCount += (mid - left + 1 - i)  // Key step
            j++
        k++
    
    Copy remaining elements
    RETURN invCount
