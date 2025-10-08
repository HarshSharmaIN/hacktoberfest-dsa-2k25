package Sorting;
// Quicj sort
// 👉 Also uses Divide and Conquer, but selects a pivot and partitions the array such that elements < pivot go left, and > pivot go right.

// 🕒 Time: Average O(n log n), Worst: O(n²)

// 🧠 Space: O(log n) (for recursion)

// ✅ Stable: No

// 🔥 Use: Fastest in practice, used in Arrays.sort() in Java (for primitives)
public class QuickSort {
    //quicksortmethod
    public int[] QuickSortMethod(int[] arr,int low,int high){
        if(low<high){
            int p = partition(arr,low,high);
            QuickSortMethod(arr, low, p-1);
            QuickSortMethod(arr, p+1, high);
        }
        return arr;
    }
    //partition
    public int partition(int[] arr, int low,int high){
        int pivote = arr[high];
        int i = low-1;
        for(int j=low;j<arr.length;j++){
            if(arr[j]<pivote){
                i++;
                int temp =  arr[j];
                arr[j] = arr[i];
                arr[i] = temp;
            }
        }
        int temp =  arr[i+1];
        arr[i+1] = arr[high];
        arr[high] = temp;
        return i+1;
    }
    public static void main(String[] args) {
        int[] arr = { 3, 70, 3, 9, 5, 2, 8, 6, 40, 42, 1 };
        QuickSort q = new QuickSort();
        arr = q.QuickSortMethod(arr, 0, arr.length-1);
        for(int i:arr){
            System.out.print(i+",");
        }
    }

}
