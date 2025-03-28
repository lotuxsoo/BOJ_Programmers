import java.util.*;

class Solution {
    public int solution(int[] scoville, int K) {
        int answer = 0;
        
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        
        for (int x : scoville) {
            pq.add(x);
        }
        
        while (!pq.isEmpty() && pq.peek() < K) {
            Integer x = pq.poll();
            Integer y = pq.poll();
            if (y == null) return -1;
            pq.add(x + y * 2);
            answer++;
        }
        
        return answer;
    }
}