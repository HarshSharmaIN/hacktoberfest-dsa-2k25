#include <iostream>
using namespace std;

int binarySearch(int arr[], int n, int target) {
    int low = 0, high = n - 1;

    while (low <= high) {
        int mid = low + (high - low) / 2;  // prevent overflow

        if (arr[mid] == target) {
            return mid;  // element found
        } 
        else if (arr[mid] < target) {
            low = mid + 1;  // search in right half
        } 
        else {
            high = mid - 1; // search in left half
        }
    }

    return -1; // element not found
}

int main() {
    int arr[] = {2, 4, 6, 8, 10, 12, 14};
    int n = sizeof(arr) / sizeof(arr[0]);
    int target = 10;

    int result = binarySearch(arr, n, target);

    if (result != -1) {
        cout << "Element found at index " << result << endl;
    } else {
        cout << "Element not found in array" << endl;
    }

    return 0;
}
