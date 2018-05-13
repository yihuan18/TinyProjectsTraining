/**
 * Created by Yihuan on 2018/5/11.
 */
public class Solution {
    public double findMedianSortedArrays(int[] nums1, int[] nums2) {
        int medianSeq = (nums1.length + nums2.length) / 2 + (nums1.length + nums2.length) % 2;
        boolean oddFlag = true;
        int i = 0 , j = 0;
        int count = 0;
        int lastStep = 0; //为0表示i结束，为1表示j结束
        if((nums1.length + nums2.length) % 2 != 0){
            oddFlag = false;
        }
        if(nums1.length == 0)
        {
            if(oddFlag)
                return (nums2[medianSeq-1] * 1.0+nums2[medianSeq] * 1.0)/2;
            else return nums2[medianSeq-1] * 1.0;
        } else if(nums2.length == 0){
            if(oddFlag)
                return (nums1[medianSeq-1] * 1.0+nums1[medianSeq] * 1.0)/2;
            else return nums1[medianSeq-1] * 1.0;
        }
        while(count < medianSeq){
            if(i == nums1.length){
                j++;
                lastStep = 1;
            }
            else if(j == nums2.length) {
                i++;
                lastStep = 0;
            }
            else {
                if(nums1[i] < nums2[j]){
                    i++;
                    lastStep = 0;
                }
                else{
                    j++;
                    lastStep = 1;
                }
            }
            count++;
        }

            if(!oddFlag){
                if(lastStep == 0)
                    return nums1[i-1] * 1.0;
                else return nums2[j-1] * 1.0;
            }
            else{
                if(lastStep == 0){
                    if(i == nums1.length)
                        return (nums1[i-1] * 1.0 + nums2[j] * 1.0)/2;
                    else if(j == nums2.length)
                        return (nums1[i-1] * 1.0 + nums1[i] * 1.0)/2;
                    if(nums1[i] < nums2[j])
                        return (nums1[i-1] * 1.0 + nums1[i] * 1.0)/2;
                    else
                        return (nums1[i-1] * 1.0 + nums2[j] * 1.0)/2;
                }else {
                    if(i == nums1.length)
                        return (nums2[j-1] * 1.0 + nums2[j] * 1.0)/2;
                    else if(j == nums2.length)
                        return (nums2[j-1]* 1.0+nums1[i]* 1.0)/2;
                    if(nums2[j] < nums1[i])
                        return (nums2[j-1] * 1.0 + nums2[j] * 1.0)/2;
                    else
                        return (nums2[j-1]* 1.0+nums1[i]* 1.0)/2;
                }
            }
    }

    public static void main(String[] args){
        Solution solution = new Solution();
        int[] nums1 = {1};
        int[] nums2 = {2,3};
        System.out.println(solution.findMedianSortedArrays(nums1 , nums2));
    }
}
