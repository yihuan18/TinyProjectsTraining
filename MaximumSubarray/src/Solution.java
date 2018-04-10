/**
 * Created by Yihuan on 2018/4/4.
 */
public class Solution {
    public int maxSubArray(int[] nums) {
        //return OPT(nums.length - 1 , nums);
        int result = nums[0];
        int M[] = new int[nums.length];
        M[0] = nums[0];
        for(int i = 1 ; i < nums.length ; i++){
            if(result + nums[i] <= nums[i])
                result = nums[i];
            else result = result + nums[i];
            M[i] = result;
        }
        result = M[0];
        for(int i = 1 ; i < nums.length ; i++)
            if(M[i] > result)
                result = M[i];
        return result;
    }

//    public int OPT(int i , int[] nums){
//        if(i == 0)
//            return nums[0];
//        return Math.max(OPT(i-1 , nums) + nums[i] , nums[i]);
//    }

    public static void main(String[] args){
        Solution solution = new Solution();
        int[] m ={0,1};
        System.out.print(solution.maxSubArray(m));
    }
}
