// Isomorphic Strings
// Problem: https://leetcode.com/problems/isomorphic-strings/
// Time Complexity: O(n)
// Space Complexity: O(1)

#include <bits/stdc++.h>
using namespace std;

class Solution {
public:
    bool isIsomorphic(string s, string t) {
        vector<int> mpp1(256, 0);
        vector<int> mpp2(256, 0);
        int n = s.size();
        for (int i = 0; i < n; i++) {
            if (mpp1[s[i]] != mpp2[t[i]]) return false;
            mpp1[s[i]] = i + 1;
            mpp2[t[i]] = i + 1;
        }
        return true;
    }
};

int main() {
    Solution sol;
    string s = "egg", t = "add";
    cout << (sol.isIsomorphic(s, t) ? "True" : "False") << endl;
    return 0;
}
