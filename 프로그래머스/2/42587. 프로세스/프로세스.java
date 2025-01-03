import java.util.*;

class Solution {
    static class Process { 
        int index;
        int priority;
        Process(int index, int priority) {
            this.index = index;
            this.priority = priority;
        }
    }
    
    public int solution(int[] priorities, int location) {
        int answer = 0;

        Queue<Process> que = new ArrayDeque<>();
        for (int i=0; i<priorities.length; i++) {
            que.add(new Process(i, priorities[i]));
        }
        
        int [] copy = Arrays.copyOf(priorities, priorities.length);
        Arrays.sort(copy); // 우선순위 오름차순
        int maxIndex = copy.length-1; // 가장 큰 우선순위 위치
        
        int cnt = 0;
        while (!que.isEmpty()) {
            Process now = que.poll();
            if (now.priority == copy[maxIndex]) {
                cnt++;
                maxIndex--;
                if (now.index == location) {
                    break;
                }
            } else {
                que.add(now);
            }
        }
        
        answer = cnt;
 
        return answer;
    }
}