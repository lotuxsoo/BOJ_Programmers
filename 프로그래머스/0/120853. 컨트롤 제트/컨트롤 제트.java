import java.util.*;

class Solution {
    public int solution(String s) {
        int answer = 0;
        
        Deque<Integer> stack = new ArrayDeque<>();
        
        String[] splits = s.split(" ");
    
        for (String str : splits) {
            if (str.equals("Z")) {
                if (!stack.isEmpty()) {
                    stack.pop();
                }
            } else {
                stack.push(Integer.parseInt(str));
            }
        }
        
        int sum = 0;
        
        while (!stack.isEmpty()) {
            sum += stack.pop();
        }
        
        answer = sum;
        
        return answer;
    }
}