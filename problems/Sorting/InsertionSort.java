package Sorting;
// Insertion Sort
// Builds the final sorted array one element at a time by inserting each element into its correct place in the sorted part.

//🕒 Time: O(n²), Best: O(n) (for sorted input)

//🧠 Space: O(1)

//✅ Stable: Yes

//🔥 Use: Good for small or nearly sorted arrays

public class InsertionSort {
    public static void main(String[] args) {
        int[] arr = { 3, 70, 3, 9, 5, 2, 8, 6, 40, 42, 1 };

        for(int i=1;i<arr.length;i++){
            int temp = arr[i];
            int j = i-1;
            while(j>=0 && arr[j]>temp){
                arr[j+1] = arr[j];
                j--;
            }
            arr[j+1] = temp;
        }
        for(int i:arr){
            System.out.print(i+",");
        }
    }
}
