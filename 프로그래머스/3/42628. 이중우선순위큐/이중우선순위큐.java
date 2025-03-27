import java.util.*;

class Solution {
    public int[] solution(String[] operations) {
        int[] answer = {};
        
        PriorityQueue<Integer> minHeap = new PriorityQueue<>((a,b) -> Integer.compare(a,b));
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>((a,b) -> Integer.compare(b,a));
        
        for (String oper : operations) {
            String[] sp = oper.split(" ");
            String d = sp[0];
            int n = Integer.parseInt(sp[1]);
            
            if (d.equals("I")) {
                minHeap.add(n);
                maxHeap.add(n);
            } else if (d.equals("D")) {
                if (minHeap.isEmpty() || maxHeap.isEmpty()) {
                    continue;
                }
                if (n == 1) {
                    int max = maxHeap.poll();
                    minHeap.remove(max);
                } else {
                    int min = minHeap.poll();
                    maxHeap.remove(min);
                }
            }   
        }
        
        if (minHeap.isEmpty() || maxHeap.isEmpty()) {
            answer = new int[]{0,0};
        } else {
            int min = minHeap.poll();
            int max = maxHeap.poll();
            answer = new int[]{max,min};
        }
        
        return answer;
    }
}