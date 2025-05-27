import java.util.*;

class Solution {
    public int solution(String name) {
        int answer = 0;
        
        int n = name.length();
        
        for (int i=0; i<n; i++) {
            answer += Math.min(name.charAt(i) - 'A', 'Z' - name.charAt(i) + 1);
        }
        
        int min = n-1;
        int i = 0;
        while (i < n) {
            int next = i+1;
            while (next<n && name.charAt(next)=='A') {
                next++;
            }
            
            int move = i + (n-next) + Math.min(i, n-next);
            min = Math.min(min, move);
            i = next;
        }
        
        answer += min;
        
        return answer;
    }
} 