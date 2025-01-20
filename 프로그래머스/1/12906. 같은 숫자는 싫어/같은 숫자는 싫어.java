import java.util.*;

public class Solution {
    public int[] solution(int []arr) {
        int[] answer = {};
        
        Deque<Integer> stack = new ArrayDeque<>();
        for (int x : arr) {
            if (!stack.isEmpty() && stack.peek() == x) {
                stack.pop();
            }
            stack.push(x);
        }
        
        List<Integer> list = new ArrayList<>(stack);
        
        int i = list.size()-1;
        answer = new int[list.size()];
        for (int x : list) {
            answer[i--] = x;
         }
        
        return answer;
    }
}