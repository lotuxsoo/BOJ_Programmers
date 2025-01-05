import java.util.*;

class Solution {
    static Map<String, PriorityQueue<String>> map = new HashMap<>();
    
    public String[] solution(String[][] tickets) {
        String[] answer = {};
        
        for (String[] ticket : tickets) {
            map.putIfAbsent(ticket[0], new PriorityQueue<>());
            map.get(ticket[0]).add(ticket[1]);
        }
        
        Deque<String> stack = new ArrayDeque<>();
        stack.push("ICN");
        
        List<String> list = new ArrayList<>();
        
        while (!stack.isEmpty()) {
            
            while (map.containsKey(stack.peek()) && !map.get(stack.peek()).isEmpty()) {
                String next = map.get(stack.peek()).poll();
                stack.push(next);
            }
            
            list.add(0, stack.pop());
        }
        
        answer = list.toArray(new String[0]);
        
        return answer;
    }
}