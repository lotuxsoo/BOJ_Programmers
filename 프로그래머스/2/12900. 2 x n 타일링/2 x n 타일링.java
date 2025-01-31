class Solution {
    static int MOD = 1000000007;
    
    public int solution(int n) {
        int answer = 0;
        
        int[][] dp = new int[n+1][2]; 
        dp[1][0] = 1;
        dp[2][0] = 1;
        dp[2][1] = 1;
        
        if (n < 3) {
            return dp[n][0] + dp[n][1];
        }
        
        for (int i=3; i<n+1; i++) {
            dp[i][0] = (dp[i-1][0] + dp[i-1][1]) % MOD;
            dp[i][1] = (dp[i-2][0] + dp[i-2][1]) % MOD;
        }
        
        answer = (dp[n][0] + dp[n][1]) % MOD;
        
        return answer;
    }
}