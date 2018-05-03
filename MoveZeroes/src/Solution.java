/**
 * Created by Yihuan on 2018/5/3.
 */
public class Solution {
    public void moveZeroes(int[] nums) {
        int firstZeroSeq = 0;
        int firstNonZeroSeq = firstZeroSeq;
        while(true) {
            while (nums[firstZeroSeq] != 0 && firstZeroSeq < nums.length-1)
                firstZeroSeq++;
            firstNonZeroSeq = firstZeroSeq;
            while (nums[firstNonZeroSeq] == 0 && firstNonZeroSeq < nums.length-1)
                firstNonZeroSeq++;

            int temp = nums[firstZeroSeq];
            nums[firstZeroSeq] = nums[firstNonZeroSeq];
            nums[firstNonZeroSeq] = temp;

            if(firstZeroSeq == (nums.length -1) || firstNonZeroSeq == (nums.length-1))
                break;
        }
    }

    public static void main(String[] args){
        Solution solution = new Solution();
        int[] nums = {2,1};
        solution.moveZeroes(nums);
        System.out.println();
    }

}
