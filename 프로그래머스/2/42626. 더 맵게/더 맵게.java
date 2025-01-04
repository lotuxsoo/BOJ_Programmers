import java.util.*;

class Solution {
    public int solution(int[] scoville, int K) {
        int answer = 0;
        
        PriorityQueue<Integer> pq = new PriorityQueue<>();
        for (int x : scoville) {
            pq.add(x);
        }
        
        while (pq.peek() < K) {
            if (pq.size() < 2) return -1;
            
            int val = pq.poll() + pq.poll()*2;
            pq.add(val);
            answer++;
        }
        
        return answer;
    }
}