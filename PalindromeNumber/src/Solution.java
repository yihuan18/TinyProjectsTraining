/**
 * Created by Yihuan on 2018/3/28.
 */

public class Solution {
    public boolean isPalindrome(int x) {
        if(x < 0)
            return false;
        String str = "" + x;
        String reverse = "";
        boolean isPalin = true;
        int length = str.length();
        for(int i = 0 ; i < (length/2) ; i++){
            if(str.charAt(i) != str.charAt(length-i-1)) {
                isPalin = false;
                break;
            }
        }
        return isPalin;
    }

    public static void main(String[] args)
    {
        Solution s = new Solution();
        System.out.print(s.isPalindrome(123321));
    }
}
