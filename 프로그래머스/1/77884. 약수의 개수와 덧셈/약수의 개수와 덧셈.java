class Solution {
    public static int yaksu(int number) {
        int cnt = 0;
        for (int i=1; i<=number; i++) {
            if (number%i == 0) {
                cnt++;
            }
        }
        return cnt;
    }
    
    public int solution(int left, int right) {
        int answer = 0;
        
        for (int i=left; i<=right; i++) {
            int ret = yaksu(i);
            if (ret%2 == 0) answer += i;
            else answer -= i;
        }
        
        return answer;
    }
}