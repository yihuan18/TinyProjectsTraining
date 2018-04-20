import java.util.HashMap;

/**
 * Created by Yihuan on 2018/4/20.
 */
class Solution {
    public int maxArea(int[] height) {
        int size = height.length;
        int capacity = 0;
        int temp_capacity = 0;
        int i = 0 , j = size - 1;
        capacity = Math.min(height[i],height[j]) * (j - i);
        while((j - i) > 1) {
            if (height[i] <= height[j]) {
                while ((i + 1) < j && height[i + 1] <= height[i])
                        i++;
                i++;
                temp_capacity = Math.min(height[i], height[j]) * (j - i);
                if (capacity < temp_capacity)
                    capacity = temp_capacity;
            } else {
                while ((j - 1) > i && height[j - 1] <= height[j])
                        j--;
                j--;
                temp_capacity = Math.min(height[i], height[j]) * (j - i);
                if (capacity < temp_capacity)
                    capacity = temp_capacity;
            }
        }
        return capacity;
    }

    public static void main(String[] args){
        Solution solution = new Solution();
        int a[] = {2,3,10,5,7,8,9};
        System.out.print(solution.maxArea(a));
    }
}
