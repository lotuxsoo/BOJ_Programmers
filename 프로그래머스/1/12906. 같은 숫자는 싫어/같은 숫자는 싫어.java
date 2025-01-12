import java.util.*;

public class Solution {
    public int[] solution(int []arr) {
        int[] answer = {};
        
        Deque<Integer> stack = new ArrayDeque<>();
        
        for (int i=0; i<arr.length; i++) {
            
            if (!stack.isEmpty() && stack.peek() == arr[i]) {
                stack.pop();
            }
            stack.push(arr[i]);
            
        }
        
        answer = new int[stack.size()];
        for (int i=stack.size()-1; i>=0; i--) {
            answer[i] = stack.pop();
        }
        
        return answer;
    }
}