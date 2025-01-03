import java.util.*;

class Solution {
    boolean solution(String s) {
        boolean answer = true;
        
        int openCount = 0;
        
        for (char c : s.toCharArray()) {
            if (c == '(') {
                openCount++;
            } else {
                openCount--;
                if (openCount < 0) {
                    return false;
                }
            }   
        }
        
        if (openCount > 0) return false;

        return answer;
    }
}