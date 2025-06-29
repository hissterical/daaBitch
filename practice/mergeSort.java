import java.util.*;

public class MergeSort{ 
    public static int merge(int[] arr, int l, int m, int r) {
        int count = 0;
        int n1 = m - l + 1;
        int n2 = r - m;

        int[] L = new int[n1];
        int[] R = new int[n2];

        /* Copy data to temporary arrays L[] and R[] */
        for (int i = 0; i < n1; i++) {
            L[i] = arr[l + i];
        }
        // Corrected loop bound from n1 to n2 for R array
        for (int i = 0; i < n2; i++) {
            R[i] = arr[m + 1 + i]; // m+1+i to correctly access elements from the right half
        }

        /* Merge the temporary arrays back into arr[l..r]*/
        int i = 0, j = 0; // Initial indexes of first and second subarrays
        int k = l;        // Initial index of merged subarray

        while (i < n1 && j < n2) {
            if (L[i] <= R[j]) {
                arr[k] = L[i];
                i++;
            } else {
                arr[k] = R[j];
                j++;
                count += (n1-i);
            }
            k++;
        }

        /* Copy remaining elements of L[] if any */
        while (i < n1) {
            arr[k] = L[i];
            i++;
            k++;
        }

        /* Copy remaining elements of R[] if any */
        while (j < n2) {
            arr[k] = R[j];
            j++;
            k++;
        }

        return count;
    }


    public static int mergeSort(int[] arr, int l, int r) {
        if (l<r) {
            int m = (l+r)/2;
            int i1 = mergeSort(arr, l, m);
            int i2 = mergeSort(arr, m+1, r);
            int i3 = merge(arr, l, m, r);

            return (i1+i2+i3);
        }
        else {return 0;}
    }

    public static void main(String[] args) {

        int[] arr = {3,2,4,1,5};
        int r = mergeSort(arr, 0, 4);
        for(int i: arr) {System.out.println(i);}
        System.out.println(r);



    }
}