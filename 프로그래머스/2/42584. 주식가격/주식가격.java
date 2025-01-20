import java.util.*;

class Solution {
    public int[] solution(int[] prices) {
        int[] answer = {};
        
        Deque<Integer> stack = new ArrayDeque<>();
        
        int n = prices.length;
        answer = new int[n];
        
        for (int i=0; i<n; i++) {
            while (!stack.isEmpty() && prices[stack.peek()] > prices[i]) {
                answer[stack.peek()] = i - stack.pop();
            }
            stack.push(i);
        }
        
        while (!stack.isEmpty()) {
            answer[stack.peek()] = n-1-stack.pop();
        }
        
        return answer;
    }
}