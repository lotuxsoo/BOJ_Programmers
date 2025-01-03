import java.util.*;

class Solution {
    public int[] solution(int[] progresses, int[] speeds) {
        int[] answer = {};
        
        int[] arr = new int[progresses.length];
        for (int i=0; i<progresses.length; i++) {
            int days = (100-progresses[i])/speeds[i];
            if ((100-progresses[i])%speeds[i] != 0) {
                days += 1;
            }
            arr[i] = days;
        }
        
        // 큐에 작업 넣기
        Queue<Integer> que = new ArrayDeque<>();
        ArrayList<Integer> result = new ArrayList<>();
        for (int i : arr) {
            if (!que.isEmpty()) {
                if (que.peek() >= i) {
                    que.add(i); // 이전 작업에 추가
                } else {
                    result.add(que.size()); // 이전 작업 개수
                    que.clear(); // 초기화 
                    que.add(i); // 작업 추가
                }
            } else {
                que.add(i);
            }
        }
        
        if (que.size() > 0) {
            result.add(que.size());
        }
        
        answer = new int[result.size()];
        for (int i=0; i<answer.length; i++) {
            answer[i] = result.get(i);
        }
        
        return answer;
    }
}