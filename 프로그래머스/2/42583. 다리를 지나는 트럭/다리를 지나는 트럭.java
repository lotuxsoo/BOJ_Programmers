import java.util.*;

class Solution {
    public int solution(int bridge_length, int weight, int[] truck_weights) {
        int answer = 0;
        
        Deque<Integer> bridge = new ArrayDeque<>(); // 브릿지 큐 생성
        
        for (int i=0; i<bridge_length; i++) {
            bridge.add(0); // 초기에는 전부 0
        }
        
        int time = 0;
        int current_weight = 0;
        int index = 0;
        
        while (index < truck_weights.length || current_weight > 0) {
            time++;
            
            // 다리에서 트럭 제거
            current_weight -= bridge.pollFirst();
            
            if (index < truck_weights.length) {
                if (current_weight + truck_weights[index] <= weight) {
                    // 다리에 새로운 트럭 추가
                    bridge.add(truck_weights[index]);
                    current_weight += truck_weights[index];
                    index++;
                } else {
                    // 새로운 트럭 추가 불가
                    bridge.add(0);
                }
            } else {
                // 더 이상 대기 트럭이 없을때
                bridge.add(0);
            }
        }
        
        answer = time;
        
        return answer;
    }
}