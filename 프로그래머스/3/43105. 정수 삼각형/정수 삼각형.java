import java.util.*;

class Solution {
    
    static int[][] dp;
    
    public int solution(int[][] triangle) {
        int answer = 0;
        int n = triangle.length;
        dp = new int[n][n];
    
        for (int i=0; i<n; i++) {
            Arrays.fill(dp[i], -1);
        }
        
        // 초깃값 설정
        dp[0][0] = triangle[0][0];
        
        
        for (int i=0; i<n; i++) {
            // 모두 왼쪽에서만 옴
            if (i > 0) dp[i][0] = Math.max(dp[i-1][0] + triangle[i][0], dp[i][0]);
            // 모두 오른쪽에서만 옴
            if (i > 0) dp[i][i] = Math.max(dp[i-1][i-1] + triangle[i][i], dp[i][i]);
            
            for (int j=1; j<i; j++) {
                dp[i][j] = Math.max(dp[i-1][j], dp[i-1][j-1]) + triangle[i][j];
            }
        }
        
        int max = Integer.MIN_VALUE;
        for (int i=0; i<n; i++) {
            if (max < dp[n-1][i]) max = dp[n-1][i];
        }
        
        answer = max;
        
        return answer;
    }
}