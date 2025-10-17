// beginner-java-tasks/task-51.java

import java.util.Arrays;

/**
 * Problem: Write a Java program to remove duplicates from a sorted array in-place.
 * The relative order of the elements should be kept the same.
 *
 * Example:
 * Input: [1, 1, 2, 3, 3]
 * Output: The function should return the new length 3, and the first 3 elements
 * of the original array should be modified to [1, 2, 3].
 */
public class RemoveDuplicatesFromSortedArray {

    /**
     * This function removes duplicates from a sorted array.
     * It uses a two-pointer approach to achieve this in linear time.
     */
    public static int removeDuplicates(int[] nums) {
        // If the array is empty, there are no duplicates to remove.
        if (nums.length == 0) {
            return 0;
        }

        // 'insertIndex' will point to the position where the next unique element should be placed.
        // It starts at 1 because the first element is always unique by itself.
        int insertIndex = 1;

        // We iterate through the array starting from the second element.
        for (int i = 1; i < nums.length; i++) {
            // We compare the current element 'nums[i]' with the previous one 'nums[i-1]'.
            // If they are different, it means we have found a new unique number.
            if (nums[i] != nums[i-1]) {
                // We place the new unique number at the 'insertIndex'.
                nums[insertIndex] = nums[i];
                // And then we increment 'insertIndex' to prepare for the next unique number.
                insertIndex++;
            }
        }

        // 'insertIndex' now holds the count of unique elements, which is the new length of the array.
        return insertIndex;
    }

    /**
     * The main method to test the removeDuplicates function with different examples.
     */
    public static void main(String[] args) {
        // --- Test Case 1 ---
        int[] nums1 = {1, 1, 2, 3, 3};
        System.out.println("Original Array: " + Arrays.toString(nums1));
        int newLength1 = removeDuplicates(nums1);
        System.out.println("New Length: " + newLength1);
        System.out.print("Array after removing duplicates: ");
        for (int i = 0; i < newLength1; i++) {
            System.out.print(nums1[i] + " ");
        }
        System.out.println("\n--------------------");

        // --- Test Case 2 ---
        int[] nums2 = {0, 0, 1, 1, 1, 2, 2, 3, 3, 4};
        System.out.println("Original Array: " + Arrays.toString(nums2));
        int newLength2 = removeDuplicates(nums2);
        System.out.println("New Length: " + newLength2);
        System.out.print("Array after removing duplicates: ");
        for (int i = 0; i < newLength2; i++) {
            System.out.print(nums2[i] + " ");
        }
        System.out.println("\n--------------------");

        // --- Test Case 3 ---
        int[] nums3 = {1, 2, 3, 4, 5};
        System.out.println("Original Array: " + Arrays.toString(nums3));
        int newLength3 = removeDuplicates(nums3);
        System.out.println("New Length: " + newLength3);
        System.out.print("Array after removing duplicates: ");
        for (int i = 0; i < newLength3; i++) {
            System.out.print(nums3[i] + " ");
        }
        System.out.println("\n--------------------");
    }
}