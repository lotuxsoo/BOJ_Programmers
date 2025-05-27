import java.util.*;

class Solution {
    public String solution(String number, int k) {
        String answer = "";
        
        Deque<Character> stack = new ArrayDeque<>();
        
        char[] ch = number.toCharArray();
        
        for (int i=0; i<ch.length; i++) {
            while (!stack.isEmpty() && (stack.peek() < ch[i]) && (k > 0)) {
                stack.pop();
                k--;
            }
            stack.push(ch[i]);
        }
        
        StringBuilder sb = new StringBuilder();
        
        while (!stack.isEmpty()) {
            sb.insert(0, stack.pop());    
        }

        while (k > 0) {
            sb.deleteCharAt(sb.length()-1);
            k--;
        }
        
        answer = sb.toString();
        
        return answer;
    }
}