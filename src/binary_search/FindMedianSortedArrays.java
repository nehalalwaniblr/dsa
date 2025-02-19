package binary_search;

public class FindMedianSortedArrays {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        // Ensure nums1 is the smaller array;1,3,7,8,9,11,15,18,19,19,21,25
        if (nums1.length > nums2.length) {
            return findMedianSortedArrays(nums2, nums1);
        }

        int m = nums1.length, n = nums2.length;
        int low = 0, high = m;

        while (low <= high) {
            int mid1 = (low + high) / 2;
            int mid2 = (m + n + 1) / 2 - mid1;

            // Handle edge cases
            int l1 = (mid1 == 0) ? Integer.MIN_VALUE : nums1[mid1 - 1];
            int r1 = (mid1 == m) ? Integer.MAX_VALUE : nums1[mid1];

            int l2 = (mid2 == 0) ? Integer.MIN_VALUE : nums2[mid2 - 1];
            int r2 = (mid2 == n) ? Integer.MAX_VALUE : nums2[mid2];

            // Found the correct partition;maxLeftX-maxLeftY<=minRightX-minRightY
            if (l1 <= r2 && l2 <= r1) {
                if ((m + n) % 2 == 0) {
                    return (Math.max(l1, l2) + Math.min(r1, r2)) / 2.0;
                } else {
                    return Math.max(l1, l2);
                }
            } else if (l1 > r2) {
                // Move left in nums1
                high = mid1 - 1;
            } else {
                // Move right in nums1
                low = mid1 + 1;
            }
        }
        throw new IllegalArgumentException("Input arrays are not sorted properly.");
    }

    public static void main(String[] args) {
//        new FindMedianSortedArrays().findMedianSortedArrays(new int[]{1,3}, new int[]{2});
//        new FindMedianSortedArrays().findMedianSortedArrays(new int[]{1,2}, new int[]{3,4});
        new FindMedianSortedArrays().findMedianSortedArrays(new int[]{1, 3, 8, 9, 15}, new int[]{7, 11, 18, 19, 21, 25});

    }
}

/*nums1 = [1, 3, 8, 9, 15]
nums2 = [7, 11, 18, 19, 21, 25]
*/