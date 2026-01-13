package system_desig_use_cases.proximity_ds;// Segment Tree for range sum queries with point updates

class SegmentTree {
    private final int[] tree;   // segment tree array
    private final int n;

    public SegmentTree(int[] arr) {
        n = arr.length;
        tree = new int[4 * n];      // safe size

        build(arr, 0, 0, n - 1);
    }

    // Build the tree (node stores sum of range)
    private void build(int[] arr, int treeIndex, int l, int r) {
        if (l == r) {                // leaf node
            tree[treeIndex] = arr[l];
        } else {
            int mid = (l + r) / 2;

            build(arr, 2 * treeIndex + 1, l, mid);        // left child
            build(arr, 2 * treeIndex + 2, mid + 1, r);      // right child

            tree[treeIndex] = tree[2 * treeIndex + 1] + tree[2 * treeIndex + 2];
        }
    }

    // Query sum in range [L, R]
    public int query(int L, int R) {
        return query2(0, 0, n - 1, L, R);
    }

    private int query(int node, int start, int end, int l, int r) {
        // 1️⃣ Completely outside the range
        if (r < start || l > end) return 0;

        // 2️⃣ Completely inside the range
        if (l <= start && end <= r) return tree[node];

        // 3️⃣ Partially overlapping
        int mid = (start + end) / 2;
        int leftSum = query(2 * node + 1, start, mid, l, r);
        int rightSum = query(2 * node + 2, mid + 1, end, l, r);

        return leftSum + rightSum;
    }

    private int query2(int node, int start, int end, int L, int R) {
        // 3 conditions:
//        1. Out of bounds:
        if (R < start || L > end) {
            return 0;
        }
//        2. Within range
        if (L <= start && R >= end) {
            return tree[node];
        }
//        3. Overlapping

            int mid = (start + end) / 2;
            int left = query2(2 * node + 1, start, mid, L, R);
            int right = query2(2 * node + 2, mid + 1, end, L, R);
            return left + right;

    }

    // Update value at index idx to newVal
    public void update(int idx, int newVal) {
        update(0, 0, n - 1, idx, newVal);
    }

    private void update(int treeIndex, int l, int r, int idx, int newVal) {
        if (l == r) {
            tree[treeIndex] = newVal;           // update leaf
        } else {
            int mid = (l + r) / 2;

            if (idx <= mid)
                update(2 * treeIndex + 1, l, mid, idx, newVal);
            else
                update(2 * treeIndex + 2, mid + 1, r, idx, newVal);

        }
    }
}

public class SegmentTreeDemo {
    public static void main(String[] args) {
        int[] arr = {5, 3, 7, 2, 6};

        SegmentTree st = new SegmentTree(arr);

        System.out.println(st.query(1, 3));   // sum(3,7,2) = 12
        System.out.println(st.query(0, 4));   // sum all = 23

        st.update(2, 10);                     // change 7 -> 10

        System.out.println(st.query(1, 3));   // now = 3 + 10 + 2 = 15
    }
}
