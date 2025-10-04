from typing import List
class Solution:
    def findMedianSortedArrays(self, nums1: List[int], nums2: List[int]) -> float:
        l1, l2 = len(nums1), len(nums2)
        total = l1 + l2
        mid = total // 2

        i, j = 0, 0
        m = 0
        prev, curr = -1, -1  

        while m <= mid:
            prev = curr
            if i < l1 and (j >= l2 or nums1[i] < nums2[j]):
                curr = nums1[i]
                i += 1
            else:
                curr = nums2[j]
                j += 1
            m += 1

        return curr if total % 2 == 1 else (curr + prev) / 2