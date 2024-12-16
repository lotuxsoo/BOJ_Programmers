import java.util.*;

class Solution {
    boolean solution(String s) {
        boolean answer = true;
        
        Stack<Character> stack = new Stack<>();
        
        char[] ch = s.toCharArray();
        for(int i=0; i<ch.length; i++) {
            if (ch[i] == '(') {
                stack.push('(');
            } else if (ch[i] == ')' && stack.size()>0) {
                if (stack.peek() == '(') {
                    stack.pop();
                } else {
                    return false;
                }
            } else {
                return false;
            }
        }
        
        if (stack.size() > 0) return false;

        return answer;
    }
}