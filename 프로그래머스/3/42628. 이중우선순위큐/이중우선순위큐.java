import java.util.*;

class Solution {
    public int[] solution(String[] operations) {
        int[] answer = {};
        
        PriorityQueue<Integer> maxHeap = new PriorityQueue<>((o1,o2) -> o2-o1);
        PriorityQueue<Integer> minHeap = new PriorityQueue<>();
        
        for (String str : operations) {
            String[] splits = str.split(" ");
            if (splits[0].equals("I")) {
                maxHeap.add(Integer.parseInt(splits[1]));
                minHeap.add(Integer.parseInt(splits[1]));
            } else if (splits[0].equals("D")) {
                if (splits[1].equals("1")) { // 최대힙
                    minHeap.remove(maxHeap.poll());
                } else if (splits[1].equals("-1")) { // 최소힙
                    maxHeap.remove(minHeap.poll());
                }
            }
        }
        
        if (!maxHeap.isEmpty() && !minHeap.isEmpty()) {
            answer = new int[]{maxHeap.poll(), minHeap.poll()};
        } else {
            answer = new int[]{0,0};
        }
        
        return answer;
    }
}