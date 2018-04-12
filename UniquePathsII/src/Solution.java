/**
 * Created by Yihuan on 2018/4/12.
 */
public class Solution {
    int M[][];
    public int uniquePathsWithObstacles(int[][] obstacleGrid) {
        int m = obstacleGrid.length;
        int n = obstacleGrid[0].length;
        int i;
        M= new int[m][n];
        for(i = 0 ; i < n ; i ++){
            if(obstacleGrid[0][i] == 1)
                break;
            M[0][i] = 1;
        }
        for(i = 0 ; i < m ; i ++){
            if(obstacleGrid[i][0] == 1)
                break;
            M[i][0] = 1;
        }
        for(i = 1;i < m; i++){
            for(int j = 1; j < n ; j++){
                if(obstacleGrid[i][j] == 1)
                    M[i][j] = 0;
                else
                    M[i][j] = M[i-1][j] + M[i][j-1];
            }
        }
        return M[m-1][n-1];
    }


    public static void main(String[] args){
        int M[][] = {{0,0},{0,1}};
        Solution solution = new Solution();
        System.out.print(solution.uniquePathsWithObstacles(M));
    }
}
