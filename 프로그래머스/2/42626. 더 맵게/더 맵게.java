import java.util.*;

class Solution {
    public int solution(int[] scoville, int K) {
        int answer = 0;
        
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        
        for (int x : scoville) {
            pq.add(x);
        }
        
        while (true) {
            Integer x = pq.poll();
            if (x == null) return -1;
            if (x >= K) break;
            
            if (x < K) {
                Integer y = pq.poll();
                if (y == null) return -1;
                int newSum = x + y * 2;
                pq.add(newSum);
                answer++;
            }
        }
        
        return answer;
    }
}