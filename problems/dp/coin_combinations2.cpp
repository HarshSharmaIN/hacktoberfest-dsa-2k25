/*
  Problem: Coin Combinations II
  Difficulty: easy
  Source: CSES - Dynamic Programming - Coin Combinations II
  Link: https://cses.fi/problemset/task/1636
*/
#include <iostream>
#include <vector>
using namespace std;

const int MOD = 1e9 + 7;

int main() {
  int n, x;
  cin >> n >> x;
  vector<int> coins(n), dp(x + 1);
  for (auto &x : coins) cin >> x;

  dp[0] = 1;
  for (int c : coins) {
    for (int i = c; i <= x; i++) {
      (dp[i] += dp[i - c]) %= MOD;
    }
  }
  cout << dp[x] << endl;
}