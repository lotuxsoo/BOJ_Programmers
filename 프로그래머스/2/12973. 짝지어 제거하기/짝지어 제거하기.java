import java.util.*;

class Solution
{
    public int solution(String s)
    {
        int answer = 0;
        
        Deque<Character> stack = new ArrayDeque<>();
        
        for (char c : s.toCharArray()) {
            if (!stack.isEmpty() && stack.peek() == c) {
                stack.pop();
            } else {
                stack.push(c);
            }
        }
        
        if (stack.isEmpty()) return 1;
        
        return answer;
    }
}