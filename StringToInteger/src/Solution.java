/**
 * Created by Yihuan on 2018/6/6.
 */
class Solution {
    public int myAtoi(String str) {
        //字符串为空
        if(str == null)
            return 0;

        int pointer = 0;
        //int result = 0;
        long result_temp = 0;
        int negative_flag = 0;
        String num = "";
        //get the first not null char
        while(pointer < str.length()){
            if(str.charAt(pointer) == ' ')
                pointer++;
            else
                break;
        }

        //字符串全是空格
        if(pointer >= str.length())
            return 0;
        //首个非空字符不合法
        if(str.charAt(pointer) < '0' && str.charAt(pointer) > '9' && str.charAt(pointer) != '-' && str.charAt(pointer)!= '+')
            return 0;

        if(str.charAt(pointer)=='-'){
            negative_flag = 1;
            pointer++;
        }
        else if(str.charAt(pointer)=='+')
            pointer++;

        while(pointer < str.length()){
            if(str.charAt(pointer)>='0' && str.charAt(pointer) <='9') {
                num += str.charAt(pointer);
                pointer++;
            }
            else
                break;
        }
        int length = num.length();
        for(int i = 0 ; i < length ; i++){
                if (negative_flag == 0) {
                    result_temp += (double)(num.charAt(i) - '0') *  Math.pow(10, length - i - 1);
                    if (result_temp >= Integer.MAX_VALUE)
                        return Integer.MAX_VALUE;
                } else {
                    char c = num.charAt(i);
                    int digit = c - '0';
                    result_temp +=(double) digit * Math.pow(10, length - i - 1) * (-1);
                    if (result_temp <= Integer.MIN_VALUE)
                        return Integer.MIN_VALUE;
                }
        }

        return (int)result_temp;

    }

    public static void main(String[] args){
        Solution solution = new Solution();
        System.out.println(solution.myAtoi("-6147483648"));
    }
}
