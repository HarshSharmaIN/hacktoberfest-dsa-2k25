# Problem: Fibonacci Series
# Author: <Your Name>
# Language: Python
# Description: Prints Fibonacci numbers using dynamic programming (memoization).

def fibonacci(n, memo={}):
    """
    Returns the nth Fibonacci number using recursion + memoization.
    """
    if n in memo:
        return memo[n]
    if n <= 1:
        return n
    memo[n] = fibonacci(n - 1, memo) + fibonacci(n - 2, memo)
    return memo[n]

def print_fibonacci_series(n):
    """
    Prints the first n Fibonacci numbers.
    """
    series = [fibonacci(i) for i in range(n)]
    print("Fibonacci Series:", *series)

# Example usage
if __name__ == "__main__":
    n = int(input("Enter n: "))
    print_fibonacci_series(n)
