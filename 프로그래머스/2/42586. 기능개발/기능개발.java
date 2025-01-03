import java.util.*;

class Solution {
    public int[] solution(int[] progresses, int[] speeds) {
        int[] answer = {};
        
        int[] days = new int[progresses.length];
        for (int i=0; i<progresses.length; i++) {
            int day = (100-progresses[i])/speeds[i];
            if ((100-progresses[i])%speeds[i] != 0) {
                day += 1;
            }
            days[i] = day;
        }
        
        int baseTime = days[0];
        int cnt = 0;
        ArrayList<Integer> result = new ArrayList<>();
        for (int day : days) {
            if (day <= baseTime) {
                cnt++;
            } else {
                result.add(cnt);
                baseTime = day;
                cnt = 1;
            }
        }
        result.add(cnt);
        
        answer = new int[result.size()];
        for (int i=0; i<answer.length; i++) {
            answer[i] = result.get(i);
        }
        
        return answer;
    }
}