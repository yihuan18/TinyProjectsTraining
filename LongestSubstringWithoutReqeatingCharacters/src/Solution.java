import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * Created by Yihuan on 2018/5/10.
 */
public class Solution {
    public int lengthOfLongestSubstring(String s) {
        int retLength = 0;
        Map<Character,Integer>map = new HashMap<>();
        for(int i = 0 ; i < s.length() ; i++)
        {
            map.clear();
            int j = i;
            while(j < s.length()){
                if(map.get(s.charAt(j)) == null ) {
                    map.put(s.charAt(j), 1);
                    j++;
                }
                else
                    break;
            }
            if((j - i) > retLength)
                retLength = j - i;

            if(retLength > s.length() - i)
                break;
        }
        return retLength;
    }

    /*
    别人的厉害的做法
     public int lengthOfLongestSubstring(String s) {
        int[] list = new int[256];
        int previous = -1, right = 0, max_len = 0;
        for(int i=0;i<list.length;i++){
            list[i]=-1;
        }
        while(right<s.length()){
            char c = s.charAt(right);
            if(list[(int)c] > previous)
                previous = list[(int)c];
            max_len = Math.max(max_len, right - previous);
            list[(int)c] = right++;
        }
        return max_len;
    }
    */

    public static void main(String[] args){
        Solution solution = new Solution();
        String s = "pwwkew";
        System.out.println(solution.lengthOfLongestSubstring(s));
    }
}
