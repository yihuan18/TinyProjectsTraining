import java.util.Arrays;

/**
 * Created by Yihuan on 2018/4/21.
 */
public class Solution {
    public int[] twoSum(int[] nums, int target) {
        int temp,i,j = 0;
        int[] result = {0,0};
        boolean isfind = false;
        for(i = 0 ; i < nums.length - 1 ; i++){
            temp = target - nums[i];
            for(j = i + 1 ; j < nums.length ; j++){
                if(nums[j] == temp) {
                    isfind = true;
                    break;
                }
            }
            if(isfind)
                break;
        }
        result[0] = i;
        result[1] = j;
        return result;
    }

    public static void main(String[] args){
        Solution solution = new Solution();
        int[] result;
        int[] input = {3,2,4};
        int target = 6;
        result = solution.twoSum(input , target);
        System.out.println(result[0]);
        System.out.println(result[1]);
    }
}
