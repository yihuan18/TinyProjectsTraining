/**
 * Created by Yihuan on 2018/4/27.
 */
public class Solution {
    int[] array;
    int result;
    public int findKthLargest(int[] nums, int k) {
        array = nums;
        quickSortForKth(0 , array.length-1 , array.length-k);
        return result;
    }

    public boolean quickSortForKth(int left , int right , int k ){
        int i , j , t , temp;

        temp = array[left]; //基准数
        i = left;
        j = right;
        while(i != j){
            while(array[j] >= temp && i < j)
                j--;

            while(array[i] <= temp && i < j)
                i++;

            if(i < j) { //swap
                t = array[i];
                array[i] = array[j];
                array[j] = t;
            }
        }
        //基准数归位
        if(i == k){
            result = temp;
            return true;
        }

        array[left] = array[i];
        array[i] = temp;

        if(i > k){
            if(quickSortForKth(left,i-1,k))
                return true;
        }
        else{
            if(quickSortForKth(i+1,right,k))
                return true;
        }

        return false;
    }

    public static void main(String[] args){
        Solution solution = new Solution();
        int[] nums = {3,2,1,5,6,4};
        int ret = solution.findKthLargest(nums,2);
        System.out.println(ret);
    }
}
