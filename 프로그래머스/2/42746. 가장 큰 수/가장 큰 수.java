import java.util.*;

class Solution {
    public String solution(int[] numbers) {
        String answer = "";
        
        PriorityQueue<String> pq = new PriorityQueue<>((a,b) -> (b+a).compareTo(a+b));
        
        for (int i : numbers) {
            pq.add(String.valueOf(i));
        }
        
        StringBuilder sb = new StringBuilder();
        while (!pq.isEmpty()) {
            sb.append(pq.poll());
        }
        
        answer = sb.charAt(0) == '0' ? "0" : sb.toString();
        
        return answer;
    }
}