/**
 * Segment Tree implementation in Java for range sum queries and point updates.
 * Author: Anish Mohanty
 */

public class SegmentTreeSum {
    private int[] tree;  // Segment tree array
    private int n;       // Size of the original array

    public SegmentTreeSum(int[] arr) {
        this.n = arr.length;
        // Allocate memory for the tree array
        // The maximum size of the segment tree is 4 * n
        tree = new int[4 * n];
        build(arr, 0, 0, n - 1);
    }

    // Build the segment tree recursively
    private void build(int[] arr, int node, int start, int end) {
        if (start == end) {
            // Leaf node will have a single element
            tree[node] = arr[start];
        } else {
            int mid = (start + end) / 2;
            // Recursively build the left and right subtrees
            build(arr, 2 * node + 1, start, mid);
            build(arr, 2 * node + 2, mid + 1, end);
            // Internal node will have the sum of both children
            tree[node] = tree[2 * node + 1] + tree[2 * node + 2];
        }
    }

    // Query the sum in the range [l, r]
    public int query(int l, int r) {
        return query(0, 0, n - 1, l, r);
    }

    private int query(int node, int start, int end, int l, int r) {
        if (r < start || end < l) {
            // No overlap
            return 0;
        }
        if (l <= start && end <= r) {
            // Total overlap
            return tree[node];
        }
        // Partial overlap
        int mid = (start + end) / 2;
        int leftSum = query(2 * node + 1, start, mid, l, r);
        int rightSum = query(2 * node + 2, mid + 1, end, l, r);
        return leftSum + rightSum;
    }

    // Update the value at index 'idx' to 'val'
    public void update(int idx, int val) {
        update(0, 0, n - 1, idx, val);
    }

    private void update(int node, int start, int end, int idx, int val) {
        if (start == end) {
            // Leaf node
            tree[node] = val;
        } else {
            int mid = (start + end) / 2;
            if (idx <= mid) {
                update(2 * node + 1, start, mid, idx, val);
            } else {
                update(2 * node + 2, mid + 1, end, idx, val);
            }
            // Internal node updates sum
            tree[node] = tree[2 * node + 1] + tree[2 * node + 2];
        }
    }

    // Main method for testing
    public static void main(String[] args) {
        int[] arr = {1, 3, 5, 7, 9, 11};
        SegmentTreeSum st = new SegmentTreeSum(arr);

        System.out.println("Sum of values in range [1, 3]: " + st.query(1, 3)); // Output: 15
        st.update(1, 10); // Update index 1 to value 10
        System.out.println("After update, sum of values in range [1, 3]: " + st.query(1, 3)); // Output: 22
    }
}
