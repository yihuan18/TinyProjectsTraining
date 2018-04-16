import java.util.Arrays;

/**
 * Created by Yihuan on 2018/3/31.
 */
public class KnapSack {
    public int[] weight = {1,1,2,2,4,4,3,3,5,5};
    public int[] value = {2,2,3,3,8,8,2,2,9,9};
    public int capacity = 10;
    public int M[][];

    public void initialization(){
        M = new int[weight.length+1][capacity+1];
        for(int m = 0 ; m <= 7 ; m++)
            M[0][m] = 0;
    }

    public int subSum(int n, int W){
        if(n == 0)
            return 0;
        for(int i = 1 ; i <= n; i++){
            for(int w = 1 ; w <= W ; w++){
                if(weight[i-1] > w)
                    M[i][w] = subSum(i-1 , w);
                else M[i][w] = Math.max(subSum(i-1,w) , value[i-1] + subSum(i-1 , w - weight[i-1]));
            }
        }
        return M[n][W];
    }

    public static void main(String[] args){
        KnapSack sack = new KnapSack();
        sack.initialization();
        sack.subSum(10 , 10);
        for(int[] m:sack.M){
            for(int a:m){
                System.out.print(a+"   ");
            }
            System.out.println();
        }

    }
}
