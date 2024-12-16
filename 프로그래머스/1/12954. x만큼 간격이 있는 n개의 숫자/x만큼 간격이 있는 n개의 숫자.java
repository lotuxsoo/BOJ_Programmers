import java.util.*;

class Solution {
    public long[] solution(int x, int n) {
        long[] answer = {};
        ArrayList<Long> list = new ArrayList<>();
        
        long now = x;
        for (int i=0; i<n; i++) {
            list.add(now);
            now += x;
        }
        
        answer = list.stream().mapToLong(Long::longValue).toArray();
        
        return answer;
    }
}