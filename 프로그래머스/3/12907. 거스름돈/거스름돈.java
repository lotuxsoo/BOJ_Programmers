import java.util.*;

class Solution {
    public int solution(int n, int[] money) {
        int answer = 0;
        int mod = 1_000_000_007;
        
        int[] dp = new int[n+1];
        dp[0] = 1; // 0원을 만드는 방법은 1가지
        
        for (int coin : money) {
            for (int i=coin; i<=n; i++) {
                dp[i] = (dp[i] + dp[i-coin]) % mod;
            }
        }
        
        answer = dp[n];
        return answer;
    }
}