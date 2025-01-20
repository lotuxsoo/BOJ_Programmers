import java.util.*;

class Solution {
    public int solution(int[][] board, int[] moves) {
        int answer = 0;
        
        Deque<Integer> stack = new ArrayDeque<>();
        
        for (int i=0; i<moves.length; i++) {
            int col = moves[i]-1;
            for (int j=0; j<board.length; j++) {
                if (board[j][col] != 0) {
                    if (!stack.isEmpty() && stack.peek() == board[j][col]) {
                        stack.pop();
                        answer += 2;
                    } else {
                        stack.push(board[j][col]);
                    }
                    board[j][col] = 0;
                    break;
                }
            }
        }
        
        return answer;
    }
}