#include <iostream>
#include <vector>
#include <climits> // for INT_MIN
using namespace std;

int main() {
    int n;
    cout << "Enter number of elements: ";
    cin >> n;

    vector<int> arr(n);
    cout << "Enter elements: ";
    for (int i = 0; i < n; i++)
        cin >> arr[i];

    int sum = 0;
    int maxVal = INT_MIN;

    for (int i = 0; i < n; i++) {
        sum += arr[i];
        if (arr[i] > maxVal)
            maxVal = arr[i];
    }

    cout << "Sum of elements = " << sum << endl;
    cout << "Maximum value = " << maxVal << endl;

    return 0;
}
