import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Created by Yihuan on 2018/4/16.
 */
public class Solution {
        public int n;
        public List<List<Integer>> result = new ArrayList<>();
        List<Integer> queue = new ArrayList<>();
        int a[];
        int book[];
        int[] nums_copy;

        public List<List<Integer>> permute(int[] nums) {
            n = nums.length;
            a = new int[n];
            book = new int[n];
            nums_copy = nums;
            dfs(1);
            return result;
        } // end permute

        public void dfs(int box) {
            if (box == n + 1) {
                queue = new ArrayList<>();
                for (int i = 0; i < n; i++)
                    queue.add(a[i]);
                result.add(queue);
                return;
            } //end box == n

            for (int i = 0; i < n; i++) {
                if (book[i] == 0) {
                    a[box - 1] = nums_copy[i];
                    book[i] = 1;
                    dfs(box + 1);
                    book[i] = 0;
                }
            }
        }// end dfs

        public static void main(String[] args){
            Solution solution = new Solution();
            List<List<Integer>> result;
            int[] nums = {1,1,1,2,3};
            result = solution.permute(nums);
            for(List<Integer> i : result){
                for(Integer n : i){
                    System.out.print(n + " ");
                }
                System.out.println();
            }
        }
}
