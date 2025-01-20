import java.util.*;

class Solution {
    public int[] solution(int[] prices) {
        int[] answer = {};
        
        int index = 0;
        answer = new int[prices.length];
        
        Deque<int[]> stack = new ArrayDeque<>();
        
        for (int i=0; i<prices.length; i++) {
            while (!stack.isEmpty() && prices[i] < stack.peek()[1]) {
                answer[stack.peek()[0]] = index - stack.pop()[0];
            }
            stack.push(new int[]{i, prices[i]});
            
            if (i != prices.length-1) index++;
        }
        
        while (!stack.isEmpty()) {
            answer[stack.peek()[0]] = index - stack.pop()[0];
        }
        
        return answer;
    }
}