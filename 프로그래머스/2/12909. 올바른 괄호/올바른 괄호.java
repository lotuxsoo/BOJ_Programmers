import java.util.*;

class Solution {
    boolean solution(String s) {
        boolean answer = true;
        
        Deque<String> stack = new ArrayDeque<>();
        
        
        for (String str : s.split("")) {
            if (str.equals("(")) {
                stack.push("(");
            } else if (str.equals(")")) {
                if (stack.isEmpty()) {
                    return false;
                } else {
                    if (stack.peek().equals("(")) {
                        stack.pop();
                    } else {
                        return false;
                    }
                }
            }  
        }
        
        if (!stack.isEmpty()) return false;

        return answer;
    }
}