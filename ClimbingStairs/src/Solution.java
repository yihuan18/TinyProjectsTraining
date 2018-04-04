/**
 * Created by Yihuan on 2018/4/2.
 */
public class Solution {
    int M[];
    public int climbStairs(int n) {
        M = new int[n];
        for(int i = 0 ; i < n ; i++)
            M[i] = 0;
        return compute(n);
     }

     public int compute(int n){
        if(n == 2)
            return 2;
        else if(n == 1)
            return 1;

        if(M[n-1] == 0)
            M[n-1] = compute(n-1)+compute(n-2);
        return M[n-1];

     }

    public static void main(String[] args){
        Solution solution = new Solution();
        System.out.print(solution.climbStairs(3));
    }
}
