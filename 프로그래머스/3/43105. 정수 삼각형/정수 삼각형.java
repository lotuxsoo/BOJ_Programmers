import java.util.*;

class Solution {
    public int solution(int[][] triangle) {
        int answer = 0;
        
        // dp 테이블 생성
        int n = triangle.length;
        int[][] dp = new int[n][n];
        
        // 초기값 설정
        dp[0][0] = triangle[0][0];
        
        // 점화식 세우기
        // 1) 가장 왼쪽: 바로 위에서만 내려올 수 있음
        // 2) 가장 오른쪽: 바로 왼쪽 위에서만 내려올 수 있음
        // 3) 나머지: 바로 위, 왼쪽 위 모두 가능
        for (int i=1; i<n; i++) {
            for (int j=0; j<triangle[i].length; j++) {
                if (j == 0) {
                    dp[i][j] = dp[i-1][j] + triangle[i][j];
                } else if (i == j) {
                    dp[i][j] = dp[i-1][j-1] + triangle[i][j];
                } else {
                    dp[i][j] = Math.max(dp[i-1][j],dp[i-1][j-1]) + triangle[i][j];
                }
            }
        }
        
        answer = -1;
        for (int i=0; i<triangle[n-1].length; i++) {
            answer = Math.max(answer, dp[n-1][i]);
        }

        return answer;
    }
}