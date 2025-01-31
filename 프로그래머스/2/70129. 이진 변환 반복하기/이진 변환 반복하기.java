import java.util.*;

class Solution {
    public int[] solution(String s) {
        int[] answer = {};
        
        int zeros = 0;
        int cnt = 0;
        
        while (!s.equals("1")) {
            cnt++;
            
            // 0 제거
            int n = s.length();
            s = s.replace("0","");
            if (n - s.length() > 0) {
                zeros += n - s.length();     
            }
            
            // 이진수 변환
            StringBuilder sb = new StringBuilder();
            n = s.length();
            while (n > 0) {
                sb.append(n % 2); 
                n /= 2;
            }
            s = sb.reverse().toString();
        }
        
        answer = new int[]{cnt, zeros};
        
        return answer;
    }
}