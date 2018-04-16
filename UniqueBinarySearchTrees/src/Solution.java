/**
 * Created by Yihuan on 2018/4/13.
 */
public class Solution {
    public int numTrees(int n) {
        int T[] = new int[n+1];
        T[0] = 1;
        T[1] = 1;
        for(int i = 2 ; i <= n ; i++){
            for(int k = 0 ; k <= i-1 ; k++)
                T[i] += T[i-1-k] * T[k];
        }
        return T[n];
    }

    public static void main(String[] args){
        Solution solution = new Solution();
        System.out.print(solution.numTrees(4));
    }
}
