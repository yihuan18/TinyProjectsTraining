/**
 * Created by Yihuan on 2018/4/10.
 */
public class Solution {
    int M[][];
    public int uniquePaths(int m, int n) {
        //int max = Math.max(m , n);
        M= new int[m][n];
        for(int i = 0 ; i < m ; i++){
            for(int j = 0 ; j < n ; j++)
                M[i][j] = 0;
        }
        return ways(m , n);
    }

    public int ways(int m , int n){
        if(m == 1 )
            return 1;
        if(n == 1)
            return 1;
        if(M[m-1][n-1] != 0 )
            return M[m-1][n-1];
        //if(M[n-1][m-1] != 0)
            //return M[n-1][m-1];

        M[m-1][n-1] = ways(m-1 , n) + ways(m , n-1);
        //M[n-1][m-1] = M[m-1][n-1];
        return M[m-1][n-1];
    }

    public static void main(String[] args){
        Solution solution = new Solution();
        int ways = solution.uniquePaths(3,7);
        System.out.print(ways);
    }
}
