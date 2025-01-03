import java.util.*;

public class Solution {
    public int[] solution(int []arr) {
        int[] answer = {};
        
        Stack<Integer> stack = new Stack<>();
        
        for (int i : arr) {
            if (stack.size() > 0 && i == stack.peek()) {
                stack.pop();
            } 
            stack.push(i);
        }
        
        answer = new int[stack.size()];
        for (int i=stack.size()-1; i>=0; i--) {
            answer[i] = stack.pop();
        }

        return answer;
    }
}