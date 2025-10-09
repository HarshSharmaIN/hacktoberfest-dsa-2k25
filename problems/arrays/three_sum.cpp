#include <bits/stdc++.h>
using namespace std;

vector<vector<int>> threeSumToK(vector<int> &nums, int k)
{
    vector<vector<int>> result;
    sort(nums.begin(), nums.end());
    int n = nums.size();

    for (int i = 0; i < n - 2; i++)
    {
        if (i > 0 && nums[i] == nums[i - 1])
            continue; // Skip duplicates

        int left = i + 1, right = n - 1;
        while (left < right)
        {
            int sum = nums[i] + nums[left] + nums[right];

            if (sum == k)
            {
                result.push_back({nums[i], nums[left], nums[right]});
                left++;
                right--;

                // Skip duplicate values
                while (left < right && nums[left] == nums[left - 1])
                    left++;
                while (left < right && nums[right] == nums[right + 1])
                    right--;
            }
            else if (sum < k)
            {
                left++;
            }
            else
            {
                right--;
            }
        }
    }
    return result;
}

int main()
{
    vector<int> nums = {1, 2, -2, 0, -1, 1, -1, 3};
    int k = 2;

    vector<vector<int>> triplets = threeSumToK(nums, k);

    cout << "Triplets with sum = " << k << ":\n";
    for (auto &t : triplets)
    {
        cout << "[ ";
        for (int num : t)
            cout << num << " ";
        cout << "]\n";
    }

    return 0;
}
