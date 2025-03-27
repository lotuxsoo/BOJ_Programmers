import java.util.*;

class Solution {
    public int[] solution(String[] operations) {
        int[] answer = {};
        
        PriorityQueue<Integer> minHeap = new PriorityQueue<>((a,b) -> Integer.compare(a,b));
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>((a,b) -> Integer.compare(b,a));
        
        for (String oper : operations) {
            if (oper.equals("D 1")) {
                Integer max = maxHeap.poll();
                if (max != null) minHeap.remove(max);
            } else if (oper.equals("D -1")) {
                Integer min = minHeap.poll();
                if (min != null ) maxHeap.remove(min);
            } else {
                int num = Integer.parseInt(oper.split(" ")[1]);
                minHeap.add(num);
                maxHeap.add(num);
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