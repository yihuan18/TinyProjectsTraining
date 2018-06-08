/**
 * Created by Yihuan on 2018/6/8.
 */
public class Solution {
    public int majorityElement(int[] nums) {
        int candidate = nums[0],candidate_count = 0;
        for(int i = 0 ; i < nums.length ; i++){
            if(nums[i] == candidate)
                candidate_count++;
            else if(candidate_count == 0){
                candidate = nums[i];
            }
            else {
                candidate_count--;
            }
        }
        return candidate;
    }

    public static void main(String[] args){
        Solution solution = new Solution();
        int[] nums = {1,1,1,2,2};
        System.out.println(solution.majorityElement(nums));
    }
}
