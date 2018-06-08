import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Yihuan on 2018/6/8.
 */
class Solution {
    /*
    public List<Integer> majorityElement(int[] nums) {
        Map<Integer,Integer> ElementCount = new HashMap<>();
        List<Integer> result = new ArrayList<>();

        int current_count = 0;
        for(int i = 0 ; i < nums.length ; i++){
            if(ElementCount.get(nums[i])!=null)
                current_count = ElementCount.get(nums[i]);
            else current_count = 0;
            ElementCount.put(nums[i] , current_count+1);
        }

        for(Integer i : ElementCount.keySet()){
            if(ElementCount.get(i) > (nums.length/3))
                result.add(i);
        }
        return result;
    }
    */


    public List<Integer> majorityElement(int[] nums) {
        if (nums == null || nums.length == 0)
            return new ArrayList<Integer>();
        List<Integer> result = new ArrayList<Integer>();
        int number1 = nums[0], number2 = nums[0], count1 = 0, count2 = 0, len = nums.length;
        for (int i = 0; i < len; i++) {
            if (nums[i] == number1)
                count1++;
            else if (nums[i] == number2)
                count2++;
            else if (count1 == 0) {
                number1 = nums[i];
                count1 = 1;
            } else if (count2 == 0) {
                number2 = nums[i];
                count2 = 1;
            } else {
                count1--;
                count2--;
            }
        }
        count1 = 0;
        count2 = 0;
        for (int i = 0; i < len; i++) {
            if (nums[i] == number1)
                count1++;
            else if (nums[i] == number2)
                count2++;
        }
        if (count1 > len / 3)
            result.add(number1);
        if (count2 > len / 3)
            result.add(number2);
        return result;
    }


    public static void main(String[] args){
        Solution solution = new Solution();
        List<Integer> integers;
        int[] nums = {1,1,1,3,3,2,2,2};
        integers = solution.majorityElement(nums);
        for(Integer integer : integers)
            System.out.println(integer);
    }
}

