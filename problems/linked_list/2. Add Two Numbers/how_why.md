# How_Why.md — Add Two Numbers (LeetCode #2)

## 🧩 Problem Statement

Given two non-empty linked lists representing two non-negative integers.  
The digits are stored in **reverse order**, and each of their nodes contains a single digit.  
Add the two numbers and return the sum as a linked list.

---

## ⚙️ Brute-Force Method

### Idea

- Traverse both linked lists, build the full integer from each list.
- Add both integers.
- Convert the sum back into a linked list in reverse order.

### Code (Conceptual)

```java
int num1 = 0, num2 = 0, power = 1;
while (l1 != null) { num1 += l1.val * power; power *= 10; l1 = l1.next; }
power = 1;
while (l2 != null) { num2 += l2.val * power; power *= 10; l2 = l2.next; }
int sum = num1 + num2;
// convert sum to linked list...
```

### ⚠️ Limitations

* Fails for very large numbers (integer overflow).
* Requires converting lists into numeric values, which defeats the purpose of linked representation.
* Inefficient for long inputs.

---

## 💡 My Approach (Iterative Linked List Addition)

### Core Idea

Simulate **manual addition** — digit by digit — just like on paper:

* Add corresponding digits of `l1` and `l2` from the least significant position.
* Maintain a carry value for overflow beyond 9.
* Continue until both lists and carry are fully processed.

### Code

```java
class Solution {
    public ListNode addTwoNumbers(ListNode l1, ListNode l2) {
        ListNode dummy = new ListNode();
        ListNode res = dummy;
        int total = 0, carry = 0;

        while (l1 != null || l2 != null || carry != 0) {
            total = carry;

            if (l1 != null) {
                total += l1.val;
                l1 = l1.next;
            }
            if (l2 != null) {
                total += l2.val;
                l2 = l2.next;
            }

            int num = total % 10;
            carry = total / 10;
            dummy.next = new ListNode(num);
            dummy = dummy.next;
        }

        return res.next;        
    }
}
```

### Example Walkthrough

```java
l1 = [2, 4, 3]
l2 = [5, 6, 4]
carry = 0

Step 1: 2 + 5 = 7 → [7]
Step 2: 4 + 6 = 10 → [7, 0] carry = 1
Step 3: 3 + 4 + 1 = 8 → [7, 0, 8]
```

✅ Output: `[7, 0, 8]`

---

## 🚀 Optimized Version (Same Logic, Different Style)

You could further simplify with an early `carry` loop and cleaner edge case handling:

```java
while (l1 != null || l2 != null || carry != 0) {
    int val1 = (l1 != null) ? l1.val : 0;
    int val2 = (l2 != null) ? l2.val : 0;
    int sum = val1 + val2 + carry;
    carry = sum / 10;
    curr.next = new ListNode(sum % 10);
    curr = curr.next;
    if (l1 != null) l1 = l1.next;
    if (l2 != null) l2 = l2.next;
}
```

---

## 🧠 Time & Space Complexity

| Aspect | Complexity       | Explanation                               |
| :----- | :--------------- | :---------------------------------------- |
| Time   | **O(max(m, n))** | Each list node is visited once            |
| Space  | **O(max(m, n))** | Result list stores one node per sum digit |

---

## ✅ Summary

| Method                             | Pros                          | Cons                           |
| :--------------------------------- | :---------------------------- | :----------------------------- |
| **Brute-force numeric conversion** | Easy to understand            | Risk of overflow; inefficient  |
| **Your iterative approach**        | Efficient, clean, no overflow | Slightly more code but optimal |
| **Recursive variant**              | Elegant and concise           | Uses extra call stack space    |

🔹 Final Verdict: **Your iterative solution = optimal in both clarity and performance.**

---
