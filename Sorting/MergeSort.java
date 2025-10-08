package Sorting;
// Merge sort
// 👉 Uses Divide and Conquer. Divides array into halves, recursively sorts, then merges sorted halves.

// 🕒 Time: O(n log n)

// 🧠 Space: O(n)

// ✅ Stable: Yes

// 🔥 Use: Used in LinkedLists, external sorting, large files
public class MergeSort {

    public int[] mergeSortMethod(int[] arr,int low,int high){
        if(low<high){
            int mid = low+(high-low)/2;
            mergeSortMethod(arr, low, mid);
            mergeSortMethod(arr, mid+1, high);
            merge(arr,low,mid,high);
        }
        return arr;
    }

    public int[] merge(int[] arr,int low,int mid, int high){
        int n1 = mid-low+1;
        int n2 = high-mid;

        int[] left = new int[n1];
        int[] right = new int[n2];

        for(int i=0;i<n1;++i){
            left[i] = arr[low+i];
        }
        for(int i=0;i<n2;++i){
            right[i] = arr[mid+1+i];
        }

        int k=low;
        int i=0;
        int j=0;

        while(i<n1 && j<n2){
            if(left[i]<=right[j]){
                arr[k] = left[i];
                i++;
            }else{
                arr[k] = right[j];
                j++;
            }
            k++;
        }

        while(i<n1){
            arr[k] = left[i];
            i++;
            k++;
        }
        while(j<n2){
            arr[k] = right[j];
            j++;
            k++;
        }
        return arr;
    }

    public static void main(String[] args) {
        int[] arr = { 3, 70, 3, 9, 5, 2, 8, 6, 40, 42, 1 };

        MergeSort m = new MergeSort();
        arr = m.mergeSortMethod(arr, 0, arr.length-1);

        for(int i:arr){
            System.out.print(i+",");
        }
    }
}
