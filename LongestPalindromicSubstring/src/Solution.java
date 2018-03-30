import java.util.Arrays;

/**
 * Created by Yihuan on 2018/3/30.
 */
public class Solution {
    public String longestPalindrome(String s) {
        int begin, end;
        int PalindromeLength[] = new int[s.length()];
        for(begin = 0 ; begin < s.length() ; begin++){
            end = s.length();
            while(!isPalindrome(s.substring(begin,end))){
               end--;
                if(end == begin + 1)
                    break;
            }
            PalindromeLength[begin] = s.substring(begin,end).length();
            if(PalindromeLength[begin] >= (s.length() - begin - 1))
                break;
        }
        int maxIndex = 0;
        int max = 0;
        for(int i = 0 ; i<s.length(); i++){
            if(PalindromeLength[i] > max){
                max = PalindromeLength[i];
                maxIndex = i;
            }
        }
        return s.substring(maxIndex,maxIndex+PalindromeLength[maxIndex]);
    }

    public boolean isPalindrome(String str) {
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

    public static void  main(String[] args){
        Solution s = new Solution();
        System.out.print(s.longestPalindrome("aba"));
    }
}
