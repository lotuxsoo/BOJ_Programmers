import java.util.*;

class Solution {
    public long solution(long n) {
        long answer = 0;
        
        String s = String.valueOf(n);
        
        char[] ch = s.toCharArray();
        
        Arrays.sort(ch);
        
        for (int i=0,j=ch.length-1; i<j; i++,j--) {
            char temp = ch[i];
            ch[i] = ch[j];
            ch[j] = temp;        
        }
        
        StringBuilder sb = new StringBuilder();
        for (char c : ch) {
            sb.append(c);
        }
        
        answer = Long.parseLong(sb.toString());
        
        return answer;
    }
}