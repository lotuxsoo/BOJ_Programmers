import java.util.*;

class Solution {
    boolean solution(String s) {
        boolean answer = true;
        
        Map<Character,Character> table = new HashMap<>();
        table.put(')', '('); // 닫는괄호, 여는괄호
        
        Deque<Character> stack = new ArrayDeque<>();
        
        for (int i=0; i<s.length(); i++) {      
            if (!table.containsKey(s.charAt(i))) { // 여는괄호
                stack.push(s.charAt(i));
            } else if (stack.isEmpty() || table.get(s.charAt(i)) != stack.pop()) {
                return false;
            }
        }
        
        answer = stack.size() == 0;

        return answer;
    }
}