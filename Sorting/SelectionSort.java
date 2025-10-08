package Sorting;
//Selection Sort

// Repeatedly selects the minimum (or maximum) element from the unsorted part and places it at the correct position.

// 🕒 Time: O(n²)

// 🧠 Space: O(1)

// ✅ Stable: No (unless modified)

🔥 Use: Not efficient; theoretical interest only
public class SelectionSort {
    public static void main(String[] args) {
        int[] arr = { 3, 70, 3, 9, 5, 2, 8, 6, 40, 42, 1 };

        for (int i = 0; i < arr.length; i++) {
            int minIndex = i;
            for (int j = i + 1; j < arr.length; j++) {
                if (arr[j] < arr[minIndex]) {
                    minIndex = j;
                }
            }
            int temp = arr[i];
            arr[i] = arr[minIndex];
            arr[minIndex] = temp;
        }
        for (int i : arr) {
            System.out.print(i + ",");
        }
    }

}
