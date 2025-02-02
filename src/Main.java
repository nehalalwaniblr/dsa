public class Main {
    public static void main(String[] args) {

        int[] m = merge(new int[]{0},0,new int[]{1}, 1);
        System.out.println(m);
    }
    public static int[] merge(int[] nums1, int m, int[] nums2, int n) {
        int j = m+n-1;
        int compareAt=n-1;
        if(m==0 && n>0){
            nums1 = nums2;

        }
        for(int i=n-1;i>=0&& m>0;i--){
            if(nums2[i]>=nums1[compareAt]){
                nums1[j]=nums2[i];
                j--;
            }else{
                nums1[j]=nums1[compareAt];
                compareAt--;
                j--;
                i++;
            }
        }
return nums1;
    }
}