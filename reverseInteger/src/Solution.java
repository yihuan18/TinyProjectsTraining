import java.util.Scanner;

/**
 * Created by Yihuan on 2018/3/26.
 */
public class Solution {
    public int reverse(int x){
        if(x == 0)
            return 0;
        int result;
        String stringOfx = "" + x;
        String resultString = "";
        int negativeFlag = 0;
        int length = stringOfx.length();
        for(int i = length - 1 ; i >= 0 ; i--){
            if(stringOfx.charAt(i) == '-'){
                negativeFlag = 1;
                break;
            }
            resultString += stringOfx.charAt(i);
        }
        try{
            result = Integer.parseInt(resultString);
        }
        catch (NumberFormatException e){
            return 0;
        }
        if(negativeFlag == 0)
            return result;
        else
            return -result;
    }

    public static void main(String[] args){
        Scanner in = new Scanner(System.in);
        while(true){
            int x = in.nextInt();
            Solution solution = new Solution();
            int y = solution.reverse(x);
            System.out.print(y);
            if(x == 0)
                return;
        }
    }
}
