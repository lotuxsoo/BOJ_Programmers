import java.util.*;

class Solution {
    public int[] solution(int[] prices) {
        int[] answer = {};
        
        int n = prices.length;
        answer = new int[n];
        
        for (int i=0; i<n-1; i++) {
            int x = 0;
            for (int j=i+1; j<n; j++) {
                x++;
                if (prices[i] > prices[j]) {
                    break;
                }
            }
            answer[i] = x;
        }
        
        return answer;
    }
}