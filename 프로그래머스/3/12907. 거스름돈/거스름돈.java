class Solution {
    
    static int[] dp; // x원을 만드는 방법의 수
    
    public int solution(int n, int[] money) {
        int answer = 0;
        
        dp = new int[n+1]; // dp 배열은 총 0...n+1
        
        dp[0] = 1; // 0원을 만드는 방법
            
        for (int coin : money) {
            for (int i=coin; i<=n; i++) {
                dp[i] += dp[i-coin];
            }
        }
        
        answer = dp[n];
        
        return answer;
    }
}