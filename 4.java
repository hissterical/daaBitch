import java.util.*;

public class CountInversions {
    
    public static long mergeAndCount(int[] arr, int left, int mid, int right) {
        int n1 = mid - left + 1;
        int n2 = right - mid;
        
        int[] leftArray = new int[n1];
        int[] rightArray = new int[n2];
        
        // Copy data to temp arrays
        for (int i = 0; i < n1; i++)
            leftArray[i] = arr[left + i];
        for (int j = 0; j < n2; j++)
            rightArray[j] = arr[mid + 1 + j];
        
        int i = 0, j = 0, k = left;
        long invCount = 0;
        
        // Merge and count inversions
        while (i < n1 && j < n2) {
            if (leftArray[i] <= rightArray[j]) {
                arr[k] = leftArray[i];
                i++;
            } else {
                arr[k] = rightArray[j];
                // All elements from leftArray[i] to leftArray[n1-1] 
                // are greater than rightArray[j]
                invCount += (n1 - i);
                j++;
            }
            k++;
        }
        
        // Copy remaining elements
        while (i < n1) {
            arr[k] = leftArray[i];
            i++;
            k++;
        }
        
        while (j < n2) {
            arr[k] = rightArray[j];
            j++;
            k++;
        }
        
        return invCount;
    }
    
    public static long mergeSortAndCount(int[] arr, int left, int right) {
        long invCount = 0;
        if (left < right) {
            int mid = left + (right - left) / 2;
            
            invCount += mergeSortAndCount(arr, left, mid);
            invCount += mergeSortAndCount(arr, mid + 1, right);
            invCount += mergeAndCount(arr, left, mid, right);
        }
        return invCount;
    }
    
    public static void printArray(int[] arr) {
        for (int value : arr) {
            System.out.print(value + " ");
        }
        System.out.println();
    }
    
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        
        System.out.print("Enter number of elements: ");
        int n = sc.nextInt();
        
        int[] arr = new int[n];
        System.out.println("Enter elements:");
        for (int i = 0; i < n; i++) {
            arr[i] = sc.nextInt();
        }
        
        System.out.println("Original array:");
        printArray(arr);
        
        // Create copy for counting (original array gets sorted)
        int[] arrCopy = arr.clone();
        
        long startTime = System.nanoTime();
        long inversionCount = mergeSortAndCount(arrCopy, 0, n - 1);
        long endTime = System.nanoTime();
        
        System.out.println("Number of inversions: " + inversionCount);
        System.out.println("Sorted array:");
        printArray(arrCopy);
        
        System.out.println("Execution time: " + (endTime - startTime) + " nanoseconds");
        
        sc.close();
    }
}
