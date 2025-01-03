import java.util.*;

class Solution {
    public int[] solution(int[] prices) {
        int[] answer = {};
        
        Deque<Integer> stack = new ArrayDeque<>();
        
        int[] result = new int[prices.length];
        
        for (int i=0; i<prices.length; i++) {
            while (!stack.isEmpty() && prices[i] < prices[stack.peek()]) {
                int last = stack.pop();
                result[last] = i - last;
            }
            stack.push(i); // 현재 인덱스 푸시
        }
        
        while (!stack.isEmpty()) {
            int last = stack.pop();
            result[last] = prices.length-1-last;
        }
        
        answer = Arrays.copyOf(result, result.length);
        
        return answer;
    }
}