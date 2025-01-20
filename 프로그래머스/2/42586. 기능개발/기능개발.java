import java.util.*;

class Solution {
    public int[] solution(int[] progresses, int[] speeds) {
        int[] answer = {};

        int N = progresses.length;
        int[] days = new int[N];
        
        for (int i=0; i<N; i++) {
            int x = (int) Math.ceil((100.0-progresses[i])/speeds[i]);
            days[i] = x;
        }
        
        ArrayList<Integer> list = new ArrayList<>();
        
        int maxDay = days[0];
        
        int cnt = 0;
        for (int i=0; i<N; i++) {
            if (days[i] <= maxDay) {
                cnt++;
            } else {
                list.add(cnt);
                cnt = 1;
                maxDay = days[i];
            }
        }
        list.add(cnt);
        
        answer = new int[list.size()];
        for (int i=0; i<list.size(); i++) {
            answer[i] = list.get(i);
        }
        
        return answer;
    }
}