import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Yihuan on 2018/4/28.
 */
public class Solution {
    private List<List<Integer>> result = new ArrayList<>();
    private int[] array;
    private List<Integer> result_temp = new ArrayList<>();
    public List<List<Integer>> combinationSum2(int[] candidates, int target) {
        array = candidates;
        Arrays.sort(array);
        find(0 , candidates.length-1 , target);

        return result;

    }

    public  void find(int begin , int end , int target){
        int last_temp = 0;
        for(int i = begin ; i <= end ; i++){
            if(array[i] == last_temp)
                continue;
            if(target >= array[i])
                result_temp.add(array[i]);
            else continue;

            if((target-array[i] == 0)){
                List<Integer> temp = new ArrayList<>();
                temp.addAll(result_temp);
                result.add(temp);
                last_temp = result_temp.remove(result_temp.size()-1);
                continue;
            }

            find(i+1,array.length-1 , target - array[i]);
            last_temp = result_temp.remove(result_temp.size()-1);
        }
    }

    public static void main(String[] args){
        Solution solution = new Solution();
        int[] candidates = {10,1,2,7,6,1,5};
        int target = 8;
        List<List<Integer>> retVal;
        retVal = solution.combinationSum2(candidates,target);
        for(List<Integer>temp : retVal){
            for(int i : temp)
                System.out.print(i + " ");
            System.out.println();
        }
    }
}
