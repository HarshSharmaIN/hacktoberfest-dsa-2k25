def length_of_LIS(nums):
    """
    Finds the length of the Longest Increasing Subsequence using Dynamic Programming.
    Time Complexity: O(n^2)
    Space Complexity: O(n)
    """
    n = len(nums)
    if n == 0:
        return 0

    dp = [1] * n  # dp[i] stores LIS ending at index i

    for i in range(1, n):
        for j in range(i):
            if nums[i] > nums[j]:
                dp[i] = max(dp[i], dp[j] + 1)

    return max(dp)


# Optimized version (Binary Search approach)
def length_of_LIS_optimized(nums):
    """
    Optimized O(n log n) solution using binary search.
    """
    from bisect import bisect_left
    subseq = []

    for num in nums:
        pos = bisect_left(subseq, num)
        if pos == len(subseq):
            subseq.append(num)
        else:
            subseq[pos] = num

    return len(subseq)


# Driver code
if __name__ == "__main__":
    test_cases = [
        [10, 9, 2, 5, 3, 7, 101, 18],
        [0, 1, 0, 3, 2, 3],
        [7, 7, 7, 7, 7],
    ]

    print("🔹 Longest Increasing Subsequence (DP Version):")
    for nums in test_cases:
        print(f"Input: {nums}")
        print(f"LIS Length: {length_of_LIS(nums)}")
        print()

    print("⚡ Optimized Version:")
    for nums in test_cases:
        print(f"Input: {nums}")
        print(f"LIS Length: {length_of_LIS_optimized(nums)}")
        print()
