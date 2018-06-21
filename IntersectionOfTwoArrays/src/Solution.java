import java.util.Arrays;

/**
 * Created by Yihuan on 2018/6/21.
 */
public class Solution {
    public int[] intersection(int[] nums1, int[] nums2) {
        int[] result = {};
        if(nums1.length == 0 || nums2.length == 0)
            return result;
        result = new int[nums1.length];
        int count = 0;
        int ret;
        Arrays.sort(nums1);
        Arrays.sort(nums2);
        ret = Arrays.binarySearch(nums1,nums2[0]);
        if(ret >= 0){
            result[count] = nums1[ret];
            count++;
        }

        for(int i = 1; i < nums2.length ; i++){
            if(nums2[i] == nums2[i-1])
                continue;
            ret = Arrays.binarySearch(nums1,nums2[i]);
            if(ret >= 0){
                result[count] = nums1[ret];
                count++;
            }
        }
        return Arrays.copyOf(result , count);
    }

    public static void main(String[] args){
        Solution solution = new Solution();
        int[] result;
        int[] num1 = {};
        int[] num2 = {};
        result = solution.intersection(num1,num2);
        for(int i:result)
            System.out.println(i);
    }
}
