/**
 * Created by Yihuan on 2018/4/1.
 */
public class Solution {
    public String convert(String s, int numRows) {
        if(numRows == 1)
            return s;
        String stringRows[] = new String[numRows];
        String result = "";
        int row = 0;
        int direction = 0; //为0表示向下，为1表示向上
        for(int i = 0 ; i < numRows ; i++)
            stringRows[i] = "";
        for(int i = 0 ; i < s.length() ; i++){ //把s放入stringRows中
            stringRows[row] += s.charAt(i);
            if(row == numRows - 1){
                row--;
                direction = 1;
            }
            else if(row == 0){
                row++;
                direction = 0;
            }
            else if(direction == 0)
                row++;
            else if(direction == 1)
                row--;
        }
        for(int i = 0 ; i < numRows ; i++)
            result += stringRows[i];
        return result;
    }


    public static void main(String[] args){
        Solution solution = new Solution();
        System.out.print(solution.convert("PAYPALISHIRING" , 1));
    }
}
