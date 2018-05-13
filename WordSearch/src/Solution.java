import java.util.ArrayList;
import java.util.List;

/**
 * Created by Yihuan on 2018/5/12. LeetCode 79
 */
public class Solution {
    private char[][] board_global;
    private String word_global;
    private int[][] board_tag;

    public boolean exist(char[][] board, String word) {
        board_global = board;
        word_global = word;
        board_tag = new int[board.length][board[0].length];

        boolean isFind = false;
        for(int i = 0 ; i < board.length ; i++)
            for(int j = 0 ; j < board[i].length ; j++){
                if(board[i][j] != word.charAt(0))
                    continue;
                board_tag[i][j] = 1;
                isFind = find(i , j , 1);
                board_tag[i][j] = 0;
                if(isFind)
                    return true;

            }
        return false;
    }

    private boolean find(int start_i , int start_j , int targetNo){
        //last_step 1 2 3 4 up down left right
        if(targetNo == word_global.length())
            return true;

        char tar = word_global.charAt(targetNo);
        int next_target = targetNo + 1;
        boolean isFind = false;


        //上
        if(start_i != 0  && board_tag[start_i - 1][start_j] != 1){
            if(board_global[start_i - 1][start_j] == tar){
                board_tag[start_i - 1][start_j] = 1;
                isFind = find(start_i - 1 , start_j , next_target);
                board_tag[start_i - 1][start_j] = 0;
            }
            if(isFind)
                return true;
        } // end up

        //下
        if(start_i != board_global.length - 1  && board_tag[start_i + 1][start_j] != 1){
            if(board_global[start_i + 1][start_j] == tar)
            {
                board_tag[start_i + 1][start_j] = 1;
                isFind = find(start_i + 1 , start_j , next_target);
                board_tag[start_i + 1][start_j] = 0;
            }
            if(isFind)
                return true;
        }// end down

        //left
        if(start_j != 0  && board_tag[start_i][start_j - 1] != 1){
            if(board_global[start_i][start_j - 1] == tar) {
                board_tag[start_i][start_j - 1] = 1;
                isFind = find(start_i, start_j - 1, next_target);
                board_tag[start_i][start_j - 1] = 0;
            }
            if(isFind)
                return true;
        }//end left

        //right
        if(start_j != board_global[0].length - 1  && board_tag[start_i][start_j + 1] != 1){
            if(board_global[start_i][start_j + 1] == tar) {
                board_tag[start_i][start_j + 1] = 1;
                isFind = find(start_i, start_j + 1, next_target);
                board_tag[start_i][start_j + 1] = 0;
            }
            if(isFind)
                return true;
        }

        return false;
    }

    public static void main(String[] args){
        Solution solution = new Solution();
        String word = "AAAAAAAAAAAAA";
        char[][] board = {{'A','A','A','A'},{'A','A','A','A'},{'A','A','A','A'}};
        System.out.println(solution.exist(board , word));
    }
}
