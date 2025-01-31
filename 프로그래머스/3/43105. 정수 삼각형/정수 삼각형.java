class Solution {
    public int solution(int[][] triangle) {
        int answer = 0;
        
        int n = triangle.length; // 세로 길이
        int[][] dp = new int[n][n];
        
        // 초기화
        dp[0][0] = triangle[0][0];
        for (int i=1; i<n; i++) {
            dp[i][0] = dp[i-1][0] + triangle[i][0];
        }
        
        for (int i=1; i<n; i++) {
            dp[i][i] = dp[i-1][i-1] + triangle[i][i];
        }
        
        for (int i=2; i<n; i++) {
            for (int j=1; j<=i; j++) {
                dp[i][j] = Math.max(dp[i-1][j], dp[i-1][j-1]) + triangle[i][j];
            }
        }
        
        for (int i=0; i<n; i++) {
            answer = Math.max(answer, dp[n-1][i]);
        }
        
        return answer;
    }
}