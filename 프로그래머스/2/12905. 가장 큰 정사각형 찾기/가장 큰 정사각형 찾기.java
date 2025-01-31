import java.util.*;

class Solution
{
    public int solution(int [][]board)
    {
        int answer = 1234;
        
        int n = board.length; 
        int m = board[0].length;
        
        int[][] dp = new int[n][m];
        
        for (int i=0; i<n; i++) {
            dp[i] = board[i].clone();
        }
        
        boolean found = false;
        for (int i=0; i<n; i++) {
            Arrays.sort(board[i]);
            found = Arrays.binarySearch(board[i], 1) >= 0;
        }
        
        int max = 0;
        for (int i=1; i<n; i++) {
            for (int j=1; j<m; j++) {
                if (dp[i][j] == 0) continue;
                dp[i][j] = Math.min(Math.min(dp[i-1][j],dp[i-1][j-1]),dp[i][j-1]) + 1;
                max = Math.max(max, dp[i][j]);
            }
        }
        
        if (max == 0 && found) {
            return 1;
        }
            
        answer = max * max;

        return answer;
    }
}