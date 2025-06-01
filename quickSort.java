import java.util.*;

public class quickSort {
    public static void swap(int[] arr, int i, int j) {
        int temp = arr[i];
        arr[i] = arr[j];
        arr[j] = temp;
    }

    public static int partition(int[] arr, int l, int r) {
        int i = l-1;
        int pivot = arr[r];

        for(int j=l; j<r; j++) {
            if(arr[j] < pivot) {
                i++;
                swap(arr, i, j);
            }
        }

        swap(arr, r, i+1);

        return i+1;
    } 

    public static void quickSort(int[] arr, int l, int r) {
        if(l<r) {
            int pi = partition(arr, l, r);

            quickSort(arr, l, pi-1);
            quickSort(arr, pi+1, r);
        } 
    }

    public static void main(String[] args) {
        int[] arr = {3,2,4,1,5};
        quickSort(arr, 0, 4);
        for(int i: arr) {System.out.println(i);}
    }
}